package com.exflyer.oddi.admin.api.notification.history;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.notification.history.dto.NotificationHistorySearchCodes;
import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryReq;
import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryResult;
import com.exflyer.oddi.admin.api.notification.history.service.NotificationHistoryService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "알림", protocols = "http")
@Slf4j
@RestController
public class NotificationHistoryApi {

    @Autowired
    private NotificationHistoryService notificationHistoryService;

    @ApiOperation(value = "문자메세지 발송내역 사용 코드", notes = "문자메세지 발송내역에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/sms-history/search-codes")
    @MenuValidApi(menuCode = "NTF003", operation = CrudOperation.READ)
    public ApiResponseDto<NotificationHistorySearchCodes> findCodeList() {
        NotificationHistorySearchCodes searchCode = notificationHistoryService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "문자메세지 발송내역 리스트 조회", notes = "문자메세지 발송내역 리스트 조회 API 입니다. ")
    @GetMapping(path = "/sms-history")
    @MenuValidApi(menuCode = "NTF003", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchHistoryResult>> findHistory(@Validated SearchHistoryReq historyReq) {
        PagingResult<SearchHistoryResult> pagingResult = notificationHistoryService.findByHistorySearchReq(historyReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }

    @ApiOperation(value = "문자메세지 재발송", notes = "문자메세지 재발송 API 입니다. ")
    @PutMapping(path = "/sms-history/re-send/{seq}")
    @MenuValidApi(menuCode = "NTF003", operation = CrudOperation.READ)
    public ApiResponseDto reSend(@PathVariable Long seq) throws ApiException {
        notificationHistoryService.reSend(seq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
