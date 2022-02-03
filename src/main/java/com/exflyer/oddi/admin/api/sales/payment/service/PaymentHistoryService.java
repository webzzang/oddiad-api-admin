package com.exflyer.oddi.admin.api.sales.payment.service;

import com.exflyer.oddi.admin.api.external.payment.PaymentService;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelRes;
import com.exflyer.oddi.admin.api.sales.payment.code.PaymentHistoryCodes;
import com.exflyer.oddi.admin.api.sales.payment.dao.PaymentHistoryMapper;
import com.exflyer.oddi.admin.api.sales.payment.dto.PaymentHistorySearchCodes;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryReq;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryResult;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentHistoryService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private PaymentHistoryMapper paymentHistoryMapper;
    @Autowired
    private PaymentService paymentService;

    public PaymentHistorySearchCodes findCodeForSearching() {

        List<Code> channelTypeCode = codeRepository.findByGroupCodeAndUsable("PTT", true);
        List<Code> incisPaymentTypeCode = codeRepository.findByGroupCodeAndUsable("IPT", true);
        return new PaymentHistorySearchCodes(channelTypeCode, incisPaymentTypeCode);
    }

    public PagingResult<SearchPaymentHistoryResult> findPaymentHistory(SearchPaymentHistoryReq searchReq) {
        PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
        searchReq.setIncisPaymentType(PaymentHistoryCodes.INCIS_PAYMENT_TYPE.getCode());
        searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "p.seq desc"));
        PageHelper.orderBy(searchReq.getOrderBy());
        Page<SearchPaymentHistoryResult> result = paymentHistoryMapper.findPaymentHistory(searchReq);
        return PagingResult.createResultDto(result);
    }

    public Integer findPaymentHistoryCount(SearchPaymentHistoryReq searchReq) {
        return paymentHistoryMapper.findPaymentHistoryCount(searchReq);
    }

    public InicisCancelRes paymentCancel(Long paymentSeq, Long seq, String memberId, String regId) throws Exception {
        return paymentService.paymentCancel(paymentSeq, seq, memberId, regId);
    }
}
