package com.exflyer.oddi.admin.api.sales.payment;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelRes;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.sales.payment.dto.PaymentHistorySearchCodes;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryReq;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryResult;
import com.exflyer.oddi.admin.api.sales.payment.service.PaymentHistoryService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "매출", protocols = "http")
@Slf4j
@RestController
public class PaymentHistoryApi {

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @ApiOperation(value = "결제내역 사용 코드", notes = "결제내역에 사용되는 코드 목록 조회 API 입니다. ")
    @GetMapping(path = "/payment-history/search-codes")
    @MenuValidApi(menuCode = "BLL002", operation = CrudOperation.READ)
    public ApiResponseDto<PaymentHistorySearchCodes> findCodeList() {
        PaymentHistorySearchCodes searchCode = paymentHistoryService.findCodeForSearching();
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
    }

    @ApiOperation(value = "결제내역 조회", notes = "결제내역 조회 API 입니다. ")
    @GetMapping(path = "/payment-history")
    @MenuValidApi(menuCode = "BLL002", operation = CrudOperation.READ)
    public ApiResponseDto<PagingResult<SearchPaymentHistoryResult>> findPaymentHistory(@Validated SearchPaymentHistoryReq searchReq) {
        PagingResult<SearchPaymentHistoryResult> pagingResult = paymentHistoryService.findPaymentHistory(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
    }

    @ApiOperation(value = "결제내역 갯수 조회", notes = "결제내역 갯수 조회 API 입니다. ")
    @GetMapping(path = "/payment-history-count")
    @MenuValidApi(menuCode = "BLL002", operation = CrudOperation.READ)
    public ApiResponseDto<Integer> findPaymentHistoryCount(@Validated SearchPaymentHistoryReq searchReq) {
        Integer historyCount = paymentHistoryService.findPaymentHistoryCount(searchReq);
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, historyCount);
    }

    @ApiOperation(value = "결제취소", notes = "결제취소 API 입니다. ")
    @PutMapping(path = "/payment-history/cancel/{paymentSeq}/{advSeq}")
    @MenuValidApi(menuCode = "BLL002", operation = CrudOperation.UPDATE)
    public ApiResponseDto paymentCancel(@PathVariable Long paymentSeq, @PathVariable Long advSeq, AdminAuth adminAuth) throws Exception {
        InicisCancelRes inicisCancelRes =  paymentHistoryService.paymentCancel(paymentSeq, advSeq, null, adminAuth.getId());
        return new ApiResponseDto(ApiResponseCodes.SUCCESS, inicisCancelRes);
    }
}
