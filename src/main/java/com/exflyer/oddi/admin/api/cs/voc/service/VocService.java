package com.exflyer.oddi.admin.api.cs.voc.service;

import com.exflyer.oddi.admin.api.cs.voc.code.VocCodes;
import com.exflyer.oddi.admin.api.cs.voc.dao.VocMapper;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocDetailResult;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchResult;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocModReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocSearchCodes;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Voc;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.VocRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VocService {

  @Autowired
  private CodeRepository codeRepository;
  @Autowired
  private VocMapper vocMapper;
  @Autowired
  private VocRepository vocRepository;

  public VocSearchCodes findCodeForSearching() {
    List<Code> inquiryTypeCode = codeRepository.findByGroupCodeAndUsable("IQT", true);
    List<Code> answerTypeCode = codeRepository.findByGroupCodeAndUsable("RPT", true);
    return new VocSearchCodes(inquiryTypeCode, answerTypeCode);
  }

  public PagingResult<VocListSearchResult> findBySearchReq(VocListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());

    searchReq.setAnswerCode(VocCodes.ANSWER.getCode());
    searchReq.setNoAnswerCode(VocCodes.NO_ANSWER.getCode());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "seq desc"));

    PageHelper.orderBy(searchReq.getOrderBy());
    Page<VocListSearchResult> result = vocMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public VocDetailResult findDetail(Long seq) throws ApiException {
    VocDetailResult vocDetailResult = vocMapper.findDetail(seq, VocCodes.ANSWER.getCode(), VocCodes.NO_ANSWER.getCode());

    //vocDetailResult.setFilePath(fileService.getFilePath(vocDetailResult.getFileSeq()));

    if (vocDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    return vocDetailResult;
  }

  public void modify(VocModReq vocModReq) throws ApiException, IOException {
    Optional<Voc> vocOptional = vocRepository.findById(vocModReq.getSeq());
    Voc voc = vocOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    voc.setModifyReq(vocModReq);
    voc.setUserCheck(false);
    vocRepository.save(voc);
  }

}
