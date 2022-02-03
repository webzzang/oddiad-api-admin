package com.exflyer.oddi.admin.api.sales.payment.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchPaymentHistoryReq extends PagingSearch {

  @ApiModelProperty(value = "광고채널", position = 1)
  private String channelType;

  @ApiModelProperty(value = "고객 이메일", position = 2)
  private String buyerEmail;

  @ApiModelProperty(value = "결제 상품", position = 3)
  private String productName;

  @ApiModelProperty(value = "광고 이름", position = 4)
  private String advName;

  @ApiModelProperty(value = "결제일 시작일자(YYYYMMDD)", position = 5)
  private String searchRegStartDate;

  @ApiModelProperty(value = "결제일 종료일자(YYYYMMDD)", position = 6)
  private String searchRegEndDate;

  @ApiModelProperty(value = "광고시작일 시작일자(YYYYMMDD)", position = 7)
  private String searchAdvStStartDate;

  @ApiModelProperty(value = "광고시작일 종료일자(YYYYMMDD)", position = 8)
  private String searchAdvStEndDate;

  @ApiModelProperty(value = "광고종료일 시작일자(YYYYMMDD)", position = 9)
  private String searchAdvEnStartDate;

  @ApiModelProperty(value = "광고종료일 종료일자(YYYYMMDD)", position = 10)
  private String searchAdvEnEndDate;

  @ApiModelProperty(value = "결제 구분(결제 : false, 취소 : true)", position = 11)
  private Boolean paymentType;

  @ApiModelProperty(value = "결제 구분 그룹코드(결제, 취소)", hidden = true)
  private String incisPaymentType;

}
