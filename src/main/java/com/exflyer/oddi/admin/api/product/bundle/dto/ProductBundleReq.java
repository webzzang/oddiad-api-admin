package com.exflyer.oddi.admin.api.product.bundle.dto;

import com.exflyer.oddi.admin.models.ProductFile;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ProductBundleReq {

  @ApiModelProperty(value = "순번", hidden = true)
  private Long seq;

  @ApiModelProperty(value = "파트너 순번", position = 1)
  private List<Long> partnerSeq;

  @ApiModelProperty(value = "상품 이름", position = 2)
  private String name;

  @ApiModelProperty(value = "상품 가격", position = 3)
  private Integer price;

  @ApiModelProperty(value = "소개글", position = 4)
  private String description;

  @ApiModelProperty(value = "새로운 파일리스트(adv_file)", position = 5)
  private List<ProductFile> productFileList;

  @ApiModelProperty(value = "광고 사례 노출", position = 6)
  private Boolean advCaseExpo = false;

  @ApiModelProperty(value = "운영 여부", position = 7)
  private Boolean operation = false;

  @ApiModelProperty(value = "메모", position = 8)
  private String memo;

  @ApiModelProperty(value = "배지코드", position = 9)
  private String badgeCode;

  @ApiModelProperty(value = "생성 날짜", hidden = true)
  private LocalDateTime regDate;

  @ApiModelProperty(value = "생성 id", hidden = true)
  private String regId;

  @ApiModelProperty(value = "변경 id", hidden = true)
  private String modId;

  @ApiModelProperty(value = "최종수정일시", hidden = true)
  private LocalDateTime modDate;

}
