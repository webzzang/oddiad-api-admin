package com.exflyer.oddi.admin.api.device.dao;

import com.exflyer.oddi.admin.api.device.dto.DeviceStateInfo;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryResult;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateResult;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceStateMapper {
    int findSearchDeviceStateCount(SearchDeviceStateReq stateReq);
    int findSearchDeviceHistoryCount(SearchDeviceHistoryReq historyReq);
    List<SearchDeviceStateResult> findSearchDeviceState(SearchDeviceStateReq stateReq);
    List<SearchDeviceHistoryResult> findSearchDeviceHistory(SearchDeviceHistoryReq historyReq);
    DeviceStateInfo findSearchDeviceInfo(String deviceId);
}
