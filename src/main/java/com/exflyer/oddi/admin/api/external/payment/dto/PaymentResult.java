package com.exflyer.oddi.admin.api.external.payment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PaymentResult {

    @ApiModelProperty(value = "결제 순번", position = 1)
    private Long paymentSeq;

    @ApiModelProperty(value = "광고 순번", position = 2)
    private Long advSeq;

    @ApiModelProperty(value = "PG 인증결과 순번", position = 3)
    private Long pgAccreditLogSeq;

    @ApiModelProperty(value = "회원 ID", position = 4)
    private String memberId;

    @ApiModelProperty(value = "쿠폰번호", position = 5)
    private String couponNumber;

    @ApiModelProperty(value = "상점아이디", position = 6)
    private String mid;

    @ApiModelProperty(value = "지불수단", position = 7)
    private String payMethod;

    @ApiModelProperty(value = "거래번호", position = 8)
    private String tid;

    @ApiModelProperty(value = "결제금액", position = 9)
    private Integer price;

    @ApiModelProperty(value = "구매자명", position = 10)
    private String buyerName;

    @ApiModelProperty(value = "구매자 이메일", position = 11)
    private String buyerEmail;

    @ApiModelProperty(value = "승인번호", position = 12)
    private String applNum;

    @ApiModelProperty(value = "전문위변조 HASH", position = 13)
    private String hashData;

}
