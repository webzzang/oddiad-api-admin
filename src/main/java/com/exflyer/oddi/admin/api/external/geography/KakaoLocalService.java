package com.exflyer.oddi.admin.api.external.geography;

import com.exflyer.oddi.admin.api.external.geography.config.KakaoLocalConfig;
import com.exflyer.oddi.admin.api.external.geography.dto.KakaoCoordinates;
import com.exflyer.oddi.admin.api.external.geography.dto.KakaoLocalResult;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KakaoLocalService {

  @Autowired
  private HttpClient httpClient;

  @Autowired
  private KakaoLocalConfig kakaoLocalConfig;

  @Autowired
  private Gson gsonUnderScore;


  public KakaoCoordinates findCoordinatesByAddress(String addr){

    KakaoLocalResult localResult;
    try {
      localResult = findDetail(addr);
    } catch (Exception e) {
      log.error("kakao local api exception", e);
      return new KakaoCoordinates();
    }
    return new KakaoCoordinates(localResult);

  }

  private KakaoLocalResult findDetail(String addr) throws IOException, URISyntaxException, ApiException {

    HttpGet httpGet = new HttpGet(kakaoLocalConfig.getHost());
    httpGet.setHeader("Authorization", " KakaoAK " + kakaoLocalConfig.getRestApiKey());
    URI uri = new URIBuilder(httpGet.getURI())
      .addParameter("analyze_type", "exact")
      .addParameter("page", "1")
      .addParameter("size", "1")
      .addParameter("query", addr)
      .build();
    httpGet.setURI(uri);
    HttpResponse response = httpClient.execute(httpGet);

    String responseString = EntityUtils.toString(response.getEntity());

    KakaoLocalResult kakaoLocalResult = gsonUnderScore.fromJson(responseString, KakaoLocalResult.class);

    EntityUtils.consumeQuietly(response.getEntity());

    return kakaoLocalResult;
  }
}
