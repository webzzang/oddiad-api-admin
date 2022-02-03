package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Role;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Schema(description = "관리자 조회 코드 목록")
@Data
public class ManagerAccountSearchCodes {

  @ApiModelProperty(value = "상태코드", position = 0)
  private List<Code> statusCode;

  @ApiModelProperty(value = "역활 순번 리스트", position = 1)
  private List<Role> roleList;

  @ApiModelProperty(value = "페이징 사이즈", position = 2)
  private List<Integer> pagingSize;

  public ManagerAccountSearchCodes(List<Code> codeList, List<Role> roleList, List<Code> pagingSizeCode) {
    this.statusCode = codeList;
    this.roleList = roleList;
    List<Integer> pagingSize = new ArrayList<>();
    for (Code code : pagingSizeCode) {
      pagingSize.add(Integer.parseInt(code.getVal()));
    }
    this.pagingSize = pagingSize;
  }
}
