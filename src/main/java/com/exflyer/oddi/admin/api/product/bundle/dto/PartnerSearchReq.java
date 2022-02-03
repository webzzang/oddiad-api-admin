package com.exflyer.oddi.admin.api.product.bundle.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartnerSearchReq extends PagingSearch {

  @ApiModelProperty(value = "조회되어있는 파트너순번(조회시 이 파트너순번은 제외 시키기 위함)", position = 1)
  private Long[] seq;


}
