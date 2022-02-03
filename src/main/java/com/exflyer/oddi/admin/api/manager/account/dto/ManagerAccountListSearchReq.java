package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ManagerAccountListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "아이디", position = 0)
  @EncryptField
  private String id;

  @ApiModelProperty(value = "상태코드", position = 0)
  private String statusCode;

  @ApiModelProperty(value = "소속그룹 순번", position = 1)
  private Long roleSeq;

  @ApiModelProperty(value = "이름", position = 2)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 3)
  @EncryptField
  private String phoneNumber;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 4)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 5)
  private String searchRegEndDate;


  @ApiModelProperty(value = "로그인 일시 검색 시작 날짜(YYYYMMDD)", position = 4)
  private String searchLoginStartDate;

  @ApiModelProperty(value = "로그인 일시 검색 종료 날짜(YYYYMMDD)", position = 5)
  private String searchLoginEndDate;

}
