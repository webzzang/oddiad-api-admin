package com.exflyer.oddi.admin.api.contents.banner.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BannerSearchCodes {

  @ApiModelProperty(value = "배너 사용여부", position = 1)
  private List<Code> bannerUsableCode;

  public BannerSearchCodes(List<Code> bannerUsableCode) {
    this.bannerUsableCode = bannerUsableCode;
  }
}
