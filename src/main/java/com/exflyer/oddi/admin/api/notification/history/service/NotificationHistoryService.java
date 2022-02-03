package com.exflyer.oddi.admin.api.notification.history.service;

import com.exflyer.oddi.admin.api.notification.history.dao.NotificationHistoryMapper;
import com.exflyer.oddi.admin.api.notification.history.dto.NotificationHistorySearchCodes;
import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryReq;
import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryResult;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Notification;
import com.exflyer.oddi.admin.models.NotificationGroup;
import com.exflyer.oddi.admin.models.NotificationHistory;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.NotificationGroupRepository;
import com.exflyer.oddi.admin.repository.NotificationHistoryRepository;
import com.exflyer.oddi.admin.repository.NotificationRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.exflyer.oddi.admin.exceptions.ApiException;
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
public class NotificationHistoryService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private NotificationHistoryMapper notificationHistoryMapper;
    @Autowired
    private NotificationHistoryRepository notificationHistoryRepository;
    @Autowired
    private NotificationGroupRepository notificationGroupRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @PersistenceContext
    EntityManager em;

    public NotificationHistorySearchCodes findCodeForSearching() {
        List<Code> targetTypeCode = codeRepository.findByGroupCodeAndUsable("NTC", true);
        List<Code> messageSendTypeCode = codeRepository.findByGroupCodeAndUsable("NAT", true);
        return new NotificationHistorySearchCodes(targetTypeCode, messageSendTypeCode);
    }

    public PagingResult<SearchHistoryResult> findByHistorySearchReq(SearchHistoryReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        PageHelper.orderBy(searchReq.getOrderBy());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "nh.seq desc"));
        Page<SearchHistoryResult> result = notificationHistoryMapper.findByHistorySearchReq(searchReq);
        return PagingResult.createResultDto(result);
    }

    @Transactional(rollbackFor = {Exception.class, Error.class})
    public void reSend(Long seq) throws ApiException {
        Optional<NotificationHistory> notificationHistoryOptional = notificationHistoryRepository.findById(seq);
        NotificationHistory notificationHistory = notificationHistoryOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        // group는 다시 등록할 필요가 없음 - 주석
        /*Optional<NotificationGroup> notificationGroupOptional = notificationGroupRepository.findById(notificationHistory.getGroupSeq());
        NotificationGroup notificationGroup = notificationGroupOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

        notificationGroup.setContents(notificationHistory.getContents());
        notificationGroup.setAdvMessage(notificationGroup.getAdvMessage());
        notificationGroup.setAlrimTalk(notificationGroup.getAlrimTalk());
        notificationGroup.setAuto(notificationGroup.getAuto());
        notificationGroup.setDone(false);
        notificationGroup.setRegDate(LocalDateUtils.krNow());
        notificationGroup.setSenderPhoneNumber(notificationHistory.getSenderPhoneNumber());
        notificationGroup.setSendTime(notificationHistory.getSendTime());
        notificationGroup.setTarget_create_done(false);
        notificationGroupRepository.save(notificationGroup);
        em.persist(notificationGroup);*/

        Notification notification = new Notification();
        notification.setGroupSeq(notificationHistory.getGroupSeq());
        notification.setReceiveName(notificationHistory.getReceiveName());
        notification.setReceivePhoneNumber(notificationHistory.getReceivePhoneNumber());
        notification.setContents(notificationHistory.getContents());
        notification.setSendTime("0");
        notification.setSenderId(notificationHistory.getSenderId());
        notification.setSenderName(notificationHistory.getSenderName());
        notification.setSenderPhoneNumber(notificationHistory.getSenderPhoneNumber());
        notification.setAlrimTalk(false);
        notification.setRegDate(LocalDateUtils.krNow());
        notificationRepository.save(notification);
    }
}
