package com.exflyer.oddi.admin.api.adv.audit;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditDetailResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchReq;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditModReq;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditSearchCodes;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvRejectListResult;
import com.exflyer.oddi.admin.api.adv.audit.service.AdvAuditService;
import com.exflyer.oddi.admin.api.external.payment.dto.InicisCancelRes;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "광고 - 광고 심사", protocols = "http")
@Slf4j
@RestController
public class AdvAuditApi {

  @Autowired
  private AdvAuditService advAuditService;

  @ApiOperation(value = "광고심사 목록 조회 사용 코드", notes = "광고심사 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/advAudit/search-codes")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.READ)
  public ApiResponseDto<AdvAuditSearchCodes> findCodeList() {
    AdvAuditSearchCodes searchCode = advAuditService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "광고심사 목록 조회", notes = "광고심사 목록 조회 API 입니다. ")
  @GetMapping(path = "/advAudit")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<AdvAuditListSearchResult>> find(@Validated AdvAuditListSearchReq advAuditListSearchReq) {
    PagingResult<AdvAuditListSearchResult> pagingResult = advAuditService.findBySearchReq(advAuditListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "광고심사 상세 조회", notes = "광고심사 상세 조회 API 입니다. ")
  @GetMapping(path = "/advAudit/{seq}")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.READ)
  public ApiResponseDto<AdvAuditDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    AdvAuditDetailResult advAuditDetailResult = advAuditService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, advAuditDetailResult);
  }

  @ApiOperation(value = "광고심사 저장(메모, 파일수정등)", notes = "광고심사 저장(메모, 파일수정등) API 입니다. ")
  @PutMapping(path = "/advAudit/{seq}")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody AdvAuditModReq advAuditModReq, AdminAuth adminAuth) throws ApiException {
    advAuditModReq.setSeq(seq);
    advAuditService.modify(advAuditModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고심사 승인/보류(auditEnum 1:승인, 2:보류)", notes = "광고심사 승인/보류(auditEnum 1:승인, 2:보류) API 입니다. ")
  @PutMapping(path = "/advAudit/audit/{seq}")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.UPDATE)
  public ApiResponseDto audit(@PathVariable Long seq, @Validated @RequestBody AdvAuditModReq advAuditModReq, AdminAuth adminAuth)
      throws ApiException, IOException {
    advAuditModReq.setSeq(seq);
    advAuditModReq.setAuditId(adminAuth.getId());
    advAuditService.audit(advAuditModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "광고심사 결제취소", notes = "광고심사 결제취소 API 입니다. ")
  @PutMapping(path = "/advAudit/cancel/{advSeq}/{memberId}")
  @MenuValidApi(menuCode = "ADV001", operation = CrudOperation.UPDATE)
  public ApiResponseDto paymentCancel(@PathVariable Long advSeq, @PathVariable String memberId, AdminAuth adminAuth) throws Exception {
    InicisCancelRes inicisCancelRes =  advAuditService.paymentCancel(advSeq, memberId, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, inicisCancelRes);
  }

}
