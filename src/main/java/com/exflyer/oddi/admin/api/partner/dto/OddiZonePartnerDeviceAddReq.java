package com.exflyer.oddi.admin.api.partner.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OddiZonePartnerDeviceAddReq {

  @NotBlank
  private String name;

  @NotBlank
  private String deviceId;

}
