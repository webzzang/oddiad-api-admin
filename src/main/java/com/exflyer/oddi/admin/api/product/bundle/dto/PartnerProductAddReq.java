package com.exflyer.oddi.admin.api.product.bundle.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PartnerProductAddReq {

  @NotBlank
  private Long partner_seq;

}
