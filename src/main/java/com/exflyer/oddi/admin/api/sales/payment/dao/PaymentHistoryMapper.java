package com.exflyer.oddi.admin.api.sales.payment.dao;

import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryReq;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentHistoryMapper {
  Page<SearchPaymentHistoryResult> findPaymentHistory(SearchPaymentHistoryReq searchReq);
  Integer findPaymentHistoryCount(SearchPaymentHistoryReq searchReq);
}
