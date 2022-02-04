package com.exflyer.oddi.admin.api.cs;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeRegReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeModReq;
import com.exflyer.oddi.admin.api.cs.notice.dto.NoticeRegReq;
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
class NoticeApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("공지사항 코드 목록 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/notice/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }

  @DisplayName("공지사항 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/notice")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("공지사항 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/notice/1")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("공지사항 등록")
  @Test
  public void save() throws Exception {
    NoticeRegReq noticeRegReq = new NoticeRegReq();
    noticeRegReq.setTitle("FAQ 제목입니다.");
    noticeRegReq.setContents("FAQ 내용입니다.");
    noticeRegReq.setFileSeq(1L);
    noticeRegReq.setExpo(true);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/notice")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(noticeRegReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/
  
  @DisplayName("공지사항 수정")
  @Test
  public void modify() throws Exception {
    NoticeModReq noticeModReq = new NoticeModReq();
    noticeModReq.setExpo(true);
    noticeModReq.setTitle("공지사항 제목입니다.11111");
    noticeModReq.setContents("공지사항 내용입니다.111111");
    noticeModReq.setFileSeq(46L);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/notice/1")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(noticeModReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

}
