package com.exflyer.oddi.admin.api.cs.voc;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocDetailResult;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchResult;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocModReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocSearchCodes;
import com.exflyer.oddi.admin.api.cs.voc.service.VocService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.repository.VocRepository;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CS 관리", protocols = "http")
@Slf4j
@RestController
public class VocApi {

  @Autowired
  private VocService vocService;


  @ApiOperation(value = "1:1문의 목록 조회 사용 코드", notes = "1:1문의 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/voc/search-codes")
  @MenuValidApi(menuCode = "COS002", operation = CrudOperation.READ)
  public ApiResponseDto<VocSearchCodes> findCodeList() {
      VocSearchCodes searchCode = vocService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "1:1문의 목록 조회", notes = "1:1문의 목록 조회 API 입니다. ")
  @GetMapping(path = "/voc")
  @MenuValidApi(menuCode = "COS002", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<VocListSearchResult>> find(@Validated VocListSearchReq vocListSearchReq) {
    PagingResult<VocListSearchResult> pagingResult = vocService.findBySearchReq(vocListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "1:1문의 상세 조회", notes = "1:1문의 상세 조회 API 입니다. ")
  @GetMapping(path = "/voc/{seq}")
  @MenuValidApi(menuCode = "COS002", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<VocDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    VocDetailResult vocDetailResult = vocService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, vocDetailResult);
  }

  @ApiOperation(value = "1:1문의 답변 등록", notes = "1:1문의 수정 API 입니다. ")
  @PutMapping(path = "/voc/{seq}")
  @MenuValidApi(menuCode = "COS002", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody VocModReq vocModReq, AdminAuth adminAuth)
      throws ApiException, IOException {
    vocModReq.setSeq(seq);
    vocModReq.setAnswerRegId(adminAuth.getId());
    vocService.modify(vocModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

}
