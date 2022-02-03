package com.exflyer.oddi.admin.api.cs.faq.dao;

import com.exflyer.oddi.admin.api.cs.faq.dto.FaqDetailResult;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqListSearchResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqMapper {

  Page<FaqListSearchResult> findBySearchReq(FaqListSearchReq searchReq);

  FaqDetailResult findDetail(Long seq);
}
