package com.exflyer.oddi.admin.api.manager.auth.dto;

import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.jwt.dto.JwtResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CertificationResult {

  @ApiModelProperty(value = "accessToken", position = 0)
  private String accessToken;

  @ApiModelProperty(value = "refressToken", position = 1)
  private String refreshToken;

  @JsonIgnore
  private ApiResponseCodes responseCode;

  public CertificationResult(JwtResult jwtResult, ApiResponseCodes apiResponseCodes) {
    this.accessToken = jwtResult.getAccessToken();
    this.refreshToken = jwtResult.getRefreshToken();
    this.responseCode = apiResponseCodes;
  }
}
