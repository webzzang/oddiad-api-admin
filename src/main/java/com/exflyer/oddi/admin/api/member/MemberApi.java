package com.exflyer.oddi.admin.api.member;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.member.dto.MemberDetailResult;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchReq;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchResult;
import com.exflyer.oddi.admin.api.member.dto.MemberModReq;
import com.exflyer.oddi.admin.api.member.dto.MemberSearchCodes;
import com.exflyer.oddi.admin.api.member.service.MemberService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "회원 관리", protocols = "http")
@Slf4j
@RestController
public class MemberApi {

  @Autowired
  private MemberService memberService;

  @Autowired
  private AesEncryptor aesEncryptor;


  @ApiOperation(value = "회원 목록 조회 사용 코드", notes = "회원 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/member/search-codes")
  @MenuValidApi(menuCode = "CST001", operation = CrudOperation.READ)
  public ApiResponseDto<MemberSearchCodes> findCodeList() {
    MemberSearchCodes searchCode = memberService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "회원 목록 조회", notes = "회원 목록 조회 API 입니다. ")
  @GetMapping(path = "/member")
  @MenuValidApi(menuCode = "CST001", operation = CrudOperation.READ)
  public ApiResponseDto<PagingResult<MemberListSearchResult>> find(@Validated MemberListSearchReq memberListSearchReq) {
    PagingResult<MemberListSearchResult> pagingResult = memberService.findBySearchReq(memberListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "회원 상세 조회", notes = "회원 상세 조회 API 입니다. ")
  @GetMapping(path = "/member/{id}")
  @MenuValidApi(menuCode = "CST001", operation = CrudOperation.READ)
  public ApiResponseDto<MemberDetailResult> findDetail(@PathVariable String id) throws ApiException {
    MemberDetailResult memberDetailResult = memberService.findDetail(id);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, memberDetailResult);
  }

  @ApiOperation(value = "회원 정보 변경", notes = "회원 정보 변경 API 입니다. ")
  @PutMapping(path = "/member/{id}")
  @MenuValidApi(menuCode = "CST001", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable String id, @Validated @RequestBody MemberModReq memberModReq, AdminAuth adminAuth) throws ApiException {
    memberModReq.setId(id);
    memberModReq.setModId(adminAuth.getId());
    memberService.modify(memberModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

}
