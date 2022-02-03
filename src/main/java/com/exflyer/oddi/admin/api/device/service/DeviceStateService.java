package com.exflyer.oddi.admin.api.device.service;

import com.exflyer.oddi.admin.api.device.code.DeviceStateCodes;
import com.exflyer.oddi.admin.api.device.dao.DeviceStateMapper;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateDetailResult;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateInfo;
import com.exflyer.oddi.admin.api.device.dto.DeviceStateSearchCodes;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryResult;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateResult;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceStateService {

    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private DeviceStateMapper deviceStateMapper;

    public DeviceStateSearchCodes findCodeForSearching() {
        List<Code> deviceTypeCode = codeRepository.findByGroupCodeAndUsable("DVT", true);
        List<Code> deviceStateCode = codeRepository.findByGroupCodeAndUsable("DSL", true);
        List<Code> deviceLevelCode = codeRepository.findByGroupCodeAndUsable("DLT", true);
        return new DeviceStateSearchCodes(deviceTypeCode, deviceStateCode, deviceLevelCode);
    }

    public PagingResult<SearchDeviceStateResult> findSearchDeviceState(SearchDeviceStateReq stateReq) {
        stateReq.setDeviceStateGroupCode(DeviceStateCodes.DEVICE_STATE_CODES.getCode());
        stateReq.setOrderBy(StringUtils.defaultIfBlank(stateReq.getOrderBy(), "ds.mod_date desc"));

        int resultCnt = deviceStateMapper.findSearchDeviceStateCount(stateReq);

        int end = stateReq.getPageNo() * stateReq.getPageSize();
        int start = end - stateReq.getPageSize();
        int pages = ((int) Math.ceil((double)resultCnt / (double)stateReq.getPageSize()));

        stateReq.setStart(start);
        stateReq.setEnd(stateReq.getPageSize());

        List<SearchDeviceStateResult> result = deviceStateMapper.findSearchDeviceState(stateReq);
        return PagingResult.createResultHashMap(resultCnt, pages, stateReq.getPageNo(), result);
    }

    public DeviceStateInfo detailDeviceInfo(String deviceId) {
        return deviceStateMapper.findSearchDeviceInfo(deviceId);
    }


    public PagingResult<SearchDeviceHistoryResult> findSearchDeviceHistory(SearchDeviceHistoryReq historyReq) {
        historyReq.setDeviceStateGroupCode(DeviceStateCodes.DEVICE_STATE_CODES.getCode());

        int resultCnt = deviceStateMapper.findSearchDeviceHistoryCount(historyReq);

        int end = historyReq.getPageNo() * historyReq.getPageSize();
        int start = end - historyReq.getPageSize();
        int pages = ((int) Math.ceil((double)resultCnt / (double)historyReq.getPageSize()));

        historyReq.setStart(start);
        historyReq.setEnd(historyReq.getPageSize());

        historyReq.setOrderBy(StringUtils.defaultIfBlank(historyReq.getOrderBy(), "dsh.reg_date desc"));
        List<SearchDeviceHistoryResult> result = deviceStateMapper.findSearchDeviceHistory(historyReq);

        return PagingResult.createResultHashMap(resultCnt, pages, historyReq.getPageNo(), result);
    }
}
