package com.exflyer.oddi.admin.jwt.dto;

import lombok.Data;

@Data
public class JwtResult {

  private String accessToken;

  private String refreshToken;

  public JwtResult(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
