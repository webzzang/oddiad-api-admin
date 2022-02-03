package com.exflyer.oddi.admin.api.adv.make.dto;

import com.exflyer.oddi.admin.models.Adv;
import com.exflyer.oddi.admin.models.AdvFile;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class AdvMakeModReq {

  @ApiModelProperty(hidden = true)
  private Long seq;

  @ApiModelProperty(hidden = true)
  private String deviceId;

  @ApiModelProperty(value = "화면분할코드", position = 1)
  private String displayDiv;

  @ApiModelProperty(value = "측면(오른쪽)화면코드", position = 2)
  private String sideContentsType;

  @ApiModelProperty(value = "하단(아래쪽)화면코드", position = 3)
  private String bottomContentsType;

  @ApiModelProperty(value = "등록 파일리스트(adv_file)", position = 4)
  private List<AdvFile> advFileList;

  @ApiModelProperty(value = "광고 리스트", position = 5)
  private List<AdvMakeExpoReq> advExpoList;

  @ApiModelProperty(value = "기본광고 노출여부", position = 6)
  private Boolean defaultAdvExpo;

  @ApiModelProperty(value = "채널타입", position = 7, required = true)
  private String channelType;

  @ApiModelProperty(value = "생성 ID", hidden = true)
  private String regId;

  @ApiModelProperty(value = "생성 일자", hidden = true)
  private LocalDateTime regDate;

}
