package com.exflyer.oddi.admin.api.external.payment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PaymentCancelReq {


  @ApiModelProperty(value = "결제순번", hidden = true)
  private Long paymentSeq;

  @ApiModelProperty(value = "광고순번", hidden = true)
  private Long advSeq;

  @ApiModelProperty(value = "결제종류")
  private String type;

  @ApiModelProperty(value = "심사진행코드")
  private String auditCode;

  public PaymentCancelReq(Long paymentSeq, Long advSeq) {
    this.paymentSeq = paymentSeq;
    this.advSeq = advSeq;
    this.type = "PGT002";
    this.auditCode = "ADT002";
  }

}
