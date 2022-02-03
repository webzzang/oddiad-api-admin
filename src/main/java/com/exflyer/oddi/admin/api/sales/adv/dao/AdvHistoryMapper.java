package com.exflyer.oddi.admin.api.sales.adv.dao;

import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryReq;
import com.exflyer.oddi.admin.api.sales.adv.dto.SearchAdvHistoryResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvHistoryMapper {
  Page<SearchAdvHistoryResult> findAdvHistory(SearchAdvHistoryReq searchReq);
}
