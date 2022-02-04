package com.exflyer.oddi.admin.api.contents.banner;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.contents.banner.dto.BannerRegReq;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBanner;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamModReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingReq;
import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingVodReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import java.util.ArrayList;
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
class SideBannerApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("광고판 우측 배너관리 사용 코드 조회")
  @Test
  public void findCodeList() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/side-banner/search-codes")
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

  @DisplayName("광고판 우측 배너 목록 조회")
  @Test
  public void findSearchSideBanner() throws Exception {

    SearchBanner searchBanner = new SearchBanner();
    searchBanner.setBannerName("탐의");
    searchBanner.setSearchStExpoStartDate("20211001");
    searchBanner.setSearchStExpoEndDate("20211231");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/side-banner/search")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                //.content(gson.toJson(searchBanner))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("광고판 우측 배너관리 상세 조회")
  @Test
  public void findDetail() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/side-banner/1")
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

  @DisplayName("광고판 우측 배너관리 등록")
  @Test
  public void save() throws Exception {

    BannerRegReq regReq = new BannerRegReq();
    regReq.setName("웹 우측 배너 등록");
    regReq.setFileSeq(5L);
    regReq.setUsable(true);
    regReq.setExpoStartDate("20211201");
    regReq.setExpoEndDate("20211231");
    regReq.setMemo("새로운 등록 메모입니다.");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/side-banner")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(regReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("광고판 우측 배너관리 수정")
  @Test
  public void modify() throws Exception {

    BannerRegReq modReq = new BannerRegReq();
    modReq.setName("[수정] 웹 우측배너 수정");
    modReq.setFileSeq(6L);
    modReq.setUsable(true);
    modReq.setExpoStartDate("20211211");
    modReq.setExpoEndDate("20211231");
    modReq.setMemo("새로운 수정 메모입니다.");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/side-banner/3")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(modReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

}
