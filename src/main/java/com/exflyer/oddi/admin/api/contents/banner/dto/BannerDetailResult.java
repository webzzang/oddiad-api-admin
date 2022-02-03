package com.exflyer.oddi.admin.api.contents.banner.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Data;

@Data
public class BannerDetailResult {

  @ApiModelProperty("순번")
  private Long seq;

  @ApiModelProperty("이름")
  private String name;

  @ApiModelProperty("종류(web, device)")
  private String type;

  @ApiModelProperty("위치 코드(우측, 상단, 하단, 팝업)")
  private String locationCode;

  @ApiModelProperty("설명")
  private String description;

  @ApiModelProperty("사용 여부")
  private Boolean usable;

  @ApiModelProperty("노출 시작 날짜")
  private String expoStartDate;

  @ApiModelProperty("노출 종료 날짜")
  private String expoEndDate;

  @Column(name = "memo")
  private String memo;

  @ApiModelProperty("생성 날짜")
  private String regDate;

  @ApiModelProperty("생성 id")
  private String regId;

  @Column(name = "mod_date")
  private String modDate;

  @Column(name = "mod_id")
  private String modId;

  @ApiModelProperty("라우터 링크")
  private String routerLink;

  @ApiModelProperty("버튼 이름")
  private String buttonName;

  @ApiModelProperty("파일 순번")
  private Long fileSeq;

  @ApiModelProperty("파일명")
  private String fileName;

  @ApiModelProperty("파일패스")
  private String filePath;

}
