package com.exflyer.oddi.admin.api.contents.banner;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.contents.banner.dto.BannerRegReq;
import com.exflyer.oddi.admin.api.contents.banner.dto.SearchBanner;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
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
class sitePopupApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("사이트 팝업관리 사용 코드 조회")
  @Test
  public void findCodeList() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/site-popup/search-codes")
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

  @DisplayName("사이트 팝업관리 목록 조회")
  @Test
  public void findSearchSideBanner() throws Exception {

    SearchBanner searchBanner = new SearchBanner();
    searchBanner.setBannerName("탐의");
    searchBanner.setSearchStExpoStartDate("20211001");
    searchBanner.setSearchStExpoEndDate("20211231");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/site-popup/search")
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
  }*/

  @DisplayName("사이트 팝업관리 상세 조회")
  @Test
  public void findDetail() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/site-popup/21")
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

  @DisplayName("사이트 팝업관리 등록")
  @Test
  public void save() throws Exception {

    BannerRegReq regReq = new BannerRegReq();
    regReq.setName("사이트 팝업 신규등록");
    regReq.setFileSeq(489L);
    regReq.setUsable(true);
    regReq.setExpoStartDate("20210101");
    regReq.setExpoEndDate("20220103");
    regReq.setMemo("새로운 등록 메모입니다.");
    regReq.setRouterLink("https://google.co.kr");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/site-popup")
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

  /*@DisplayName("사이트 팝업관리 수정")
  @Test
  public void modify() throws Exception {

    BannerRegReq modReq = new BannerRegReq();
    modReq.setName("[수정] 사이트 팝업 수정");
    modReq.setFileSeq(9L);
    modReq.setUsable(true);
    modReq.setExpoStartDate("20211212");
    modReq.setExpoEndDate("20211231");
    modReq.setMemo("새로운 수정 메모입니다.");
    modReq.setRouterLink("https://google.co.kr");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/site-popup/8")
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
  }*/

}
