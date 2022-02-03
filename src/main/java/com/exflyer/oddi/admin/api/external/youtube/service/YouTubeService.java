package com.exflyer.oddi.admin.api.external.youtube.service;

import com.exflyer.oddi.admin.api.external.youtube.dao.YouTubeMapper;
import com.exflyer.oddi.admin.api.external.youtube.dto.YouTubeListResult;
import com.exflyer.oddi.admin.api.external.youtube.dto.YouTubeRes;
import com.exflyer.oddi.admin.api.external.youtube.dto.YouTubeRes.Items;
import com.exflyer.oddi.admin.config.YouTubeConfig;
import com.exflyer.oddi.admin.models.Youtube;
import com.exflyer.oddi.admin.repository.YouTubeRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class YouTubeService {

    @Autowired
    private YouTubeConfig youTubeConfig;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private Gson gson;
    @Autowired
    private YouTubeRepository youTubeRepository;
    @Autowired
    private YouTubeMapper youTubeMapper;

    private String nextToken = "";

    public void requestGetYouTubePlayItem() throws IOException {
        String apiUrl = youTubeConfig.getApiUrl() + "?key="+youTubeConfig.getApiKey() +"&playlistId="+youTubeConfig.getPlayListId() +"&part="+youTubeConfig.getPart();
        apiUrl+= "&pageToken=" + nextToken;

        HttpGet httpGet = new HttpGet(apiUrl);

        HttpResponse response = httpClient.execute(httpGet);
        String resBody = EntityUtils.toString(response.getEntity());

        YouTubeRes youTubeRes = gson.fromJson(resBody, YouTubeRes.class);
        nextToken = youTubeRes.getNextpagetoken();

        int position = Integer.parseInt(youTubeRes.getItems().get(0).getSnippet().getPosition());

        // 유튜브 api 조회 총갯수와 DB에 저장된 갯수가 같지 않을시 delete, insert
//        if (youTubeRepository.findByYouTubeCnt() != youTubeRes.getPageinfo().getTotalresults()) {

            if (position == 0) youTubeRepository.deleteAll();

            List<Items> playItems = youTubeRes.getItems();
            playItems.forEach(datas -> {

                String publishedAt = datas.getSnippet().getPublishedat();

                Youtube youtube = new Youtube();
                youtube.setYoutubeId(datas.getId());
                youtube.setYoutubeTitle(datas.getSnippet().getTitle());
                youtube.setYoutubeRegDate(publishedAt.substring(0, publishedAt.indexOf("T")) + publishedAt.substring(publishedAt.indexOf("T"), publishedAt.indexOf("Z")));
                youtube.setYoutubeDescription(datas.getSnippet().getDescription());
                youtube.setYoutubeThumbnails(datas.getSnippet().getThumbnails().getHigh().getUrl());
                youtube.setYoutubePlayId(datas.getSnippet().getResourceid().getVideoid());
                youtube.setYoutubeUrl("https://www.youtube.com/watch?v="+datas.getSnippet().getResourceid().getVideoid());
                youtube.setRegDate(LocalDateUtils.nowKrDateString());

                youTubeRepository.save(youtube);
            });
//        }

        if(nextToken != null){
            requestGetYouTubePlayItem();
        }
    }

    public List<YouTubeListResult>getYouTube(Long partnerSeq){
        return youTubeMapper.getByIdYouTubePlayItems(partnerSeq);
    }
}
