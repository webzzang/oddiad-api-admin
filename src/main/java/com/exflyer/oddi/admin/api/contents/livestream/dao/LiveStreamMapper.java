package com.exflyer.oddi.admin.api.contents.livestream.dao;

import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingVodList;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartner;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartnerResult;
import com.exflyer.oddi.admin.models.Partner;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveStreamMapper {
    Page<SearchPartnerResult> findLiveStreamSearchPartner(SearchPartner searchReq);
    List<LiveStreamingVodList>findByYoutubeList();
}
