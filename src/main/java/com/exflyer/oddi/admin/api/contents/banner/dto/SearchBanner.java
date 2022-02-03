package com.exflyer.oddi.admin.api.contents.banner.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchBanner extends PagingSearch {

  @ApiModelProperty(value = "배너이름", position = 1)
  private String bannerName;

  @ApiModelProperty(value = "from 노출시작일", position = 2)
  private String searchStExpoStartDate;

  @ApiModelProperty(value = "to 노출시작일", position = 3)
  private String searchStExpoEndDate;

  @ApiModelProperty(value = "from 노출종료일", position = 4)
  private String searchEnExpoStartDate;

  @ApiModelProperty(value = "to 노출종료일", position = 5)
  private String searchEnExpoEndDate;

  @ApiModelProperty(value = "from 등록일", position = 6)
  private String searchRegStartDate;

  @ApiModelProperty(value = "to 등록일", position = 7)
  private String searchRegEndDate;

  @ApiModelProperty(value = "배너위치타입(상단, 하단, 우측)", hidden = true)
  private String bannerLocationType;

  @ApiModelProperty(value = "배너타입(web, device)", hidden = true)
  private String bannerType;

}
