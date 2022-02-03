package com.exflyer.oddi.admin.api.cs.notice.service;

import com.exflyer.oddi.admin.api.cs.notice.dao.NoticeMapper;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeDetailResult;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchResult;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeModReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeRegReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeSearchCodes;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Files;
import com.exflyer.oddi.admin.models.Notice;
import com.exflyer.oddi.admin.models.NoticeFiles;
import com.exflyer.oddi.admin.models.NoticeFilesPk;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.FilesRepository;
import com.exflyer.oddi.admin.repository.NoticeFilesRepository;
import com.exflyer.oddi.admin.repository.NoticeRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class NoticeService {

  @PersistenceContext
  EntityManager em;

  @Autowired
  private CodeRepository codeRepository;

  @Autowired
  private NoticeMapper noticeMapper;

  @Autowired
  private NoticeRepository noticeRepository;

  @Autowired
  private NoticeFilesRepository noticeFilesRepository;

  @Autowired
  private FileService fileService;

  public NoticeSearchCodes findCodeForSearching() {
    List<Code> expoCode = codeRepository.findByGroupCodeAndUsable("SHO", true);
    return new NoticeSearchCodes(expoCode);
  }

  public PagingResult<NoticeListSearchResult> findBySearchReq(NoticeListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "seq desc"));
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<NoticeListSearchResult> result = noticeMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public NoticeDetailResult findDetail(Long id) throws ApiException {
    NoticeDetailResult noticeDetailResult = noticeMapper.findDetail(id);
    if (noticeDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    //if(noticeDetailResult.getFileSeq() != null)noticeDetailResult.setFilePath(fileService.getFilePath(noticeDetailResult.getFileSeq()));

    return noticeDetailResult;
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void save(NoticeRegReq noticeRegReq) throws ApiException {

    // 공지사항 등록
    Notice notice = new Notice(noticeRegReq);
    noticeRepository.save(notice);
    em.persist(notice);

    //notice_files 매핑
    if(noticeRegReq.getFileSeq() != null) {

      NoticeFiles noticeFiles = new NoticeFiles();

      noticeFiles.setNoticeSeq(notice.getSeq());
      noticeFiles.setFileSeq(noticeRegReq.getFileSeq());
      noticeFiles.setRegDate(LocalDateUtils.krNow());

      noticeFilesRepository.save(noticeFiles);

      // 매핑여부 변경
      fileService.updateMappingDone(Arrays.asList(noticeRegReq.getFileSeq()));
    }

  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void modify(NoticeModReq noticeModReq) throws ApiException {

    Optional<Notice> noticeOptional = noticeRepository.findById(noticeModReq.getSeq());
    Notice notice = noticeOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    notice.setModifyReq(noticeModReq);
    noticeRepository.save(notice);

    //NoticeFiles noticeFiles = noticeFilesRepository.findAllByNoticeFilesPkOrderByNoticeFilesPk( NoticeFilesPk.builder().noticeSeq(noticeModReq.getSeq()).build());
    NoticeFiles noticeFiles = noticeFilesRepository.findAllByNoticeSeqOrderByNoticeSeq(noticeModReq.getSeq());
    int isFileDuplicate = noticeFilesRepository.isFileDuplicate(noticeModReq.getSeq(), noticeModReq.getFileSeq());

    // 파일을 수정하지 않고 그대로 등록하려는 경우는 삭제/저장 하지 않는다.
    if(isFileDuplicate == 0) {

      // 기존 파일이 있고 새로 파일을 등록하려는경우
      // 기존파일 DB delete, insert
      if (noticeFiles != null) {
        if (noticeFiles.getFileSeq() != null && noticeModReq.getFileSeq() != null) {
          noticeFilesRepository.delete(noticeFiles);  //notice_filses 기존 파일 삭제

          //S3 파일삭제
          fileService.delete(Arrays.asList(noticeFiles.getFileSeq()));
        }
      }

      if (noticeModReq.getFileSeq() != null) {
        //saveNoticeFile(noticeModReq);

        NoticeFiles noticeFilesDto = new NoticeFiles();

        noticeFilesDto.setNoticeSeq(noticeModReq.getSeq());
        noticeFilesDto.setFileSeq(noticeModReq.getFileSeq());
        noticeFilesDto.setRegDate(LocalDateUtils.krNow());
        noticeFilesRepository.save(noticeFilesDto);  // 새로 등록

        // 매핑여부 변경
        fileService.updateMappingDone(Arrays.asList(noticeModReq.getFileSeq()));
      }
    }
  }

  /*public void saveNoticeFile(NoticeModReq noticeModReq){
    NoticeFiles noticeFilesDto = new NoticeFiles();

    noticeFilesDto.setNoticeSeq(noticeModReq.getSeq());
    noticeFilesDto.setFileSeq(noticeModReq.getFileSeq());
    noticeFilesDto.setRegDate(LocalDateUtils.krNow());
    noticeFilesRepository.save(noticeFilesDto);  // 새로 등록
  }*/
}
