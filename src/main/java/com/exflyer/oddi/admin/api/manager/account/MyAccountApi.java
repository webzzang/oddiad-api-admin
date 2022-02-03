package com.exflyer.oddi.admin.api.manager.account;

import com.exflyer.oddi.admin.annotaions.LoginNeedApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountDetail;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModPasswordReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModReq;
import com.exflyer.oddi.admin.api.manager.account.service.ManagerMyAccountService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.manager.menu.dto.ManagerMenu;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "관리자 내 계정", protocols = "http")
@Slf4j
@RestController
public class MyAccountApi {

  @Autowired
  private ManagerMyAccountService managerMyAccountService;

  @ApiOperation(value = "관리자 계정 메뉴 조회", notes = "관리자 계정 메뉴 조회 API ")
  @GetMapping(path = "/my/menu")
  @LoginNeedApi
  public ApiResponseDto<List<ManagerMenu>> findManagerMenu(AdminAuth adminAuth) throws ApiException {
    List<ManagerMenu> managerMenuList = managerMyAccountService.findMenu(adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, managerMenuList);
  }

  @ApiOperation(value = "관리자 계정 상세 조회", notes = "관리자 계정 상세 조회 API ")
  @GetMapping(path = "/my")
  @LoginNeedApi
  @OddiEncrypt
  public ApiResponseDto<ManagerAccountDetail> findDetail(AdminAuth adminAuth) throws ApiException {
    ManagerAccountDetail managerDetailResult = managerMyAccountService.findDetail(adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, managerDetailResult);
  }

  @ApiOperation(value = "관리자 내 계정 수정", notes = "관리자 내 계정 수정 API ")
  @PutMapping(path = "/my")
  @LoginNeedApi
  @OddiEncrypt
  public ApiResponseDto modify(@Validated @RequestBody ManagerMyAccountModReq managerMyAccountModReq,  AdminAuth adminAuth) throws ApiException {
    managerMyAccountModReq.setModId(adminAuth.getId());
    managerMyAccountModReq.setId(adminAuth.getId());
    managerMyAccountModReq.setModDate(LocalDateUtils.krNow());
    managerMyAccountService.modify(managerMyAccountModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 내 계정 비밀번호 변경", notes = "관리자 내 계정 비밀번호 변경 API ")
  @PutMapping(path = "/my/password")
  @LoginNeedApi
  @OddiEncrypt
  public ApiResponseDto modify(@Validated @RequestBody ManagerMyAccountModPasswordReq modPasswordReq,  AdminAuth adminAuth) throws ApiException {
    modPasswordReq.setModId(adminAuth.getId());
    modPasswordReq.setId(adminAuth.getId());
    modPasswordReq.setModDate(LocalDateUtils.krNow());
    managerMyAccountService.modifyPassword(modPasswordReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 내 계정 비밀번호 연장", notes = "관리자 내 계정 비밀번호 연장 API ")
  @PutMapping(path = "/my/password/extension")
  @LoginNeedApi
  public ApiResponseDto continuePassword(AdminAuth adminAuth) throws ApiException {
    managerMyAccountService.continuePassword(adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }
}
