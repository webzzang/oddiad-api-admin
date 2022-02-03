package com.exflyer.oddi.admin.api.external.payment.dao;

import com.exflyer.oddi.admin.api.external.payment.dto.PaymentCancelRes;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMapper {
  PaymentCancelRes findMemberPaymentInfo(Long advSeq, String memberId, String paymentCompleteCode);
  PaymentCancelRes findPaymentInfo(Long paymentSeq, Long advSeq, String paymentCompleteCode);
}
