package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LiveStreamingVodList {

  /**
   * 유튜브 동영상id
   */
  @ApiModelProperty("유튜브 동영상id")
  private String youtubeId;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  private Long partnerSeq;

  /**
   * 파트너 명
   */
  @ApiModelProperty("파트너 명")
  private String partnerName;

  /**
   * 유튜브 동영상 등록일
   */
  @ApiModelProperty("유튜브 동영상 등록일")
  private String youtubeRegDate;

  /**
   * 유튜브 제목
   */
  @ApiModelProperty("유튜브 제목")
  private String youtubeTitle;

  /**
   * 유튜브 설명
   */
  @ApiModelProperty("유튜브 설명")
  private String youtubeDescription;

  /**
   * 유튜브 썸네일 정보
   */
  @ApiModelProperty("유튜브 썸네일 정보")
  private String youtubeThumbnails;

  /**
   * 유튜브 재생ID
   */
  @ApiModelProperty("유튜브 재생ID")
  private String youtubePlayId;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  private String regDate;

  /**
   * 유튜브 플레이 경로
   */
  @ApiModelProperty("유튜브 플레이 경로")
  private String youtubeUrl;

  /**
   * 노출여부
   */
  @ApiModelProperty("노출여부")
  private Boolean expo;

}
