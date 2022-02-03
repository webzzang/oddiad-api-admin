package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 메뉴
 */
@Data
@Entity
@ApiModel("메뉴")
@Table(name = "menu")
public class Menu implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @ApiModelProperty("id")
  @Column(name = "id", nullable = false)
  private String id;

  /**
   * 그룹 id
   */
  @Column(name = "group_id")
  @ApiModelProperty("그룹 id")
  private String groupId;

  /**
   * 메뉴 이름
   */
  @ApiModelProperty("메뉴 이름")
  @Column(name = "menu_name")
  private String menuName;

  /**
   * 아이콘
   */
  @Column(name = "icon")
  @ApiModelProperty("아이콘")
  private String icon;

  /**
   * 라우터 링크
   */
  @ApiModelProperty("라우터 링크")
  @Column(name = "router_link")
  private String routerLink;

  /**
   * 사용 여부
   */
  @Column(name = "usable")
  @ApiModelProperty("사용 여부")
  private Boolean usable = true;

  /**
   * 정렬 번호
   */
  @Column(name = "ordering")
  @ApiModelProperty("정렬 번호")
  private Integer ordering;

}
