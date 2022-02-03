package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 기본광고 파일 매핑
 */
@Data
@Entity
@ApiModel("파트너 기본광고 파일 매핑")
@Table(name = "partner_default_adv_files")
@IdClass(PartnerDefaultAdvFiles.ID.class)
public class PartnerDefaultAdvFiles implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 종류(오디존, 지하철)
   */
  @Id
  @Column(name = "channel_type")
  @ApiModelProperty("종류(오디존, 지하철)")
  private String channelType;

  /**
   * 기본광고 파일순번
   */
  @Id
  @Column(name = "default_adv_file_seq")
  @ApiModelProperty("기본광고 파일순번")
  private Long defaultAdvFileSeq;

  /**
   * 기본광고 파일타입
   */
  @Column(name = "default_adv_type")
  @ApiModelProperty("기본광고 파일타입")
  private String defaultAdvType;

  /**
   * 파일 노출순서
   */
  @Column(name = "view_order")
  @ApiModelProperty("파일 노출순서")
  private Integer viewOrder;

  @Data
  static class ID implements Serializable {
    private String channelType;
    private Long defaultAdvFileSeq;
  }


}
