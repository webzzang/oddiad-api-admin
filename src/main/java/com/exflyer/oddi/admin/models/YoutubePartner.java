package com.exflyer.oddi.admin.models;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유튜브 파트너 매핑
 */
@Data
@Entity
@ApiModel("유튜브 파트너 매핑")
@Table(name = "youtube_partner")
public class YoutubePartner implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 유튜브 동영상id
   */
  @Id
  @ApiModelProperty("유튜브 동영상id")
  @Column(name = "youtube_id", nullable = false)
  private String youtubeId;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq")
  private Long partnerSeq;

}
