package com.exflyer.oddi.admin.api.external.geography.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KakaoLocalConfig {

  @Value("${kakao.local-host}")
  private String host;

  @Value("${kakao.rest-key}")
  private String restApiKey;

}
