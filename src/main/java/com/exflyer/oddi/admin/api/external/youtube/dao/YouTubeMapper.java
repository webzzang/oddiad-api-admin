package com.exflyer.oddi.admin.api.external.youtube.dao;

import com.exflyer.oddi.admin.api.external.youtube.dto.YouTubeListResult;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeMapper {
    List<YouTubeListResult> getByIdYouTubePlayItems(Long partnerSeq);
}
