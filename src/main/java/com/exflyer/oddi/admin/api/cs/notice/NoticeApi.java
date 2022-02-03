package com.exflyer.oddi.admin.api.cs.notice;

import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeDetailResult;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchResult;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeModReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeRegReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeSearchCodes;
import com.exflyer.oddi.admin.api.cs.notice.service.NoticeService;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Notice;
import com.exflyer.oddi.admin.repository.NoticeFilesRepository;
import com.exflyer.oddi.admin.repository.NoticeRepository;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "CS 관리", protocols = "http")
@Slf4j
@RestController
public class NoticeApi {

  @Autowired
  private NoticeService noticeService;

  @Autowired
  private NoticeRepository noticeRepository;

  @Autowired
  private NoticeFilesRepository noticeFilesRepository;

  @Autowired
  private FileService fileService;


  @ApiOperation(value = "공지사항 목록 조회 사용 코드", notes = "공지사항 목록 조회 시 사용되는 코드 목록 조회 API 입니다. ")
  @GetMapping(path = "/notice/search-codes")
  @MenuValidApi(menuCode = "COS001", operation = CrudOperation.READ)
  public ApiResponseDto<NoticeSearchCodes> findCodeList() {
    NoticeSearchCodes searchCode = noticeService.findCodeForSearching();
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, searchCode);
  }

  @ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 목록 조회 API 입니다. ")
  @GetMapping(path = "/notice")
  @MenuValidApi(menuCode = "COS001", operation = CrudOperation.READ)
  public ApiResponseDto<PagingResult<NoticeListSearchResult>> find(@Validated NoticeListSearchReq noticeListSearchReq) {
    PagingResult<NoticeListSearchResult> pagingResult = noticeService.findBySearchReq(noticeListSearchReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, pagingResult);
  }

  @ApiOperation(value = "공지사항 상세 조회", notes = "공지사항 상세 조회 API 입니다. ")
  @GetMapping(path = "/notice/{seq}")
  @MenuValidApi(menuCode = "COS001", operation = CrudOperation.READ)
  public ApiResponseDto<NoticeDetailResult> findDetail(@PathVariable Long seq) throws ApiException {
    NoticeDetailResult noticeDetailResult = noticeService.findDetail(seq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, noticeDetailResult);
  }

  @ApiOperation(value = "공지사항 등록", notes = "공지사항 등록 API 입니다. ")
  @PostMapping(path = "/notice")
  @MenuValidApi(menuCode = "COS001", operation = CrudOperation.CREATE)
  public ApiResponseDto save(@Validated @RequestBody NoticeRegReq noticeRegReq, AdminAuth adminAuth) throws ApiException {
    noticeRegReq.setRegId(adminAuth.getId());
    noticeRegReq.setRegDate(LocalDateUtils.krNow());

    noticeService.save(noticeRegReq);

    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "공지사항 수정", notes = "공지사항 수정 API 입니다. ")
  @PutMapping(path = "/notice/{seq}")
  @MenuValidApi(menuCode = "COS001", operation = CrudOperation.UPDATE)
  public ApiResponseDto modify(@PathVariable Long seq, @Validated @RequestBody NoticeModReq noticeModReq, AdminAuth adminAuth) throws ApiException {

    noticeModReq.setSeq(seq);
    noticeModReq.setModId(adminAuth.getId());
    noticeService.modify(noticeModReq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

}
