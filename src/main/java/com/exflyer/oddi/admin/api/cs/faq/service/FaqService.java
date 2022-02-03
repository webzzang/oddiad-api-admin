package com.exflyer.oddi.admin.api.cs.faq.service;

import com.exflyer.oddi.admin.api.cs.faq.dao.FaqMapper;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqDetailResult;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchResult;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqModReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqSearchCodes;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.Faq;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.FaqRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FaqService {

  @Autowired
  private CodeRepository codeRepository;

  @Autowired
  private FaqMapper faqMapper;

  @Autowired
  private FaqRepository faqRepository;

  public FaqSearchCodes findCodeForSearching() {
    List<Code> categoryCode = codeRepository.findByGroupCode("PTT");
    List<Code> expoCode = codeRepository.findByGroupCodeAndUsable("SHO", true);
    return new FaqSearchCodes(categoryCode, expoCode);
  }

  public PagingResult<FaqListSearchResult> findBySearchReq(FaqListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "seq desc"));
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<FaqListSearchResult> result = faqMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  public FaqDetailResult findDetail(Long seq) throws ApiException {
    FaqDetailResult faqDetailResult = faqMapper.findDetail(seq);
    if (faqDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    return faqDetailResult;
  }

  public void modify(FaqModReq faqModReq) throws ApiException {
    Optional<Faq> faqOptional = faqRepository.findById(faqModReq.getSeq());
    Faq faq = faqOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    faq.setModifyReq(faqModReq);
    faqRepository.save(faq);
  }
}
