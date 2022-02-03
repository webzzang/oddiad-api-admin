package com.exflyer.oddi.admin.api.terms;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.terms.codes.TermsTypeCodes;
import com.exflyer.oddi.admin.api.terms.dto.TermsDetailResult;
import com.exflyer.oddi.admin.api.terms.dto.TermsSearchReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsSearchResult;
import com.exflyer.oddi.admin.api.terms.service.TermsService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "약관 관리", protocols = "http")
@Slf4j
@RestController
public class TermsSearchApi {

  @Autowired
  private TermsService termsService;

  @ApiOperation(value = "서비스 이용 약관 조회", notes = "서비스 이용 약관 조회 API 입니다. ")
  @GetMapping(path = "/terms/service")
  @MenuValidApi(menuCode = "TRM001", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findServiceTerms(@Validated TermsSearchReq termsSearchReq) {
    termsSearchReq.setTypeCode(TermsTypeCodes.SERVICE.getCode());
    PagingResult<TermsSearchResult> termsResult = termsService.findTerms(termsSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsResult);
  }

  @ApiOperation(value = "개인 정보 처리 약관 조회", notes = "개인 정보 처리 약관 API 입니다. ")
  @GetMapping(path = "/terms/privacy")
  @MenuValidApi(menuCode = "TRM002", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findPrivacyTerms(@Validated TermsSearchReq termsSearchReq) {
    termsSearchReq.setTypeCode(TermsTypeCodes.PRIVACY.getCode());
    PagingResult<TermsSearchResult> termsResult = termsService.findTerms(termsSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsResult);
  }

  @ApiOperation(value = "제 3자 정보 제공 약관 조회", notes = "제 3자 정보 제공 약관 API 입니다. ")
  @GetMapping(path = "/terms/provide")
  @MenuValidApi(menuCode = "TRM003", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findProvideTerms(@Validated TermsSearchReq termsSearchReq) {
    termsSearchReq.setTypeCode(TermsTypeCodes.PROVIDE.getCode());
    PagingResult<TermsSearchResult> termsResult = termsService.findTerms(termsSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsResult);
  }

  @ApiOperation(value = "개인 정보 수집 약관 조회", notes = "개인 정보 수집 약관 조회 API 입니다. ")
  @GetMapping(path = "/terms/marketing")
  @MenuValidApi(menuCode = "TRM004", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findMarketingTerms(@Validated TermsSearchReq termsSearchReq) {
    termsSearchReq.setTypeCode(TermsTypeCodes.MARKETING.getCode());
    PagingResult<TermsSearchResult> termsResult = termsService.findTerms(termsSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsResult);
  }

  @ApiOperation(value = "광고 신청 약관 조회", notes = "광고 신청 약관 조회 API 입니다. ")
  @GetMapping(path = "/terms/adv")
  @MenuValidApi(menuCode = "TRM005", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findAdvTerms(@Validated TermsSearchReq termsSearchReq) {
    termsSearchReq.setTypeCode(TermsTypeCodes.ADV.getCode());
    PagingResult<TermsSearchResult> termsResult = termsService.findTerms(termsSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsResult);
  }

//

  @ApiOperation(value = "서비스 이용 약관 상세 조회", notes = "서비스 이용 약관 상세 조회 API 입니다. ")
  @GetMapping(path = "/terms/service/{seq}")
  @MenuValidApi(menuCode = "TRM001", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<TermsDetailResult> findDetailServiceTerms(@PathVariable Long seq) throws ApiException {
    TermsDetailResult termsDetailResult = termsService.findDetailTerms(seq, TermsTypeCodes.SERVICE.getCode());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsDetailResult);
  }

  @ApiOperation(value = "개인 정보 처리 약관 상세 조회", notes = "개인 정보 처리 약관 상세 API 입니다. ")
  @GetMapping(path = "/terms/privacy/{seq}")
  @MenuValidApi(menuCode = "TRM002", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findDetailPrivacyTerms(@PathVariable Long seq)
    throws ApiException {
    TermsDetailResult termsDetailResult = termsService.findDetailTerms(seq, TermsTypeCodes.PRIVACY.getCode());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsDetailResult);
  }

  @ApiOperation(value = "제 3자 정보 제공 약관 상세 조회", notes = "제 3자 정보 제공 약관 상세 API 입니다. ")
  @GetMapping(path = "/terms/provide/{seq}")
  @MenuValidApi(menuCode = "TRM003", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findDetailProvideTerms(@PathVariable Long seq)
    throws ApiException {
    TermsDetailResult termsDetailResult = termsService.findDetailTerms(seq, TermsTypeCodes.PROVIDE.getCode());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsDetailResult);
  }

  @ApiOperation(value = "개인 정보 수집 약관 상세 조회", notes = "개인 정보 수집 약관 상세 조회 API 입니다. ")
  @GetMapping(path = "/terms/marketing/{seq}")
  @MenuValidApi(menuCode = "TRM004", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findDetailMarketingTerms(@PathVariable Long seq)
    throws ApiException {
    TermsDetailResult termsDetailResult = termsService.findDetailTerms(seq, TermsTypeCodes.MARKETING.getCode());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsDetailResult);
  }

  @ApiOperation(value = "광고 신청 약관 상세 조회", notes = "광고 신청 약관 상세 조회 API 입니다. ")
  @GetMapping(path = "/terms/adv/{seq}")
  @MenuValidApi(menuCode = "TRM005", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<TermsSearchResult>> findDetailAdvTerms(@PathVariable Long seq)
    throws ApiException {
    TermsDetailResult termsDetailResult = termsService.findDetailTerms(seq, TermsTypeCodes.ADV.getCode());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, termsDetailResult);
  }


}
