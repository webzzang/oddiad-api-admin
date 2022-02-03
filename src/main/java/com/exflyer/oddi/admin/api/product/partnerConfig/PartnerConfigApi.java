package com.exflyer.oddi.admin.api.product.partnerConfig;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigDetailResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneDefaultReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneSearchCodes;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.SubwayConfigDetailResult;
import com.exflyer.oddi.admin.api.product.partnerConfig.service.PartnerConfigService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.ChannelCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.nio.channels.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "상품관리 - 오디존,지하철 설정", protocols = "http")
@Slf4j
@RestController
public class PartnerConfigApi {

    @Autowired
    private PartnerConfigService partnerConfigService;

    @ApiOperation(value = "오디존설정 사용 코드", notes = "오디존설정에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/product/config/oddi-zone/search-codes")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.READ)
    public ApiResponseDto<OddiZoneSearchCodes> findCodeList() {
        OddiZoneSearchCodes searchCode = partnerConfigService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "오디존 설정 조회", notes = "오디존 설정 조회 API 입니다.")
    @GetMapping(path = "/product/config/oddi-zone")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.READ)
    public ApiResponseDto<OddiZoneConfigDetailResult> odZoneDetail() throws ApiException {
        OddiZoneConfigDetailResult detail = partnerConfigService.detail(ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "오디존 설정 정보 저장", notes = "오디존 설정 정보 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZonesave(@Validated @RequestBody OddiZoneConfigReq saveReq)
        throws ApiException {
        saveReq.setType(ChannelCodes.ODDI_ZONE.getCode());
        partnerConfigService.save(saveReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 설정 기기당 슬롯수 저장", notes = "오디존 설정 기기당 슬롯수 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/slot-count/{slotCount}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneSlotCountSave(@PathVariable Integer slotCount)
        throws ApiException {
        partnerConfigService.slotCountSave(slotCount, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 설정 슬롯당 노출시간 저장", notes = "오디존 설정 슬롯당 노출시간 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/slot-video-time/{slotVideoTime}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneSlotVideoTimeSave(@PathVariable Integer slotVideoTime)
        throws ApiException {
        partnerConfigService.slotVideoTimeSave(slotVideoTime, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 광고 시작 가능일 저장", notes = "오디존 광고 시작 가능일 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/adv-start/{oddiAdvFromStartDate}/{oddiAdvToStartDate}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneOddiAdvFromStartDateSave(@PathVariable Integer oddiAdvFromStartDate,@PathVariable Integer oddiAdvToStartDate)
        throws ApiException {
        partnerConfigService.oddiAdvFromStartDateSave(oddiAdvFromStartDate, oddiAdvToStartDate, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 최장 광고기간 저장", notes = "오디존 최장 광고기간 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/adv-max-date/{oddiAdvMaxDate}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneOddiAdvMaxDateSave(@PathVariable Integer oddiAdvMaxDate)
        throws ApiException {
        partnerConfigService.oddiAdvMaxDateSave(oddiAdvMaxDate, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 광고 신청취소 가능일 저장", notes = "오디존 광고 신청취소 가능일 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/adv-cancel-date/{oddiAdvCancelDate}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneOddiAdvCancelDateSave(@PathVariable Integer oddiAdvCancelDate)
        throws ApiException {
        partnerConfigService.oddiAdvCancelDateSave(oddiAdvCancelDate, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 디자인 제작 요청여부 저장", notes = "오디존 디자인 제작 요청여부 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/design-request/{designRequest}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneDesignRequestSave(@PathVariable Boolean designRequest)
        throws ApiException {
        partnerConfigService.designRequestSave(designRequest, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 기본광고 저장", notes = "오디존 기본광고 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/defaultAdv")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneAdvDefaultSaveSave(@Validated @RequestBody OddiZoneDefaultReq saveReq)
        throws ApiException {
        saveReq.setType(ChannelCodes.ODDI_ZONE.getCode());
        partnerConfigService.advDefaultSave(saveReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "오디존 화면분할 저장", notes = "오디존 화면분할 저장 API 입니다. ")
    @PutMapping(path = "/product/config/od-zone/displayDiv/{displayDiv}/{sideDisplayServiceCode}")
    @MenuValidApi(menuCode = "PRD002", operation = CrudOperation.UPDATE)
    public ApiResponseDto odZoneDisplayDivSave(@PathVariable String displayDiv, @PathVariable String sideDisplayServiceCode)
        throws ApiException {
        partnerConfigService.displayDivSave(displayDiv, sideDisplayServiceCode, ChannelCodes.ODDI_ZONE.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 광고 설정 조회", notes = "지하철 광고 설정 조회 API 입니다.")
    @GetMapping(path = "/product/config/subway")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.READ)
    public ApiResponseDto<SubwayConfigDetailResult> subwayDetail() throws ApiException {
        SubwayConfigDetailResult detail = partnerConfigService.subwayDetail(ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "지하철 광고 설정 정보 저장", notes = "지하철 광고 설정 정보 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwaySave(@Validated @RequestBody OddiZoneConfigReq saveReq)
        throws ApiException {
        saveReq.setType(ChannelCodes.SUBWAY.getCode());
        partnerConfigService.save(saveReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 광고  설정 기기당 슬롯수 저장", notes = "지하철 광고  설정 기기당 슬롯수 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/slot-count/{slotCount}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwaySlotCountSave(@PathVariable Integer slotCount)
        throws ApiException {
        partnerConfigService.slotCountSave(slotCount, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 광고  설정 슬롯당 노출시간 저장", notes = "지하철 광고  설정 슬롯당 노출시간 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/slot-video-time/{slotVideoTime}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwaySlotVideoTimeSave(@PathVariable Integer slotVideoTime)
        throws ApiException {
        partnerConfigService.slotVideoTimeSave(slotVideoTime, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 익월 광고 신청 마감일 저장", notes = "지하철 익월 광고 신청 마감일 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/adv-last-date/{subwayAdvLastDate}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwayAdvLastDateSave(@PathVariable Integer subwayAdvLastDate)
        throws ApiException {
        partnerConfigService.subwayAdvLastDateSave(subwayAdvLastDate, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 최대광고 시작일 저장", notes = "지하철 최대광고 시작일 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/adv-max-start-date/{subwayAdvMaxStartDate}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwayAdvMaxStartDateSave(@PathVariable Integer subwayAdvMaxStartDate)
        throws ApiException {
        partnerConfigService.subwayAdvMaxStartDateSave(subwayAdvMaxStartDate, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 최장 광고기간 저장", notes = "지하철 최장 광고기간 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/adv-max-date/{subwayAdvMaxDate}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwayAdvMaxDateSave(@PathVariable Integer subwayAdvMaxDate)
        throws ApiException {
        partnerConfigService.subwayAdvMaxDateSave(subwayAdvMaxDate, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 광고 신청취소 가능일 저장", notes = "지하철 광고 신청취소 가능일 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/adv-cancel-date/{subwayAdvCancelDate}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwayAdvCancelDateSave(@PathVariable Integer subwayAdvCancelDate)
        throws ApiException {
        partnerConfigService.subwayAdvCancelDateSave(subwayAdvCancelDate, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 디자인 제작 요청여부 저장", notes = "지하철 디자인 제작 요청여부 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/design-request/{designRequest}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto subwayDesignRequestSave(@PathVariable Boolean designRequest)
        throws ApiException {
        partnerConfigService.designRequestSave(designRequest, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "지하철 운영시간 저장", notes = "지하철 운영시간 저장 API 입니다. ")
    @PutMapping(path = "/product/config/subway/operation-time/{operationStartTime}/{operationEndTime}")
    @MenuValidApi(menuCode = "PRD003", operation = CrudOperation.UPDATE)
    public ApiResponseDto operationStartTimeSave(@PathVariable String operationStartTime,@PathVariable String operationEndTime)
        throws ApiException {
        partnerConfigService.operationStartTimeSave(operationStartTime, operationEndTime, ChannelCodes.SUBWAY.getCode());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
