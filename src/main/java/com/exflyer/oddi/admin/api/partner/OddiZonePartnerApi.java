package com.exflyer.oddi.admin.api.partner;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.partner.dto.OddiZoneAddReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerDetailResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchResult;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerSearchCodes;
import com.exflyer.oddi.admin.api.partner.dto.PartnerDeviceAddReq;
import com.exflyer.oddi.admin.api.partner.service.OddiZonePartnerService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.PartnerDevice;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "광고처관리 - 오디존", protocols = "http")
@Slf4j
@RestController
public class OddiZonePartnerApi {

  @Autowired
  private OddiZonePartnerService oddiZonePartnerService;

  @ApiOperation(value = "오디존 목록 조회 사용 코드", notes = "오디존 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner/oddi-zone/codes")
  @MenuValidApi(menuCode = "ADP001", operation = CrudOperation.READ)
  public ApiResponseDto<OddiZonePartnerSearchCodes> findCodeList() {
    OddiZonePartnerSearchCodes searchCode = oddiZonePartnerService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "오디존 목록 조회", notes = "오디존 목록 조회 API 입니다. ")
  @GetMapping(path = "/partner/oddi-zone")
  @MenuValidApi(menuCode = "ADP001", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<OddiZonePartnerListSearchResult>> find(@Validated OddiZonePartnerListSearchReq oddiZonePartnerListSearchReq) {
    oddiZonePartnerListSearchReq.setChannelType(ChannelCodes.ODDI_ZONE.getCode());
    PagingResult<OddiZonePartnerListSearchResult> pagingResult = oddiZonePartnerService.findBySearchReq(oddiZonePartnerListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "오디존 상세 조회", notes = "오디존 상세 조회 API 입니다. ")
  @GetMapping(path = "/partner/oddi-zone/{seq}")
  @MenuValidApi(menuCode = "ADP001", operation = CrudOperation.READ)
  public ApiResponseDto<OddiZonePartnerDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    OddiZonePartnerDetailResult oddiZonePartnerDetailResult = oddiZonePartnerService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, oddiZonePartnerDetailResult);
  }

  @ApiOperation(value = "오디존 신규등록", notes = "오디존 신규등록 API 입니다. ")
  @PostMapping(path = "/partner/oddi-zone")
  @MenuValidApi(menuCode = "ADP001", operation = CrudOperation.CREATE)
  public ApiResponseDto add(@Validated @RequestBody OddiZoneAddReq oddiZoneAddReq, AdminAuth adminAuth) {
    oddiZoneAddReq.setChannelType(ChannelCodes.ODDI_ZONE.getCode());
    oddiZoneAddReq.setRegId(adminAuth.getId());

    List<String> partnerDevice = oddiZonePartnerService.isAddPartnerDevice(oddiZoneAddReq);
    List<String> deviceInfo = oddiZonePartnerService.isAddPartnerDeviceInfo(oddiZoneAddReq);

    ApiResponseDto returnDto;

    if(partnerDevice.size() > 0) {
      returnDto = new ApiResponseDto(ApiResponseCodes.DUPLICATE_DEVICE, partnerDevice);
    }else if(deviceInfo.size() != 0){
      returnDto = new ApiResponseDto(ApiResponseCodes.ACCURATE_NOT_DEVICE, deviceInfo);
    }else{
      returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
      oddiZonePartnerService.save(oddiZoneAddReq);
    }

    /*Map<String, Integer> isDeviceInfo = oddiZonePartnerService.isAddDeviceInfo(oddiZoneAddReq);

    ApiResponseDto returnDto;

    if(isDeviceInfo != null) {
        int partner_device_cnt = Integer.parseInt(isDeviceInfo.get("partner_device_cnt") + "");
        int device_info_cnt = Integer.parseInt(isDeviceInfo.get("device_info_cnt") + "");

        if (partner_device_cnt > 0) {
            returnDto = new ApiResponseDto(ApiResponseCodes.DUPLICATE_DEVICE);
        } else if (device_info_cnt == 0) {
            returnDto = new ApiResponseDto(ApiResponseCodes.ACCURATE_NOT_DEVICE);
        } else {
            returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
            oddiZonePartnerService.save(oddiZoneAddReq);
        }
    }else{
        returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
        oddiZonePartnerService.save(oddiZoneAddReq);
    }*/

    return returnDto;
  }


  @ApiOperation(value = "오디존 수정", notes = "오디존 수정 API 입니다. ")
  @PutMapping(path = "/partner/oddi-zone/{seq}")
  @MenuValidApi(menuCode = "ADP001", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody OddiZoneAddReq oddiZoneAddReq, AdminAuth adminAuth)
      throws ApiException {
    oddiZoneAddReq.setChannelType(ChannelCodes.ODDI_ZONE.getCode());
    oddiZoneAddReq.setSeq(seq);
    oddiZoneAddReq.setModId(adminAuth.getId());
    oddiZoneAddReq.setRegId(adminAuth.getId());

    ApiResponseDto returnDto;
    List<String> deviceInfo = oddiZonePartnerService.isAddPartnerDeviceInfo(oddiZoneAddReq);

    if(deviceInfo.size() != 0){
      returnDto = new ApiResponseDto(ApiResponseCodes.ACCURATE_NOT_DEVICE, deviceInfo);
    }else {
      returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
      oddiZonePartnerService.modify(oddiZoneAddReq);
    }

    /*Map<String, Integer> isDeviceInfo = oddiZonePartnerService.isModifyDeviceInfo(oddiZoneAddReq);

    ApiResponseDto returnDto;

    if(isDeviceInfo != null) {
      int partner_device_cnt = Integer.parseInt(isDeviceInfo.get("partner_device_cnt") + "");
      int device_info_cnt = Integer.parseInt(isDeviceInfo.get("device_info_cnt") + "");

      if (partner_device_cnt > 0) {
        returnDto = new ApiResponseDto(ApiResponseCodes.DUPLICATE_DEVICE);
      } else if (device_info_cnt == 0) {
        returnDto = new ApiResponseDto(ApiResponseCodes.ACCURATE_NOT_DEVICE);
      } else {
        returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
        oddiZonePartnerService.modify(oddiZoneAddReq);
      }
    }else{
      returnDto = new ApiResponseDto(ApiResponseCodes.SUCCESS);
      oddiZonePartnerService.modify(oddiZoneAddReq);
    }*/

    return returnDto;
  }

}
