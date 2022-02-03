package com.exflyer.oddi.admin.exceptions;


import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import lombok.Getter;

public class ApiException extends Exception {

  @Getter
  private ApiResponseCodes apiResponseCodes;


  public ApiException(ApiResponseCodes code){
    this.apiResponseCodes = code;
  }

}
