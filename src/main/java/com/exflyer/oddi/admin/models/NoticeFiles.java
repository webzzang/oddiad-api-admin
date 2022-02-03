package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 공지 파일
 */
@Data
@Entity
@ApiModel("공지 파일")
@Table(name = "notice_files")
@IdClass(NoticeFiles.ID.class)
public class NoticeFiles  {

  private static final long serialVersionUID = 1L;

  /*@EmbeddedId
  private NoticeFilesPk noticeFilesPk;*/


  /**
   * 순번
   */
  @ApiModelProperty("순번")
  @Column(name = "notice_seq", nullable = false)
  @Id
  private Long noticeSeq;

  /**
   * 파일 순번
   */
  @ApiModelProperty("파일 순번")
  @Column(name = "file_seq", nullable = false)
  @Id
  private Long fileSeq;

  private LocalDateTime regDate;


  @Data
  static class ID implements Serializable {
    private Long noticeSeq;
    private Long fileSeq;
  }

}
