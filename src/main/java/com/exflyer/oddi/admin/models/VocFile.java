package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * voc 파일
 */
@Data
@Entity
@ApiModel("voc 파일")
@Table(name = "voc_file")
public class VocFile implements Serializable {

  private static final long serialVersionUID = 1L;


  @EmbeddedId
  private VocFilePk vocFilePk;

  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

}
