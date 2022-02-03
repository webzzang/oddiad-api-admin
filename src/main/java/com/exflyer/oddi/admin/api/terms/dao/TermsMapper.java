package com.exflyer.oddi.admin.api.terms.dao;

import com.exflyer.oddi.admin.api.terms.dto.TermsSearchReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsSearchResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsMapper {

  Page<TermsSearchResult> findBySearchReq(TermsSearchReq searchReq);
}
