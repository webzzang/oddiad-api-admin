package com.exflyer.oddi.admin.share.dto;

import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
  @ApiModelProperty(value = "코드", position = 1)
  private String code;

  @ApiModelProperty(value = "메세지", position = 2)
  private String message;

  @ApiModelProperty(value = "데이터", position = 3)
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public ApiResponseDto(ApiResponseCodes responseCodeEnums) {
    this.code = responseCodeEnums.getCode();
    this.message = responseCodeEnums.getMessage();
  }

  public ApiResponseDto(ApiResponseCodes responseCodeEnums, T responseData) {
    this.code = responseCodeEnums.getCode();
    this.message = responseCodeEnums.getMessage();
    this.data = responseData;
  }
}
