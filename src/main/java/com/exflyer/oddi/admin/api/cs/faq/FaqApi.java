package com.exflyer.oddi.admin.api.cs.faq;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.annotaions.OddiEncrypt;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqDetailResult;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchResult;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqModReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqRegReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqSearchCodes;
import com.exflyer.oddi.admin.api.cs.faq.service.FaqService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Faq;
import com.exflyer.oddi.admin.repository.FaqRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
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

@Api(tags = "CS 관리", protocols = "http")
@Slf4j
@RestController
public class FaqApi {

  @Autowired
  private FaqService faqService;

  @Autowired
  private FaqRepository faqRepository;


  @ApiOperation(value = "FAQ 목록 조회 사용 코드", notes = "FAQ 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/faq/search-codes")
  @MenuValidApi(menuCode = "COS003", operation = CrudOperation.READ)
  public ApiResponseDto<FaqSearchCodes> findCodeList() {
    FaqSearchCodes searchCode = faqService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "FAQ 목록 조회", notes = "FAQ 목록 조회 API 입니다. ")
  @GetMapping(path = "/faq")
  @MenuValidApi(menuCode = "COS003", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<PagingResult<FaqListSearchResult>> find(@Validated FaqListSearchReq faqListSearchReq) {
    PagingResult<FaqListSearchResult> pagingResult = faqService.findBySearchReq(faqListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "FAQ 상세 조회", notes = "FAQ 상세 조회 API 입니다. ")
  @GetMapping(path = "/faq/{seq}")
  @MenuValidApi(menuCode = "COS003", operation = CrudOperation.READ)
  //@OddiEncrypt
  public ApiResponseDto<FaqDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    FaqDetailResult faqDetailResult = faqService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, faqDetailResult);
  }

  @ApiOperation(value = "FAQ 등록", notes = "FAQ 등록 API 입니다. ")
  @PostMapping(path = "/faq")
  @MenuValidApi(menuCode = "COS003", operation = CrudOperation.CREATE)
  public ApiResponseDto save(@Validated @RequestBody FaqRegReq faqRegReq, AdminAuth adminAuth) {

    faqRegReq.setRegId(adminAuth.getId());
    faqRegReq.setRegDate(LocalDateUtils.krNow());

    Faq faq = new Faq(faqRegReq);
    faqRepository.save(faq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "FAQ 수정", notes = "FAQ 수정 API 입니다. ")
  @PutMapping(path = "/faq/{seq}")
  @MenuValidApi(menuCode = "COS003", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody FaqModReq faqModReq, AdminAuth adminAuth) throws ApiException {
    faqModReq.setSeq(seq);
    faqModReq.setModId(adminAuth.getId());
    faqService.modify(faqModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

}
