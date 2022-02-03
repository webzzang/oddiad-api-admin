package com.exflyer.oddi.admin.api.cs.partnerRequest.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PartnerRequestListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "이름", position = 1)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 2)
  private String phoneNumber;

  @ApiModelProperty(value = "지역", position = 3)
  private String location;

  @ApiModelProperty(value = "업종", position = 4)
  private String business;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 5)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 6)
  private String searchRegEndDate;

}
