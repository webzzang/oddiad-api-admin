package com.exflyer.oddi.admin.api.manager.role;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleCondition;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleConditionResult;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleDetail;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleModReq;
import com.exflyer.oddi.admin.api.manager.role.service.ManagerRoleService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "관리자 그룹", protocols = "http")
@Slf4j
@RestController
public class ManagerRoleApi {

  @Autowired
  private ManagerRoleService managerRoleService;

  @ApiOperation(value = "관리자 그룹 목록 조회", notes = "관리자 그룹 목록을 조회 하는 API 입니다.")
  @MenuValidApi(operation = CrudOperation.READ, menuCode = "MNG002")
  @GetMapping(value = "/manager/role", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto<PagingResult<RoleConditionResult>> findRoleOfPaging(RoleCondition condition) {
    PagingResult<RoleConditionResult> result = managerRoleService.findRoleOfPaging(condition);
    return new ApiResponseDto<>(ApiResponseCodes.SUCCESS, result);
  }

  @ApiOperation(value = "관리자 그룹 상세 조회", notes = "관리자 그룹 상세 조회 하는 API 입니다.")
  @MenuValidApi(operation = CrudOperation.READ, menuCode = "MNG002")
  @GetMapping(value = "/manager/role/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto<RoleDetail> findDetail(@PathVariable Long seq) throws ApiException {
    RoleDetail roleDetail = managerRoleService.findDetail(seq);
    return new ApiResponseDto<>(ApiResponseCodes.SUCCESS, roleDetail);
  }

  @ApiOperation(value = "관리자 그룹 초기 값", notes = "관리자 그룹 초기 값 API 입니다.")
  @MenuValidApi(operation = CrudOperation.READ, menuCode = "MNG002")
  @GetMapping(value = "/manager/role/init-info", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto<RoleDetail> findInitInfo() throws ApiException {
    RoleDetail roleDetail = managerRoleService.findInitInfo();

    return new ApiResponseDto<>(ApiResponseCodes.SUCCESS, roleDetail);
  }


  @ApiOperation(value = "관리자 그룹 생성", notes = "관리자 그룹 생성 API 입니다.")
  @MenuValidApi(operation = CrudOperation.CREATE, menuCode = "MNG002")
  @PostMapping(value = "/manager/role", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto add(@Validated @RequestBody RoleAddReq roleAddReq, AdminAuth adminAuth) throws ApiException {
    roleAddReq.setRegId(adminAuth.getId());
    managerRoleService.add(roleAddReq);
    return new ApiResponseDto<>(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 그룹 수정", notes = "관리자 그룹 수정 API 입니다. \n 삭제 후 등록 합니다.")
  @MenuValidApi(operation = CrudOperation.UPDATE, menuCode = "MNG002")
  @PutMapping(value = "/manager/role/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody RoleModReq roleModReq, AdminAuth adminAuth) throws ApiException {
    roleModReq.setRoleSeq(seq);
    roleModReq.setModId(adminAuth.getId());
    managerRoleService.modify(roleModReq);
    return new ApiResponseDto<>(ApiResponseCodes.SUCCESS);
  }
//
  @ApiOperation(value = "관리자 그룹 중복 체크", notes = "관리자 그룹 중복 체크 API 입니다.")
  @MenuValidApi(operation = CrudOperation.READ, menuCode = "MNG002")
  @GetMapping(value = "/manager/role/{name}/duplication", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto isDuplication(String roleName) {
    boolean duplication = managerRoleService.isDuplication(roleName);
    return duplication ? new ApiResponseDto(ApiResponseCodes.DUPLICATE)
      : new ApiResponseDto(ApiResponseCodes.NOT_DUPLICATE);
  }
}
