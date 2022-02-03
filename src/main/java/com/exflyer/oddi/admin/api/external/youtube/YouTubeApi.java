package com.exflyer.oddi.admin.api.external.youtube;

import com.exflyer.oddi.admin.api.external.youtube.dto.YouTubeListResult;
import com.exflyer.oddi.admin.api.external.youtube.service.YouTubeService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유투브 오디채널 목록조회", protocols = "http")
@Slf4j
@RestController
public class YouTubeApi {

  @Autowired
  private YouTubeService youTubeService;

  // TODO 스케쥴러 클래스로 나눠서 새벽 2시에 돌릴것
  //@Scheduled(fixedDelay = 5)
  @GetMapping(path = "/youtubeItem")
  public void getPlayItemAfterDBInsert() throws IOException {
    youTubeService.requestGetYouTubePlayItem();
  }

  @ApiOperation(value = "유투브 오디채널 목록 조회", notes = "유투브 오디채널 목록 조회 API 입니다. ")
  @GetMapping(path = "/youtube/playItem/{partnerSeq}")
  public ApiResponseDto<List<YouTubeListResult>> getYouTube(@PathVariable Long partnerSeq){
    List<YouTubeListResult> results = youTubeService.getYouTube(partnerSeq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, results);
  }

}
