package com.exflyer.oddi.admin.api.contents.livestream.dto;

import com.exflyer.oddi.admin.models.LiveSchedule;
import com.exflyer.oddi.admin.models.LiveStreaming;
import com.exflyer.oddi.admin.models.Youtube;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LiveStreamDetailResult {

  private LiveStreaming liveStreamResult;
  private List<LiveSchedule> liveScheduleList;
  private List<LiveStreamingVodList> vodList;

  public LiveStreamDetailResult(LiveStreaming liveStreamResult, List<LiveSchedule> liveScheduleList, List<LiveStreamingVodList> vodList){
    this.liveStreamResult = liveStreamResult;
    this.liveScheduleList = liveScheduleList;
    this.vodList = vodList;
  }

}
