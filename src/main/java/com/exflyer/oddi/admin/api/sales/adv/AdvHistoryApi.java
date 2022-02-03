package com.exflyer.oddi.admin.api.sales.adv;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.sales.adv.dto.AdvHistorySearchCodes;
import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryReq;
import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryResult;
import com.exflyer.oddi.admin.api.sales.adv.service.AdvHistoryService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "매출", protocols = "http")
@Slf4j
@RestController
public class AdvHistoryApi {

    @Autowired
    private AdvHistoryService advHistoryService;

    @ApiOperation(value = "광고내역 사용 코드", notes = "광고내역에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/adv-history/search-codes")
    @MenuValidApi(menuCode = "BLL001", operation = CrudOperation.READ)
    public ApiResponseDto<AdvHistorySearchCodes> findCodeList() {
        AdvHistorySearchCodes searchCode = advHistoryService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "광고내역 조회", notes = "광고내역 조회 API 입니다. ")
    @GetMapping(path = "/adv-history")
    @MenuValidApi(menuCode = "BLL001", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchAdvHistoryResult>> findPaymentHistory(@Validated SearchAdvHistoryReq searchReq) {
        PagingResult<SearchAdvHistoryResult> pagingResult = advHistoryService.findAdvHistory(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }
}
