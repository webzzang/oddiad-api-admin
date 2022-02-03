package com.exflyer.oddi.admin.api.adv.make;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeDetailResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeListSearchResult;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeModReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeSearchCodes;
import com.exflyer.oddi.admin.api.adv.make.dto.DevicePushRes;
import com.exflyer.oddi.admin.api.adv.make.service.AdvMakeService;
import com.exflyer.oddi.admin.api.adv.make.service.OddiAdvMakeService;
import com.exflyer.oddi.admin.api.adv.make.service.SubwayAdvMakeService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "광고 - 광고 편성", protocols = "http")
@Slf4j
@RestController
public class AdvMakeApi {

  @Autowired
  private AdvMakeService advMakeService;
  @Autowired
  private OddiAdvMakeService oddiAdvMakeService;
  @Autowired
  private SubwayAdvMakeService subwayAdvMakeService;

  @ApiOperation(value = "광고편성 목록 조회 사용 코드", notes = "광고편성 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/advMake/search-codes")
  @MenuValidApi(menuCode = "ADV002", operation = CrudOperation.READ)
  public ApiResponseDto<AdvMakeSearchCodes> findCodeList() {
    AdvMakeSearchCodes searchCode = advMakeService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "광고편성 목록 조회", notes = "광고편성 목록 조회 API 입니다. ")
  @GetMapping(path = "/advMake")
  @MenuValidApi(menuCode = "ADV002", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<AdvMakeListSearchResult>> find(@Validated AdvMakeListSearchReq advMakeListSearchReq) {
    PagingResult<AdvMakeListSearchResult> pagingResult = advMakeService.findBySearchReq(advMakeListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "광고편성 상세 조회", notes = "광고편성 상세 조회 API 입니다. ")
  @GetMapping(path = "/advMake/{seq}/{deviceId}")
  @MenuValidApi(menuCode = "ADV002", operation = CrudOperation.READ)
  public ApiResponseDto<AdvMakeDetailResult> findDetail(@PathVariable Long seq, @PathVariable String deviceId) throws ApiException {
    AdvMakeDetailResult advMakeDetailResult = advMakeService.findDetail(seq, deviceId);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, advMakeDetailResult);
  }

  @ApiOperation(value = "광고편성 저장", notes = "광고편성 저장 API 입니다. ")
  @PutMapping(path = "/advMake/{seq}/{deviceId}")
  @MenuValidApi(menuCode = "ADV002", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @PathVariable String deviceId, @Validated @RequestBody AdvMakeModReq advMakeModReq, AdminAuth adminAuth) throws ApiException {
    advMakeModReq.setDeviceId(deviceId);
    advMakeModReq.setSeq(seq);
    advMakeModReq.setRegId(adminAuth.getId());
    advMakeModReq.setRegDate(LocalDateUtils.krNow());

    if(ChannelCodes.ODDI_ZONE.getCode().equals(advMakeModReq.getChannelType())){
      oddiAdvMakeService.modify(advMakeModReq);
    }else if(ChannelCodes.SUBWAY.getCode().equals(advMakeModReq.getChannelType())){
      subwayAdvMakeService.modify(advMakeModReq);
    }

    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고편성 기기송출", notes = "광고편성 기기송출 API 입니다. ")
  @PostMapping(path = "/advMake/{seq}/{deviceId}")
  @MenuValidApi(menuCode = "ADV002", operation = CrudOperation.READ)
  public ApiResponseDto<DevicePushRes> devicePush(@PathVariable Long seq, @PathVariable String deviceId, @Validated @RequestBody AdvMakeModReq advMakeModReq, AdminAuth adminAuth) throws IOException, ApiException {
    advMakeModReq.setDeviceId(deviceId);
    advMakeModReq.setSeq(seq);
    advMakeModReq.setRegId(adminAuth.getId());
    advMakeModReq.setRegDate(LocalDateUtils.krNow());
    DevicePushRes devicePushRes = oddiAdvMakeService.devicePush(seq, deviceId, "request_main_ad", advMakeModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, devicePushRes);
  }

}
