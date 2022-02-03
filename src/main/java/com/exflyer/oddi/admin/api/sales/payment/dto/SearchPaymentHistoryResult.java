package com.exflyer.oddi.admin.api.sales.payment.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchPaymentHistoryResult {

  @ApiModelProperty(value = "결제순번", position = 1)
  private String paymentSeq;

  @ApiModelProperty(value = "결제일시", position = 2)
  private String regDate;

  @ApiModelProperty(value = "결제타입", position = 3)
  private String pgPaymentType;

  @ApiModelProperty(value = "결제타입명", position = 4)
  private String pgPaymentTypeName;

  @DecryptField
  @ApiModelProperty(value = "회원 이메일", position = 5)
  private String buyerEmail;

  @ApiModelProperty(value = "채널타입", position = 6)
  private String channelType;

  @ApiModelProperty(value = "채널타입명", position = 7)
  private String channelTypeName;

  @ApiModelProperty(value = "결제상품명", position = 8)
  private String productName;

  @ApiModelProperty(value = "결제광고명", position = 9)
  private String advName;

  @ApiModelProperty(value = "광고시작일", position = 10)
  private String advStartDate;

  @ApiModelProperty(value = "광고종료일", position = 11)
  private String advEndDate;

  @ApiModelProperty(value = "결제금액", position = 12)
  private String price;

  @ApiModelProperty(value = "광고 순번", position = 13)
  private Long advSeq;

  @ApiModelProperty(value = "마지막 결제타입", position = 14)
  private String lastPaymentType;

}
