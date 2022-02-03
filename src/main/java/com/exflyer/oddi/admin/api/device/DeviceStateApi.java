package com.exflyer.oddi.admin.api.device;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateDetailResult;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateInfo;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateSearchCodes;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryResult;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateResult;
import com.exflyer.oddi.admin.api.device.service.DeviceStateService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "광고기기", protocols = "http")
@Slf4j
@RestController
public class DeviceStateApi {

    @Autowired
    private DeviceStateService deviceStateService;

    @ApiOperation(value = "기기관리 사용 코드", notes = "기기관리에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/device/search-codes")
    @MenuValidApi(menuCode = "DVC001", operation = CrudOperation.READ)
    public ApiResponseDto<DeviceStateSearchCodes> findCodeList() {
        DeviceStateSearchCodes searchCode = deviceStateService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "기기관리 리스트 조회", notes = "기기관리 리스트 조회 API 입니다. ")
    @GetMapping(path = "/device")
    @MenuValidApi(menuCode = "DVC001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchDeviceStateResult>> findSearchDeviceState(@Validated SearchDeviceStateReq stateReq) {
        PagingResult<SearchDeviceStateResult> pagingResult = deviceStateService.findSearchDeviceState(stateReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }

    @ApiOperation(value = "기기관리 상세 기본정보 조회", notes = "기기관리 상세 - 기본정보 조회 API 입니다. ")
    @GetMapping(path = "/device/info/{deviceId}")
    @MenuValidApi(menuCode = "DVC001", operation = CrudOperation.READ)
    public ApiResponseDto<DeviceStateInfo> detailDeviceInfo(@PathVariable String deviceId) {
        DeviceStateInfo detailDeviceInfo = deviceStateService.detailDeviceInfo(deviceId);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detailDeviceInfo);
    }

    @ApiOperation(value = "기기관리 상세 - 상태이력 리스트 조회", notes = "기기관리 상세 - 상태이력 리스트 조회 API 입니다. ")
    @GetMapping(path = "/device/history")
    @MenuValidApi(menuCode = "DVC001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchDeviceHistoryResult>> findSearchDeviceHistory(@Validated SearchDeviceHistoryReq historyReq) {
        PagingResult<SearchDeviceHistoryResult> deviceStateDetailResult = deviceStateService.findSearchDeviceHistory(historyReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, deviceStateDetailResult);
    }

}
