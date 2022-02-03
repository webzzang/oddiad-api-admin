package com.exflyer.oddi.admin.api.cs.partnerRequest;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchReq;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchResult;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestSearchCodes;
import com.exflyer.oddi.admin.api.cs.partnerRequest.service.PartnerRequestService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CS 관리", protocols = "http")
@Slf4j
@RestController
public class PartnerRequestApi {

  @Autowired
  private PartnerRequestService partnerRequestService;

  @ApiOperation(value = "파트너문의 목록 조회 사용 코드", notes = "파트너문의 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner-request/search-codes")
  @MenuValidApi(menuCode = "COS004", operation = CrudOperation.READ)
  public ApiResponseDto<PartnerRequestSearchCodes> findCodeList() {
    PartnerRequestSearchCodes searchCode = partnerRequestService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "파트너문의 목록 조회", notes = "파트너문의 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner-request")
  @MenuValidApi(menuCode = "COS004", operation = CrudOperation.READ)
  @OddiEncrypt
  public ApiResponseDto<PagingResult<PartnerRequestListSearchResult>> find(@Validated PartnerRequestListSearchReq partnerRequestListSearchReq) {
    PagingResult<PartnerRequestListSearchResult> pagingResult = partnerRequestService.findBySearchReq(partnerRequestListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }


}
