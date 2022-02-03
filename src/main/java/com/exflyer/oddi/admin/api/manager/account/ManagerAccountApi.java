package com.exflyer.oddi.admin.api.manager.account;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountDetail;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountListSearchReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountModReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountRegReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountSearchCodes;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccounts;
import com.exflyer.oddi.admin.api.manager.account.service.ManagerAccountService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
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

@Api(tags = "관리자 계정", protocols = "http")
@Slf4j
@RestController
public class ManagerAccountApi {

  @Autowired
  private ManagerAccountService managerAccountService;

  @Autowired
  private AesEncryptor aesEncryptor;

  @ApiOperation(value = "관리자 계정 코드 조회", notes = "관리자 계정 목록 조회시 사용하는 코드 목록 API 입니다. ")
  @GetMapping(path = "/account/codes")
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.READ)
  public ApiResponseDto<ManagerAccountSearchCodes> findCodeList() {
    ManagerAccountSearchCodes searchCode = managerAccountService.findSearchCode();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "관리자 계정 목록", notes = "관리자 계정 목록 API 입니다. ")
  @GetMapping(path = "/account")
//  @OddiEncrypt
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.READ)
  public ApiResponseDto<PagingResult<ManagerAccounts>> findBySearchReq(
    @Validated ManagerAccountListSearchReq searchReq) {
    PagingResult<ManagerAccounts> pagingResult = managerAccountService.findBySearchReq(searchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "관리자 계정 상세", notes = "관리자 계정 상세 조회 API 입니다. ")
  @GetMapping(path = "/account/{id}")
  @OddiEncrypt
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.READ)
  public ApiResponseDto<ManagerAccountDetail> findDetailById(@PathVariable String id) throws ApiException {
    String encryptString = aesEncryptor.encrypt(id);
    ManagerAccountDetail managerAccountDetail = managerAccountService.findDetailById(encryptString);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, managerAccountDetail);
  }

  @ApiOperation(value = "관리자 계정 수정", notes = "관리자 계정 수정 API 입니다.")
  @PutMapping(path = "/account/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.UPDATE)
  @OddiEncrypt
  public ApiResponseDto modifySearchReq(@PathVariable String id,
    @Validated @RequestBody ManagerAccountModReq managerAccountModReq, AdminAuth adminAuth) throws ApiException {
    String encryptString = aesEncryptor.encrypt(id);
    managerAccountModReq.setId(encryptString);
    managerAccountModReq.setModId(adminAuth.getId());
    managerAccountService.modify(managerAccountModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 계정 비밀번호 초기화", notes = "관리자 계정 비밀번호 초기화 API 입니다.")
  @PutMapping(path = "/account/{id}/password-init", produces = MediaType.APPLICATION_JSON_VALUE)
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.UPDATE)
  public ApiResponseDto findBySearchReq(@PathVariable String id, AdminAuth adminAuth)
      throws ApiException {
    String encryptString = aesEncryptor.encrypt(id);
    managerAccountService.initPassword(encryptString, adminAuth);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 계정 중복 체크", notes = "관리자 계정 중복 체크 API 입니다.")
  @GetMapping(path = "/account/{id}/duplication", produces = MediaType.APPLICATION_JSON_VALUE)
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.READ)
  public ApiResponseDto add(@PathVariable String id) {
    boolean duplication = managerAccountService.isDuplication(aesEncryptor.encrypt(id));
    return duplication ? new ApiResponseDto(ApiResponseCodes.DUPLICATE) : new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "관리자 등록", notes = "관리자 계정 등록 API 입니다.")
  @PostMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
  @OddiEncrypt
  @MenuValidApi(menuCode = "MNG001", operation = CrudOperation.CREATE)
  public ApiResponseDto add(@Validated @RequestBody ManagerAccountRegReq managerAccountRegReq, AdminAuth adminAuth)
    throws ApiException {
    managerAccountRegReq.setRegManagerId(adminAuth.getId());
    managerAccountService.add(managerAccountRegReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }
}
