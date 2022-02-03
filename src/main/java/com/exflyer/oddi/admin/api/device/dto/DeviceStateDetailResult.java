package com.exflyer.oddi.admin.api.device.dto;

import com.exflyer.oddi.admin.share.dto.PagingResult;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeviceStateDetailResult {

  private DeviceStateInfo deviceResult;
  private PagingResult<SearchDeviceHistoryResult> deviceHistoryList;

  public DeviceStateDetailResult(DeviceStateInfo deviceResult, PagingResult<SearchDeviceHistoryResult> deviceHistoryList){
    this.deviceResult = deviceResult;
    this.deviceHistoryList = deviceHistoryList;
  }

}
