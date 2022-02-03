package com.exflyer.oddi.admin.api.notification.send.dao;

import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchReq;
import com.exflyer.oddi.admin.api.notification.send.dto.MemberSearchResult;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTarget;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchGroupTargetResult;
import com.exflyer.oddi.admin.api.notification.send.dto.SearchPartnerReq;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMapper {
  Page<SearchGroupTargetResult> findByGroupTargetSearchReq(SearchGroupTarget searchReq);
  Page<MemberSearchResult> findByMemberSearchReq(MemberSearchReq searchReq);
  Page<SearchPartnerResult> findAdvNowExpoPartner(SearchPartnerReq searchReq);
  List<MemberSearchResult> findByAdvMemberSearchResult(Long partnerSeq);
}
