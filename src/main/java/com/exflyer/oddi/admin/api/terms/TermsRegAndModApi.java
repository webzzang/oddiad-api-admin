package com.exflyer.oddi.admin.api.terms;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.terms.codes.TermsTypeCodes;
import com.exflyer.oddi.admin.api.terms.dto.TermsAddReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsModReq;
import com.exflyer.oddi.admin.api.terms.service.TermsService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "약관 관리", protocols = "http")
@Slf4j
@RestController
public class TermsRegAndModApi {

  @Autowired
  private TermsService termsService;


  @ApiOperation(value = "서비스 이용 약관 등록", notes = "서비스 이용 약관 등록 API 입니다. ")
  @PostMapping(path = "/terms/service")
  @MenuValidApi(menuCode = "TRM001", operation = CrudOperation.CREATE)
  public ApiResponseDto addServiceTerms(@Validated @RequestBody TermsAddReq termsAddReq, AdminAuth adminAuth)
    throws ApiException {
    termsAddReq.setTypeCode(TermsTypeCodes.SERVICE.getCode());
    termsAddReq.setRegId(adminAuth.getId());
    termsService.addTerms(termsAddReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 처리 약관 등록", notes = "개인 정보 처리 약관 등록 API 입니다. ")
  @PostMapping(path = "/terms/privacy")
  @MenuValidApi(menuCode = "TRM002", operation = CrudOperation.CREATE)
  public ApiResponseDto addPrivacyTerms(@Validated @RequestBody TermsAddReq termsAddReq, AdminAuth adminAuth)
    throws ApiException {
    termsAddReq.setTypeCode(TermsTypeCodes.PRIVACY.getCode());
    termsAddReq.setRegId(adminAuth.getId());
    termsService.addTerms(termsAddReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "제 3자 정보 제공 약관 등록", notes = "제 3자 정보 제공 약관 등록 API 입니다. ")
  @PostMapping(path = "/terms/provide")
  @MenuValidApi(menuCode = "TRM003", operation = CrudOperation.CREATE)
  public ApiResponseDto addProvideTerms(@Validated @RequestBody TermsAddReq termsAddReq, AdminAuth adminAuth)
    throws ApiException {
    termsAddReq.setTypeCode(TermsTypeCodes.PROVIDE.getCode());
    termsAddReq.setRegId(adminAuth.getId());
    termsService.addTerms(termsAddReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 수집 약관 등록", notes = "개인 정보 수집 약관 등록 API 입니다. ")
  @PostMapping(path = "/terms/marketing")
  @MenuValidApi(menuCode = "TRM004", operation = CrudOperation.CREATE)
  public ApiResponseDto addMarketingTerms(@Validated @RequestBody TermsAddReq termsAddReq, AdminAuth adminAuth)
    throws ApiException {
    termsAddReq.setTypeCode(TermsTypeCodes.MARKETING.getCode());
    termsAddReq.setRegId(adminAuth.getId());
    termsService.addTerms(termsAddReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고 신청 약관 등록", notes = "광고 신청 등록 API 입니다. ")
  @PostMapping(path = "/terms/adv")
  @MenuValidApi(menuCode = "TRM005", operation = CrudOperation.CREATE)
  public ApiResponseDto addAdvTerms(@Validated @RequestBody TermsAddReq termsAddReq, AdminAuth adminAuth)
    throws ApiException {
    termsAddReq.setTypeCode(TermsTypeCodes.ADV.getCode());
    termsAddReq.setRegId(adminAuth.getId());
    termsService.addTerms(termsAddReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "서비스 이용 약관 수정", notes = "서비스 이용 약관 수정 API 입니다. ")
  @PutMapping(path = "/terms/service/{seq}")
  @MenuValidApi(menuCode = "TRM001", operation = CrudOperation.UPDATE)
  public ApiResponseDto modifyServiceTerms(@PathVariable Long seq,  @Validated @RequestBody TermsModReq termsModReq, AdminAuth adminAuth)
    throws ApiException {
    termsModReq.setSeq(seq);
    termsModReq.setTypeCode(TermsTypeCodes.SERVICE.getCode());
    termsModReq.setModId(adminAuth.getId());
    termsService.modTerms(termsModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 처리 약관 수정", notes = "개인 정보 처리 약관 수정 API 입니다. ")
  @PutMapping(path = "/terms/privacy/{seq}")
  @MenuValidApi(menuCode = "TRM002", operation = CrudOperation.UPDATE)
  public ApiResponseDto modPrivacyTerms(@PathVariable Long seq,  @Validated @RequestBody TermsModReq termsModReq, AdminAuth adminAuth)
    throws ApiException {
    termsModReq.setSeq(seq);
    termsModReq.setTypeCode(TermsTypeCodes.PRIVACY.getCode());
    termsModReq.setModId(adminAuth.getId());
    termsService.modTerms(termsModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "제 3자 정보 제공 약관 수정", notes = "제 3자 정보 제공 약관 수정 API 입니다. ")
  @PutMapping(path = "/terms/provide/{seq}")
  @MenuValidApi(menuCode = "TRM003", operation = CrudOperation.UPDATE)
  public ApiResponseDto modProvideTerms(@PathVariable Long seq,  @Validated @RequestBody TermsModReq termsModReq, AdminAuth adminAuth)
    throws ApiException {
    termsModReq.setSeq(seq);
    termsModReq.setTypeCode(TermsTypeCodes.PROVIDE.getCode());
    termsModReq.setModId(adminAuth.getId());
    termsService.modTerms(termsModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 수집 약관 수정", notes = "개인 정보 수집 약관 수정 API 입니다. ")
  @PutMapping(path = "/terms/marketing/{seq}")
  @MenuValidApi(menuCode = "TRM004", operation = CrudOperation.UPDATE)
  public ApiResponseDto modMarketingTerms(@PathVariable Long seq,  @Validated @RequestBody TermsModReq termsModReq, AdminAuth adminAuth)
    throws ApiException {
    termsModReq.setSeq(seq);
    termsModReq.setTypeCode(TermsTypeCodes.MARKETING.getCode());
    termsModReq.setModId(adminAuth.getId());
    termsService.modTerms(termsModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고 신청 약관 수정", notes = "광고 신청 약관 수정 API 입니다. ")
  @PutMapping(path = "/terms/adv/{seq}")
  @MenuValidApi(menuCode = "TRM005", operation = CrudOperation.UPDATE)
  public ApiResponseDto modAdvTerms(@PathVariable Long seq,  @Validated @RequestBody TermsModReq termsModReq, AdminAuth adminAuth)
    throws ApiException {
    termsModReq.setSeq(seq);
    termsModReq.setTypeCode(TermsTypeCodes.ADV.getCode());
    termsModReq.setModId(adminAuth.getId());
    termsService.modTerms(termsModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "서비스 이용 약관 폐기", notes = "서비스 이용 약관 폐기 API 입니다. ")
  @PutMapping(path = "/terms/service/garbage/{seq}")
  @MenuValidApi(menuCode = "TRM001", operation = CrudOperation.UPDATE)
  public ApiResponseDto garbageServiceTerms(@PathVariable Long seq,  AdminAuth adminAuth){
    termsService.garbageTerms(seq, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 처리 약관 폐기", notes = "개인 정보 처리 약관 폐기 API 입니다. ")
  @PutMapping(path = "/terms/privacy/garbage/{seq}")
  @MenuValidApi(menuCode = "TRM002", operation = CrudOperation.UPDATE)
  public ApiResponseDto garbagePrivacyTerms(@PathVariable Long seq,  AdminAuth adminAuth){
    termsService.garbageTerms(seq, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "제 3자 정보 제공 약관 폐기", notes = "제 3자 정보 제공 약관 폐기 API 입니다. ")
  @PutMapping(path = "/terms/provide/garbage/{seq}")
  @MenuValidApi(menuCode = "TRM003", operation = CrudOperation.UPDATE)
  public ApiResponseDto garbageProvideTerms(@PathVariable Long seq,  AdminAuth adminAuth){
    termsService.garbageTerms(seq, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "개인 정보 수집 약관 폐기", notes = "개인 정보 수집 약관 폐기 API 입니다. ")
  @PutMapping(path = "/terms/marketing/garbage/{seq}")
  @MenuValidApi(menuCode = "TRM004", operation = CrudOperation.UPDATE)
  public ApiResponseDto garbageMarketingTerms(@PathVariable Long seq,  AdminAuth adminAuth){
    termsService.garbageTerms(seq, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고 신청 약관 폐기", notes = "광고 신청 약관 폐기 API 입니다. ")
  @PutMapping(path = "/terms/adv/garbage/{seq}")
  @MenuValidApi(menuCode = "TRM005", operation = CrudOperation.UPDATE)
  public ApiResponseDto garbageAdvTerms(@PathVariable Long seq,  AdminAuth adminAuth){
    termsService.garbageTerms(seq, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }


}
