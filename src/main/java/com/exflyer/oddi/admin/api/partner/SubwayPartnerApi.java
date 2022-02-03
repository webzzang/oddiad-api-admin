package com.exflyer.oddi.admin.api.partner;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.partner.dto.OddiZoneAddReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerDetailResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerSearchCodes;
import com.exflyer.oddi.admin.api.partner.dto.OddiZoneSubwayDetail;
import com.exflyer.oddi.admin.api.partner.service.OddiZonePartnerService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "광고처관리 - 지하철", protocols = "http")
@Slf4j
@RestController
public class SubwayPartnerApi {

  @Autowired
  private OddiZonePartnerService oddiZonePartnerService;

  @ApiOperation(value = "지하철 목록 조회 사용 코드", notes = "지하철 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner/subway/codes")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.READ)
  public ApiResponseDto<OddiZonePartnerSearchCodes> findCodeList() {
    OddiZonePartnerSearchCodes searchCode = oddiZonePartnerService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "지하철 목록 조회", notes = "지하철 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner/subway")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<OddiZonePartnerListSearchResult>> find(@Validated OddiZonePartnerListSearchReq oddiZonePartnerListSearchReq) {
    oddiZonePartnerListSearchReq.setChannelType(ChannelCodes.SUBWAY.getCode());
    PagingResult<OddiZonePartnerListSearchResult> pagingResult = oddiZonePartnerService.findBySearchReq(oddiZonePartnerListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "지하철 상세 조회", notes = "지하철 상세 조회 API 입니다. ")
  @GetMapping(path = "/partner/subway/{seq}")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.READ)
  public ApiResponseDto<OddiZonePartnerDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    OddiZonePartnerDetailResult oddiZonePartnerDetailResult = oddiZonePartnerService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, oddiZonePartnerDetailResult);
  }

  @ApiOperation(value = "지하철 신규등록", notes = "지하철 신규등록 API 입니다. ")
  @PostMapping(path = "/partner/subway")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.CREATE)
  public ApiResponseDto add(@Validated @RequestBody OddiZoneAddReq oddiZoneAddReq, AdminAuth adminAuth) {
    oddiZoneAddReq.setChannelType(ChannelCodes.SUBWAY.getCode());
    oddiZoneAddReq.setRegId(adminAuth.getId());
    oddiZonePartnerService.save(oddiZoneAddReq);

    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }


  @ApiOperation(value = "지하철 수정", notes = "지하철 수정 API 입니다. ")
  @PutMapping(path = "/partner/subway/{seq}")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody OddiZoneAddReq oddiZoneAddReq, AdminAuth adminAuth)
      throws ApiException {
    oddiZoneAddReq.setChannelType(ChannelCodes.SUBWAY.getCode());
    oddiZoneAddReq.setSeq(seq);
    oddiZoneAddReq.setModId(adminAuth.getId());
    oddiZoneAddReq.setRegId(adminAuth.getId());
    oddiZonePartnerService.modify(oddiZoneAddReq);

    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }


  @ApiOperation(value = "지하철 전체설정 - 지하철 운영시간 조회", notes = "지하철 전체설정 - 지하철 운영시간 조회 API 입니다. ")
  @GetMapping(path = "/partner/subway/config")
  @MenuValidApi(menuCode = "ADP002", operation = CrudOperation.READ)
  public ApiResponseDto<OddiZoneSubwayDetail> findSubwayOperation() throws ApiException {
    OddiZoneSubwayDetail oddiZoneSubwayDetail = oddiZonePartnerService.findSubwayOperation();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, oddiZoneSubwayDetail);
  }

}
