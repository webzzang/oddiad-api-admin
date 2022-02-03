package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 요청
 */
@Data
@Entity
@ApiModel("파트너 요청")
@Table(name = "partner_request")
public class PartnerRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @ApiModelProperty("순번")
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 이름
   */
  @ApiModelProperty("이름")
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 전화번호
   */
  @ApiModelProperty("전화번호")
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  /**
   * 지역
   */
  @ApiModelProperty("지역")
  @Column(name = "location")
  private String location;

  /**
   * 업종
   */
  @ApiModelProperty("업종")
  @Column(name = "business")
  private String business;

  /**
   * 내용
   */
  @ApiModelProperty("내용")
  @Column(name = "contents")
  private String contents;

  /**
   * 문의 사항
   */
  @Column(name = "memo")
  @ApiModelProperty("문의 사항")
  private String memo;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date")
  @ApiModelProperty("생성 날짜")
  private LocalDateTime regDate;

  /**
   * 확인 여부
   */
  @Column(name = "confirm")
  @ApiModelProperty("확인 여부")
  private Integer confirm;

  /**
   * 확인 id
   */
  @ApiModelProperty("확인 id")
  @Column(name = "confirm_id")
  private String confirmId;

}
