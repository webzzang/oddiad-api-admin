package com.exflyer.oddi.admin.api.cs.partnerRequest.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartnerRequestListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "이름", position = 2)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 3)
  private String phoneNumber;

  @ApiModelProperty(value = "지역", position = 4)
  private String location;

  @ApiModelProperty(value = "업종코드", position = 5)
  private String business;

  @ApiModelProperty(value = "업종코드명", position = 5)
  private String businessName;

  @ApiModelProperty(value = "내용", position = 6)
  private String contents;

  @ApiModelProperty(value = "문의사항", position = 7)
  private String memo;

  @ApiModelProperty(value = "확인여부", position = 8)
  private String confirm;

  @ApiModelProperty(value = "확인ID", position = 9)
  @DecryptField
  private String confirm_id;

  @ApiModelProperty(value = "등록일", position = 10)
  private String regDate;

}
