package com.exflyer.oddi.admin.api.notification.send.service;

import com.exflyer.oddi.admin.api.adv.make.code.AdvAuditCodes;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.api.notification.send.code.NotificationCodes;
import com.exflyer.oddi.admin.api.notification.send.dao.NotificationMapper;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchReq;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchResult;
import com.exflyer.oddi.admin.api.notification.send.dto.NotificationReq;
import com.exflyer.oddi.admin.api.notification.send.dto.NotificationSearchCodes;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTarget;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTargetResult;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchPartnerReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Member;
import com.exflyer.oddi.admin.models.Notification;
import com.exflyer.oddi.admin.models.NotificationGroup;
import com.exflyer.oddi.admin.models.NotificationTarget;
import com.exflyer.oddi.admin.models.NotificationTargetGroup;
import com.exflyer.oddi.admin.models.Partner;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.MemberRepository;
import com.exflyer.oddi.admin.repository.NotificationGroupRepository;
import com.exflyer.oddi.admin.repository.NotificationRepository;
import com.exflyer.oddi.admin.repository.NotificationTargetGroupRepository;
import com.exflyer.oddi.admin.repository.NotificationTargetRepository;
import com.exflyer.oddi.admin.repository.PartnerRepository;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationTargetRepository notificationTargetRepository;
    @Autowired
    private NotificationTargetGroupRepository notificationTargetGroupRepository;
    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private NotificationMapper notificationMapper;
//    @Autowired
//    private KakaoNotificationConfig kakaoNotificationConfig;
    @Autowired
    private AesEncryptor aesEncryptor;
    @PersistenceContext
    EntityManager em;

    public PagingResult<SearchGroupTargetResult> findByGroupTargetSearchReq(SearchGroupTarget searchReq) {

        searchReq.setOddiPartnerCode(NotificationCodes.NOTIFICATION_ODDI_PARTNER.getCode());
        searchReq.setSubwayPartnerCode(NotificationCodes.NOTIFICATION_SUBWAY_PARTNER.getCode());
        searchReq.setNotificationMemberCode(NotificationCodes.NOTIFICATION_SEARCH_MEMBER.getCode());
        searchReq.setNotificationTelCode(NotificationCodes.NOTIFICATION_SEARCH_TEL.getCode());

        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<SearchGroupTargetResult> result = notificationMapper.findByGroupTargetSearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }

    public PagingResult<MemberSearchResult> findByMemberSearchReq(MemberSearchReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        PageHelper.orderBy(searchReq.getOrderBy());
        searchReq.setExcludeMemberCode(NotificationCodes.EXCLUDE_MEMBER_CODE.getCode());
        Page<MemberSearchResult> result = notificationMapper.findByMemberSearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }

    public PagingResult<SearchPartnerResult> findAdvNowExpoPartner(SearchPartnerReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        PageHelper.orderBy(searchReq.getOrderBy());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "a2.start_date"));
        searchReq.setNowDate(LocalDateUtils.krNowByFormatter("yyyyMMdd"));
        searchReq.setAuditApprovalCode(AdvAuditCodes.ADV_APPROVAL.getCode());
        searchReq.setProgressPaymentCompleteCode(AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        Page<SearchPartnerResult> result = notificationMapper.findAdvNowExpoPartner(searchReq);
        return PagingResult.createResultDto(result);
    }

    public NotificationSearchCodes findCodeForSearching() {
        List<Code> notificationTypeCode = codeRepository.findByGroupCodeAndUsable("NTC", true);
        List<Code> messageSendCode = codeRepository.findByGroupCodeAndUsable("MST", true);
        List<Code> messageTypeCode = codeRepository.findByGroupCodeAndUsable("MTC", true);
        return new NotificationSearchCodes(notificationTypeCode, messageSendCode, messageTypeCode);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void sendSms(NotificationReq sendReq) throws ApiException {
        NotificationTargetGroup notificationTargetGroup = new NotificationTargetGroup(sendReq);
        if(sendReq.getMessageSendType().equals(NotificationCodes.NOTIFICATION_IMMEDIATELY_SEND.getCode())){
            sendReq.setReservationDate("0");
        }

        notificationTargetGroupRepository.save(notificationTargetGroup);
        em.persist(notificationTargetGroup);

        //sendReq.setFromPhoneNumber(kakaoNotificationConfig.getReSendFromPhoneNum());
        sendReq.setRegName(NotificationCodes.MANAGER_NAME.getName());
        sendReq.setTargetGroupSeq(notificationTargetGroup.getSeq());

        // 회원검색
        if(sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_SEARCH_MEMBER.getCode())){
            saveNotificationGroup(sendReq);

            sendReq.getMemberIdList().forEach(data -> {
                Member member = null;
                Optional<Member> memberOptional = memberRepository.findById(data.getMemberId());
                try {
                    member = memberOptional.orElseThrow(()-> new ApiException(ApiResponseCodes.NOT_FOUND));
                } catch (ApiException e) {
                    e.printStackTrace();
                }

                sendReq.setSendPhoneNumber(aesEncryptor.decrypt(member.getPhoneNumber()));
                sendReq.setMemberName(data.getMemberName());
                sendReq.setMemberId(data.getMemberId());

                saveNotificationTarget(sendReq, data.getMemberName(), null);

                /*Notification notification = new Notification(sendReq);
                //회원정보 검색후 셋팅
                Optional<Member> memberOptional = memberRepository.findById(sendReq.getMemberId());
                try {
                    Member member = memberOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

                    notification.setReceiveName(member.getName());
                    notification.setReceivePhoneNumber(aesEncryptor.decrypt(member.getPhoneNumber()));

                    if((sendReq.getMessageTypeCode().equals(NotificationCodes.NOTIFICATION_ADV.getCode()) && member.getReceiveConsent() == 1) || sendReq.getMessageTypeCode().equals(NotificationCodes.NOTIFICATION_INFO.getCode())){
                        notificationRepository.save(notification);
                    }

                } catch (ApiException e) {
                    e.printStackTrace();
                }*/
            });
        }
        // 전화번호
        if(sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_SEARCH_TEL.getCode())){
            saveNotificationGroup(sendReq);

            sendReq.getSendPhoneNumberList().forEach(data -> {

                sendReq.setSendPhoneNumber(data);

                saveNotificationTarget(sendReq, data, null);

//                Notification notification = new Notification(sendReq);
//                notificationRepository.save(notification);
            });

        }
        // 전체회원, 전체광고주, 전체 오디존 광고주, 전체 지하철 광고주, 오디존광고주, 지하철광고주
        if(sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ALL_MEMBER.getCode())
            || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ALL_PARTNER.getCode())
            || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ALL_ODDI_PARTNER.getCode())
            || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ALL_SUBWAY_PARTNER.getCode())
            || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ODDI_PARTNER.getCode())
            || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_SUBWAY_PARTNER.getCode())
        ){
            saveNotificationGroup(sendReq);

            // 선택 오디존/지하철 파트너
            if(sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_ODDI_PARTNER.getCode()) || sendReq.getTargetCode().equals(NotificationCodes.NOTIFICATION_SUBWAY_PARTNER.getCode())) {
                sendReq.getPartnerList().forEach(datas -> {
                    Optional<Partner> partnerOptional = partnerRepository.findById(datas.getPartnerSeq());
                    Partner partner = null;
                    try {
                        partner = partnerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }

                    List<MemberSearchResult> lst = notificationMapper.findByAdvMemberSearchResult(datas.getPartnerSeq());

                    // 광고처에 속한 광고주별 발송
                    for(MemberSearchResult member : lst){

                        sendReq.setMemberId(member.getId());
                        sendReq.setName(member.getName());
                        sendReq.setSendPhoneNumber(aesEncryptor.decrypt(member.getPhoneNumber()));

                        saveNotificationTarget(sendReq, member.getName(), datas.getPartnerSeq());

                        /*Notification notification = new Notification(sendReq);
                        notification.setReceiveName(member.getName());
                        notification.setReceivePhoneNumber(aesEncryptor.decrypt(member.getPhoneNumber()));

                        notificationRepository.save(notification);*/
                    }
                });
            }
        }
    }

    //알림대상 등록(커스텀(전화번호, 회원, 오디존 광고주, 지하철 광고주)일때만 등록, 전체는 해당안됨)
    private void saveNotificationTarget(NotificationReq sendReq, String targetName, Long partnerSeq){
        NotificationTarget notificationTarget = new NotificationTarget(sendReq);
        notificationTarget.setPartnerSeq(partnerSeq);
        notificationTarget.setName(targetName);
        notificationTargetRepository.save(notificationTarget);
    }

    //그룹등록
    private void saveNotificationGroup(NotificationReq sendReq){
        sendReq.setTargetCreateDone(false);
        if (sendReq.getMessageTypeCode().equals(NotificationCodes.NOTIFICATION_ADV.getCode())) {
            sendReq.setAdvMessage(true);
        } else {
            sendReq.setAdvMessage(false);
        }
        sendReq.setAuto(false);
        sendReq.setDone(false);
        sendReq.setTargetCreateDone(false);
        sendReq.setAlrimTalk(false);
//        sendReq.setSendPhoneNumber(sendReq.getFromPhoneNumber());

        NotificationGroup notificationGroup = new NotificationGroup(sendReq);
        notificationGroupRepository.save(notificationGroup);
    }

}
