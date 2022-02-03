package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PartnerReq {

  @ApiModelProperty(value = "파트너순번", position = 1)
  private Long partnerSeq;

  @ApiModelProperty(value = "파트너명", position = 2)
  private String partnerName;
}
