package com.exflyer.oddi.admin.api.notification.history.dao;

import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryReq;
import com.exflyer.oddi.admin.api.notification.history.dto.SearchHistoryResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryMapper {
  Page<SearchHistoryResult> findByHistorySearchReq(SearchHistoryReq searchReq);
}
