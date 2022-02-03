package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeModReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeRegReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * 공지 파일
 */
@Data
@Embeddable
@Builder
public class NoticeFilesPk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @ApiModelProperty("순번")
  @Column(name = "notice_seq", nullable = false)
  private Long noticeSeq;

  /**
   * 파일 순번
   */
  @ApiModelProperty("파일 순번")
  @Column(name = "file_seq", nullable = false)
  private Long fileSeq;

}
