package com.exflyer.oddi.admin.api.manager.auth.api;

import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.manager.auth.dto.CertificationResult;
import com.exflyer.oddi.admin.api.manager.auth.dto.SignInReq;
import com.exflyer.oddi.admin.api.manager.auth.service.SignInService;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "로그인", protocols = "http")
@Slf4j
@RestController
public class SignInApi {

  @Autowired
  private SignInService signInService;

  @ApiOperation(value = "로그인", notes = "관리자 로그인 API 입니다. ")
  @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
  @OddiEncrypt
  public ApiResponseDto signIn(@Validated @RequestBody SignInReq signInReq) throws ApiException{
    CertificationResult certificationResult = signInService.signIn(signInReq);
    return new ApiResponseDto(certificationResult.getResponseCode(), certificationResult);
  }



}
