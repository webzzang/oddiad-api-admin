package com.exflyer.oddi.admin.api.cs.notice.dao;

import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeDetailResult;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeListSearchResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {
  Page<NoticeListSearchResult> findBySearchReq(NoticeListSearchReq searchReq);
  NoticeDetailResult findDetail(Long seq);
}
