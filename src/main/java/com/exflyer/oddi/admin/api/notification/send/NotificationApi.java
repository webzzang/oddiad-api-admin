package com.exflyer.oddi.admin.api.notification.send;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.api.contents.livestream.service.LiveStreamService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchReq;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchResult;
import com.exflyer.oddi.admin.api.notification.send.dto.NotificationReq;
import com.exflyer.oddi.admin.api.notification.send.dto.NotificationSearchCodes;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTarget;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTargetResult;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchPartnerReq;
import com.exflyer.oddi.admin.api.notification.send.service.NotificationService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "알림", protocols = "http")
@Slf4j
@RestController
public class NotificationApi {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private LiveStreamService liveStreamService;

    @ApiOperation(value = "문자메세지 발송그룹 검색", notes = "문자메세지 발송그룹 검색 API 입니다. ")
    @GetMapping(path = "/send-sms/search-group-target")
    @MenuValidApi(menuCode = "NTF001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchGroupTargetResult>> findGroupTarget(@Validated SearchGroupTarget searchGroupTarget) {
        PagingResult<SearchGroupTargetResult> pagingResult = notificationService.findByGroupTargetSearchReq(searchGroupTarget);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }

    @ApiOperation(value = "회원 검색", notes = "회원 검색 API 입니다. ")
    @GetMapping(path = "/send-sms/search-member")
    @MenuValidApi(menuCode = "NTF001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<MemberSearchResult>> findMember(@Validated MemberSearchReq memberSearchReq) {
        PagingResult<MemberSearchResult> pagingResult = notificationService.findByMemberSearchReq(memberSearchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }

    @ApiOperation(value = "광고처별 광고주 조회", notes = "광고처별 광고주 조회 API 입니다. ")
    @GetMapping(path = "/send-sms/search-partner")
    @MenuValidApi(menuCode = "NTF001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchPartnerResult>> findAdvNowExpoPartner(@Validated SearchPartnerReq searchReq) throws ApiException {
        PagingResult<SearchPartnerResult> searchPartnerList = notificationService.findAdvNowExpoPartner(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchPartnerList);
    }

    @ApiOperation(value = "문자메세지 발송 사용 코드", notes = "오디존설정에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/send-sms/search-codes")
    @MenuValidApi(menuCode = "NTF001", operation = CrudOperation.READ)
    public ApiResponseDto<NotificationSearchCodes> findCodeList() {
        NotificationSearchCodes searchCode = notificationService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "문자메세지 발송", notes = "문자메세지 발송 조회 API 입니다.")
    @PostMapping(path = "/send-sms")
    @MenuValidApi(menuCode = "NTF001", operation = CrudOperation.CREATE)
    public ApiResponseDto sendSms(@Validated @RequestBody NotificationReq sendReq, AdminAuth adminAuth) throws ApiException {
        sendReq.setRegId(adminAuth.getId());
        notificationService.sendSms(sendReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS);
    }

}
