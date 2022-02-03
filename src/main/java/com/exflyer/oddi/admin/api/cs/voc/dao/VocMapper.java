package com.exflyer.oddi.admin.api.cs.voc.dao;

import com.exflyer.oddi.admin.api.cs.voc.dto.VocDetailResult;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface VocMapper {

  Page<VocListSearchResult> findBySearchReq(VocListSearchReq searchReq);

  VocDetailResult findDetail(Long seq, String answerCode, String noAnswerCode);
}
