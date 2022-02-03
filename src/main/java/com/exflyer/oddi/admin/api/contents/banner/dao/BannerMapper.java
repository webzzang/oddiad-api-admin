package com.exflyer.oddi.admin.api.contents.banner.dao;

import com.exflyer.oddi.admin.api.contents.banner.dto.BannerDetailResult;
import com.exflyer.oddi.admin.api.contents.banner.dto.BannerRegReq;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBanner;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBannerResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper {
    Page<SearchBannerResult> findSearchSideBanner(SearchBanner searchReq);
    BannerDetailResult findByBannerByFile(Long seq);
    int findBySitePopupExpoDate(BannerRegReq regReq);
}
