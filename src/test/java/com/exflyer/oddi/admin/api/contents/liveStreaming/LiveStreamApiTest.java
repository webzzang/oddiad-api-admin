package com.exflyer.oddi.admin.api.contents.liveStreaming;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveScheduleModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingVodReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.SearchPartner;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class LiveStreamApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("라이브스트림 광고처 조회")
  @Test
  public void findLiveStreamSearchPartner() throws Exception {

    SearchPartner searchPartner = new SearchPartner();
//    searchPartner.setMallName("테스트");
//    searchPartner.setChannelType("PTT001");
//    searchPartner.setExcludeSeq(Arrays.asList(1L));

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/live-stream/search-partner")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(searchPartner))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("라이브스트림 상세 조회")
  @Test
  public void detail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/live-stream")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  /*@DisplayName("라이브스트림 채널 ID 변경")
  @Test
  public void promotionSave() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/live-stream/channel-id")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content("adafdfasdfas")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("라이브스트림 채널 등록(라이브채널 운영일정)")
  @Test
  public void saveLiveStreaming() throws Exception {

    LiveStreamingReq liveStreamingReq = new LiveStreamingReq();
    liveStreamingReq.setTitle("목동역 실시간스트리밍");
    liveStreamingReq.setOperationDay("20211201");
    liveStreamingReq.setStartTime("200000");
    liveStreamingReq.setEndTime("210000");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/live-stream")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(liveStreamingReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("라이브스트림 채널 등록(라이브채널 운영일정) 변경")
  @Test
  public void modifyLiveChannel() throws Exception {

    LiveStreamingReq liveStreamingReq = new LiveStreamingReq();
    liveStreamingReq.setTitle("[수정]목동역 실시간스트리밍");
    liveStreamingReq.setOperationDay("20211201");
    liveStreamingReq.setStartTime("210000");
    liveStreamingReq.setEndTime("220000");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/live-stream/live-channel/3")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(liveStreamingReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("라이브스트림 채널 등록(라이브채널 운영일정) 삭제")
  @Test
  public void deleteLiveChannel() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/live-stream/live-channel/3")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("라이브스트림 vod 변경")
  @Test
  public void modifyVod() throws Exception {

    LiveScheduleModReq liveScheduleModReq = new LiveScheduleModReq();
    liveScheduleModReq.setPartnerSeq(19L);
    liveScheduleModReq.setExpo(false);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/live-stream/vod/VVVVcG1hM3NiekJQeFFoV255VUtEQlpnLjFiOENCN1ZaSDZF")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(liveScheduleModReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("라이브스트림 vod 광고처 삭제")
  @Test
  public void deleteVod() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/live-stream/vod-partner/VVVVcG1hM3NiekJQeFFoV255VUtEQlpnLjFiOENCN1ZaSDZF")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("라이브스트림 전체 정보 변경")
  @Test
  public void modify() throws Exception {

    List lst = new ArrayList();
    List lst2 = new ArrayList();

    LiveStreamingReq liveStreamingReq = new LiveStreamingReq();
    liveStreamingReq.setTitle("선릉역 1번출구");
    liveStreamingReq.setOperationDay("20211202");
    liveStreamingReq.setStartTime("091000");
    liveStreamingReq.setEndTime("101000");

    lst.add(liveStreamingReq);
    liveStreamingReq = new LiveStreamingReq();
    liveStreamingReq.setTitle("신사역 입구");
    liveStreamingReq.setOperationDay("20211201");
    liveStreamingReq.setStartTime("080000");
    liveStreamingReq.setEndTime("150000");

    lst.add(liveStreamingReq);
    liveStreamingReq = new LiveStreamingReq();
    liveStreamingReq.setTitle("가로수길 스타벅스");
    liveStreamingReq.setOperationDay("20211201");
    liveStreamingReq.setStartTime("153000");
    liveStreamingReq.setEndTime("160000");

    lst.add(liveStreamingReq);

    LiveStreamingVodReq liveStreamingVodReq = new LiveStreamingVodReq();
    liveStreamingVodReq.setYoutubeId("VVVVcG1hM3NiekJQeFFoV255VUtEQlpnLi1kNUZSZ3QzM0Zn");
    //liveStreamingVodReq.setPartnerSeq(19L);
    liveStreamingVodReq.setExpo(true);
    lst2.add(liveStreamingVodReq);

    liveStreamingVodReq = new LiveStreamingVodReq();
    liveStreamingVodReq.setYoutubeId("VVVVcG1hM3NiekJQeFFoV255VUtEQlpnLjhObHhhalRaZnc4");
    //liveStreamingVodReq.setPartnerSeq(19L);
    liveStreamingVodReq.setExpo(true);
    lst2.add(liveStreamingVodReq);

    liveStreamingVodReq = new LiveStreamingVodReq();
    liveStreamingVodReq.setYoutubeId("VVVVcG1hM3NiekJQeFFoV255VUtEQlpnLjRsd0N0NGpVSWFj");
    //liveStreamingVodReq.setPartnerSeq(19L);
    liveStreamingVodReq.setExpo(true);
    lst2.add(liveStreamingVodReq);

    LiveStreamModReq liveStreamModReq = new LiveStreamModReq();
//    liveStreamModReq.setLiveStreamChannelId("UCUpma3sbzBPxQhWnyUKDBZg");
//    liveStreamModReq.setLiveStreamList(lst);
    liveStreamModReq.setVodList(lst2);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/live-stream")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(liveStreamModReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

}
