package com.exflyer.oddi.admin.api.contents.livestream;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.contents.livestream.code.LiveStreamCodes;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveScheduleModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamDetailResult;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartner;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.api.contents.livestream.service.LiveStreamService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Partner;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "컨텐츠", protocols = "http")
@Slf4j
@RestController
public class LiveStreamApi {

    @Autowired
    private LiveStreamService liveStreamService;

    @ApiOperation(value = "광고처 조회", notes = "광고처 조회 API 입니다. ")
    @GetMapping(path = "/live-stream/search-partner")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchPartnerResult>> findLiveStreamSearchPartner(@Validated SearchPartner searchReq) throws ApiException {
        searchReq.setOperationGroupCode(LiveStreamCodes.OPERATION_GROUP_CODE.getCode());
        PagingResult<SearchPartnerResult> searchPartnerList = liveStreamService.findLiveStreamSearchPartner(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchPartnerList);
    }

    @ApiOperation(value = "라이브스트림 상세 조회", notes = "라이브스트림 상세 조회 API 입니다. ")
    @GetMapping(path = "/live-stream")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.READ)
    public ApiResponseDto<LiveStreamDetailResult> findDetail() throws ApiException {
        LiveStreamDetailResult detail = liveStreamService.findDetail();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, detail);
    }

    @ApiOperation(value = "라이브스트림 채널 ID 변경", notes = "라이브스트림 채널 ID 변경 API 입니다. ")
    @PostMapping(path = "/live-stream/channel-id")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto channelIdModify(@RequestBody String channedId) {
        liveStreamService.channelIdModify(channedId);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 채널 등록(라이브채널 운영일정)", notes = "라이브스트림 채널 등록(라이브채널 운영일정) API 입니다. ")
    @PostMapping(path = "/live-stream")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto saveLiveStreaming(@Validated @RequestBody LiveStreamingReq liveStreamingReq, AdminAuth adminAuth) throws ApiException {
        liveStreamingReq.setRegId(adminAuth.getId());
        liveStreamService.saveLiveStreaming(liveStreamingReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 라이브채널(라이브채널 운영일정) 변경", notes = "라이브스트림 라이브채널(라이브채널 운영일정) 변경 API 입니다. ")
    @PutMapping(path = "/live-stream/live-channel/{seq}")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto modifyLiveChannel(@PathVariable Long seq, @Validated @RequestBody LiveStreamingReq liveStreamingReq, AdminAuth adminAuth) throws ApiException {
        liveStreamingReq.setModId(adminAuth.getId());
        liveStreamService.modifyLiveChannel(seq, liveStreamingReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 라이브채널(라이브채널 운영일정) 삭제", notes = "라이브스트림 라이브채널(라이브채널 운영일정) 삭제 API 입니다. ")
    @DeleteMapping(path = "/live-stream/live-channel/{seq}")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto deleteLiveChannel(@PathVariable Long seq) {
        liveStreamService.deleteLiveChannel(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 vod 변경", notes = "라이브스트림 vod 변경 API 입니다. ")
    @PutMapping(path = "/live-stream/vod/{youtubeId}")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto modifyVod( @PathVariable String youtubeId, @Validated @RequestBody LiveScheduleModReq liveScheduleModReq) throws ApiException {
        liveScheduleModReq.setYoutubeId(youtubeId);
        liveStreamService.modifyVod(liveScheduleModReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 vod 광고처 삭제", notes = "라이브스트림 vod 광고처 삭제 API 입니다. ")
    @PutMapping(path = "/live-stream/vod-partner/{youtubeId}")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto updateByPartnerByVod(@PathVariable String youtubeId) {
        liveStreamService.updateByPartnerByVod(youtubeId);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

    @ApiOperation(value = "라이브스트림 전체 정보 변경", notes = "라이브스트림 전체 정보 변경 API 입니다. ")
    @PutMapping(path = "/live-stream")
    @MenuValidApi(menuCode = "CNT001", operation = CrudOperation.UPDATE)
    public ApiResponseDto modify(@Validated @RequestBody LiveStreamModReq modReq, AdminAuth adminAuth) throws ApiException {
        modReq.setRegId(adminAuth.getId());
        liveStreamService.modify(modReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
