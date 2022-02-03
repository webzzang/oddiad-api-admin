package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSearchReq extends PagingSearch {

  @ApiModelProperty(value = "상태코드", position = 1)
  private String statusCode;

  @ApiModelProperty(value = "아이디", position = 2)
  private String id;

  @ApiModelProperty(value = "이름", position = 3)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 4)
  @EncryptField
  private String phoneNumber;

  @ApiModelProperty(value = "이메일", position = 5)
  @EncryptField
  private String email;

  @ApiModelProperty(value = "제외시킬 회원 ID", position = 6)
  private List<String> excludeMemberId;

  @ApiModelProperty(value = "회원 - 휴면코드", hidden = true)
  private String excludeMemberCode;

}
