package com.exflyer.oddi.admin.api.product.promotion.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PromotionAddReq {

  @NotBlank
  private Long partner_seq;

}
