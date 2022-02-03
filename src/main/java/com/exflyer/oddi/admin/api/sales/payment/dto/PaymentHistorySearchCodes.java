package com.exflyer.oddi.admin.api.sales.payment.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentHistorySearchCodes {

  @ApiModelProperty(value = "채널구분코드", position = 1)
  private List<Code> channelTypeCode;

  @ApiModelProperty(value = "결제구분코드", position = 2)
  private List<Code> incisPaymentTypeCode;

  public PaymentHistorySearchCodes(List<Code> channelTypeCode, List<Code> incisPaymentTypeCode) {
    this.channelTypeCode = channelTypeCode;
    this.incisPaymentTypeCode = incisPaymentTypeCode;
  }
}
