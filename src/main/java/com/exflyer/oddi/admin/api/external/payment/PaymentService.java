package com.exflyer.oddi.admin.api.external.payment;

import com.exflyer.oddi.admin.api.adv.make.code.AdvAuditCodes;
import com.exflyer.oddi.admin.api.external.notification.dto.KakaoNotificationReq;
import com.exflyer.oddi.admin.api.external.payment.dao.PaymentMapper;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelReq;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelRes;
import com.exflyer.oddi.admin.api.external.payment.dto.PaymentCancelRes;
import com.exflyer.oddi.admin.config.InicisConfig;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.KakaoNoticationCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Adv;
import com.exflyer.oddi.admin.models.KakaoTemplate;
import com.exflyer.oddi.admin.models.Manager;
import com.exflyer.oddi.admin.models.Notification;
import com.exflyer.oddi.admin.models.NotificationGroup;
import com.exflyer.oddi.admin.models.Payment;
import com.exflyer.oddi.admin.models.PgPaymentLog;
import com.exflyer.oddi.admin.models.PromotionCoupon;
import com.exflyer.oddi.admin.repository.AdvRepository;
import com.exflyer.oddi.admin.repository.KakaoTemplateRepository;
import com.exflyer.oddi.admin.repository.NotificationGroupRepository;
import com.exflyer.oddi.admin.repository.NotificationRepository;
import com.exflyer.oddi.admin.repository.PaymentRepository;
import com.exflyer.oddi.admin.repository.PgPaymentLogRepository;
import com.exflyer.oddi.admin.repository.PromotionCouponRepository;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.SignatureUtil;
import java.net.InetAddress;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private AdvRepository advRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PromotionCouponRepository promotionCouponRepository;
    @Autowired
    private PgPaymentLogRepository pgPaymentLogRepository;
    @Autowired
    private InicisConfig inicisConfig;
    @Autowired
    private AesEncryptor aesEncryptor;
    @Autowired
    private KakaoTemplateRepository kakaoTemplateRepository;
    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public InicisCancelRes paymentCancel(Long paymentSeq, Long advSeq, String memberId, String regId) throws Exception {

        InicisCancelRes inicisCancelRes;//????????? ??????
        PaymentCancelRes paymentCancelRes;

        if(paymentSeq == null){
            //memberId = aesEncryptor.encrypt(memberId);
            paymentCancelRes = paymentMapper.findMemberPaymentInfo(advSeq, memberId, AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        }else{
            paymentCancelRes = paymentMapper.findPaymentInfo(paymentSeq, advSeq, AdvAuditCodes.PROGRESS_PAYMENT_COMPLETE.getCode());
        }

        if (paymentCancelRes == null) {
            throw new ApiException(ApiResponseCodes.NOT_FOUND);
        }

        // hash(INIAPIKey+ type + paymethod + timestamp + clientIp + mid + tid)
        String hashData =
            inicisConfig.getInicisKey() + "Refund" + paymentCancelRes.getPayMethod() + LocalDateUtils.krNowByFormatter("yyyyMMddHHmmss")
                + inicisConfig.getInicisClientIpl()
                + paymentCancelRes.getMid()
                + paymentCancelRes.getTid();

        paymentCancelRes.setHashData(SignatureUtil.hash(hashData, "SHA-512"));

        log.debug("=================================================================================");
        log.debug("InicisKey, {} ", inicisConfig.getInicisKey());
        log.debug("PayMethod, {} ", paymentCancelRes.getPayMethod());
        log.debug("InicisClientIpl, {} ", inicisConfig.getInicisClientIpl());
        log.debug("Mid, {} ", paymentCancelRes.getMid());
        log.debug("Tid, {} ", paymentCancelRes.getTid());
        log.debug("hashData, {} ", hashData);
        log.debug("=================================================================================");
        InicisCancelReq inicisCancelReq = new InicisCancelReq(paymentCancelRes, inicisConfig.getInicisClientIpl());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> paramMap = objectMapper.convertValue(inicisCancelReq, Map.class);

        HttpUtil httpUtil = new HttpUtil();
        inicisCancelRes = new Gson().fromJson(httpUtil.processHTTP(paramMap, inicisConfig.getInicisCancelUrl()), InicisCancelRes.class);

        log.info("============================================");
        log.info(new Gson().toJson(inicisCancelRes));
        log.info("============================================");

        try{
            if ("00".equals(inicisCancelRes.getResultCode())) {
                PgPaymentLog pgPaymentLog = new PgPaymentLog();
                pgPaymentLog.setCancelPgPayment(inicisCancelRes,paymentCancelRes);
                pgPaymentLogRepository.save(pgPaymentLog);

                // ???????????? ????????????
                Optional<Adv> advAuditOptional = advRepository.findById(advSeq);
                Adv adv = advAuditOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                //adv.setAuditCode(AdvAuditCodes.ADV_WAITING.getCode());
                adv.setProgressCode(AdvAuditCodes.PROGRESS_PAYMENT_CANCEL.getCode());
                advRepository.save(adv);

                // payment type:???????????? update
                Optional<Payment> paymentOptional = paymentRepository.findById(paymentCancelRes.getPaymentSeq());
                Payment payment = paymentOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                payment.setType(AdvAuditCodes.PROGRESS_PAYMENT_CANCEL.getCode());
                paymentRepository.save(payment);

                // ??????????????????
                PromotionCoupon promotionCoupon = promotionCouponRepository.findByMemberByCouponCode(memberId == null ? paymentCancelRes.getMemberId() : memberId, paymentCancelRes.getCouponNumber());
                //PromotionCoupon promotionCoupon = promotionCouponOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                if(promotionCoupon != null){
                    //promotionCoupon.setMemberId(null);
                    promotionCoupon.setUsable(false);
                    promotionCoupon.setUsingDate(null);
                    promotionCouponRepository.save(promotionCoupon);
                }

                try {
                    notification(paymentCancelRes, regId);
                }catch(Exception e){
                    log.info("============================[ paymentCancel ERROR START ]===========================");
                    log.info("inicisCancelRes {}",  "[paymentSeq : "+paymentCancelRes.getPaymentSeq()+"]", "inicis db insert error");
                    log.info("============================[ paymentCancel ERROR END ]===========================");
                }

            // ????????????????????? ?????? DB????????? ???????????? ??????
            }
            else if("01".equals(inicisCancelRes.getResultCode())){
                PgPaymentLog pgPaymentLog = new PgPaymentLog();
                pgPaymentLog.setCancelPgPayment(paymentCancelRes);
                pgPaymentLogRepository.save(pgPaymentLog);

                // ???????????? ????????????
                Optional<Adv> advAuditOptional = advRepository.findById(advSeq);
                Adv adv = advAuditOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                //adv.setAuditCode(AdvAuditCodes.ADV_WAITING.getCode());
                adv.setProgressCode(AdvAuditCodes.PROGRESS_PAYMENT_CANCEL.getCode());
                advRepository.save(adv);

                // payment type:???????????? update
                Optional<Payment> paymentOptional = paymentRepository.findById(paymentCancelRes.getPaymentSeq());
                Payment payment = paymentOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                payment.setType(AdvAuditCodes.PROGRESS_PAYMENT_CANCEL.getCode());
                paymentRepository.save(payment);

                // ??????????????????
                PromotionCoupon promotionCoupon = promotionCouponRepository.findByMemberByCouponCode(memberId == null ? paymentCancelRes.getMemberId() : memberId, paymentCancelRes.getCouponNumber());
                //PromotionCoupon promotionCoupon = promotionCouponOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
                if(promotionCoupon != null){
                    //promotionCoupon.setMemberId(null);
                    promotionCoupon.setUsable(false);
                    promotionCoupon.setUsingDate(null);
                    promotionCouponRepository.save(promotionCoupon);
                }

                inicisCancelRes = new InicisCancelRes();
                inicisCancelRes.setResultCode(pgPaymentLog.getResultCode());
                inicisCancelRes.setResultMsg(pgPaymentLog.getResultMsg());

            }
        }catch(Exception e){
            log.info("============================[ paymentCancel ERROR START ]===========================");
            log.info("inicisCancelRes {}",  "[paymentSeq : "+paymentCancelRes.getPaymentSeq()+"]", "inicis db insert error");
            log.info("============================[ paymentCancel ERROR END ]===========================");
        }

        return inicisCancelRes;
    }

    private void notification(PaymentCancelRes paymentCancelRes, String regId) throws ApiException {

        KakaoNotificationReq kakaoNotificationReq = sendKakaoNotification(paymentCancelRes, regId);

        NotificationGroup notificationGroup = new NotificationGroup();
        notificationGroup.setContents(kakaoNotificationReq.getTemplateText());
        notificationGroup.setTemplateId(KakaoNoticationCodes.PAYMENT_CANCEL.getCode());
        notificationGroup.setTargetGroupSeq(0L);
        notificationGroup.setAlrimTalk(true);
        notificationGroup.setAdvMessage(false);
        notificationGroup.setAuto(false);
//        notificationGroup.setSenderPhoneNumber(kakaoNotificationConfig.getReSendFromPhoneNum());
        notificationGroup.setDone(false);
        notificationGroup.setRegId(kakaoNotificationReq.getRegId());
        notificationGroup.setRegDate(LocalDateUtils.krNow());
        notificationGroupRepository.save(notificationGroup);
        em.persist(notificationGroup);

        Notification notification = new Notification();
        notification.setGroupSeq(0L);
        notification.setContents(kakaoNotificationReq.getText());
        notification.setSendTime("0");
        //notification.setSenderPhoneNumber(kakaoNotificationConfig.getReSendFromPhoneNum());
        notification.setAlrimTalk(true);
        notification.setRegDate(LocalDateUtils.krNow());
        notification.setKakaoTemplateId(kakaoNotificationReq.getTemplateCode());
        notification.setSenderId(regId);
        notification.setSenderName("?????? ?????????");
//    notification.setReceiveId(aesEncryptor.decrypt(encryptManagerId));
        notification.setReceiveId(paymentCancelRes.getMemberId());
        notification.setReceiveName(paymentCancelRes.getBuyerName());
        notification.setReceivePhoneNumber(paymentCancelRes.getBuyerTel()); //???????????????
        notification.setKakaoTemplateId(kakaoNotificationReq.getTemplateCode());

        notificationRepository.save(notification);
    }


    /**
     *
     *
     ??????AD ??????> #{????????????} ?????? ???????????? ????????? ????????????, ?????? ?????? ????????? ?????????????????????.

     ?????? ??????: #{?????? ??????}
     ?????????: #{?????? ??????}
     ?????? ??????: #{???????????????} ~ #{???????????????}
     ?????? ?????? ??????: #{?????? ??????}

     ?????? ???????????? ????????? ?????? ?????? ????????? ????????? ?????? ??? ????????????. (?????? 2~4??? ??????)

     #{???????????? url}
     *
     *
     *
     * */
    public KakaoNotificationReq sendKakaoNotification(PaymentCancelRes paymentCancelRes, String regId) throws ApiException {
        String templateCode = KakaoNoticationCodes.PAYMENT_CANCEL.getCode();

        Optional<KakaoTemplate> kakaoTemplateOptional = kakaoTemplateRepository.findById(templateCode);
        KakaoTemplate kakaoTemplate = kakaoTemplateOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        KakaoNotificationReq kakaoNotificationReq = new KakaoNotificationReq();
        kakaoNotificationReq.setText(
            kakaoTemplate.getTemplateContents()
                .replace("#{????????????}", paymentCancelRes.getBuyerName())
                .replace("#{?????? ??????}", paymentCancelRes.getAdvTitle())
                .replace("#{?????? ??????}", paymentCancelRes.getRegDate())
                .replace("#{???????????????}", paymentCancelRes.getStartDate())
                .replace("#{???????????????}", paymentCancelRes.getEndDate())
                .replace("#{?????? ??????}", paymentCancelRes.getPriceLabel())
                .replace("#{???????????? url}", "https://oddiad.co.kr")
        );
        kakaoNotificationReq.setReSend("Y");
        kakaoNotificationReq.setTo(paymentCancelRes.getBuyerTel());
        kakaoNotificationReq.setTemplateCode(templateCode);
        kakaoNotificationReq.setRegId(regId);
        kakaoNotificationReq.setTemplateText(kakaoTemplate.getTemplateContents());

        return kakaoNotificationReq;
    }
}
