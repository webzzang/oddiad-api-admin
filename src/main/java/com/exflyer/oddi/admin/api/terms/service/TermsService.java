package com.exflyer.oddi.admin.api.terms.service;

import com.exflyer.oddi.admin.api.terms.codes.TermsStatusCodes;
import com.exflyer.oddi.admin.api.terms.dao.TermsMapper;
import com.exflyer.oddi.admin.api.terms.dto.TermsAddReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsDetailResult;
import com.exflyer.oddi.admin.api.terms.dto.TermsModReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsSearchReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsSearchResult;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Terms;
import com.exflyer.oddi.admin.repository.TermsRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TermsService {

  @Autowired
  private TermsRepository termsRepository;

  @Autowired
  private TermsMapper termsMapper;


  public PagingResult<TermsSearchResult> findTerms(TermsSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "status_code, seq desc"));
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<TermsSearchResult> result = termsMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

  @Transactional
  public void addTerms(TermsAddReq termsAddReq) throws ApiException {
    Terms terms = new Terms(termsAddReq);

    if (!terms.getType().equals(termsAddReq.getTypeCode())) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }

    // 사용 상태 코드라면 이전 사용 상태의 약관을 폐기로 만든다.
    if (termsAddReq.getStatusCode().equals(TermsStatusCodes.USING.getCode())) {
      termsRepository.updateAllNotUsingByType(termsAddReq.getTypeCode(), TermsStatusCodes.NOT_USING.getCode(), termsAddReq.getAdvTerms());
    }

    termsRepository.save(terms);
  }

  @Transactional
  public void modTerms(TermsModReq termsModReq) throws ApiException {
    Terms terms = termsRepository.findBySeqAndType(termsModReq.getSeq(), termsModReq.getTypeCode());
    if (terms == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    if (!terms.getType().equals(termsModReq.getTypeCode())) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }

    if (termsModReq.getStatusCode().equals(TermsStatusCodes.USING.getCode())) {
      termsRepository.updateAllNotUsingByType(termsModReq.getTypeCode(), TermsStatusCodes.NOT_USING.getCode(), termsModReq.getAdvTerms());
    }
    terms.setInfoByTermsModReq(termsModReq);
    termsRepository.save(terms);
  }

  @Transactional
  public void garbageTerms(Long seq, String id){
      termsRepository.updateGabageTerms(TermsStatusCodes.NOT_USING.getCode(), id, seq);
  }

  public TermsDetailResult findDetailTerms(Long seq, String typeCode) throws ApiException {
    Optional<Terms> optionalTerms = termsRepository.findById(seq);
    Terms terms = optionalTerms.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));

    if (!terms.getType().equals(typeCode)) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }

    return new TermsDetailResult(terms);

  }
}
