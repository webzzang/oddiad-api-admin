package com.exflyer.oddi.admin.api.member.dao;

import com.exflyer.oddi.admin.api.member.dto.MemberDetailResult;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchReq;
import com.exflyer.oddi.admin.api.member.dto.MemberListSearchResult;
import com.exflyer.oddi.admin.api.member.dto.MemberTermsListResult;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

  Page<MemberListSearchResult> findBySearchReq(MemberListSearchReq searchReq);

  MemberDetailResult findDetail(String id);

  List<MemberTermsListResult> findByMemberTerms(String id);
}
