package com.exflyer.oddi.admin.share;


import com.exflyer.oddi.admin.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AesEncryptor aesEncryptor;

  public static final String Header = "x-access-token";


  public String getTestToken(){
    String testId = "jisoon0923@gmail.com";
    return jwtService.createAccessToken(aesEncryptor.encrypt(testId)).getAccessToken();
  }

}
