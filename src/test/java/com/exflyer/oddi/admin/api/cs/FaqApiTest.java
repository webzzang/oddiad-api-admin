package com.exflyer.oddi.admin.api.cs;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.cs.faq.dto.FaqModReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqRegReq;
import com.exflyer.oddi.admin.api.member.dto.MemberModReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.LocalDateUtils;
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
class FaqApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("FAQ 코드 목록 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/faq/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }

  @DisplayName("FAQ 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/faq")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("FAQ 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/faq/1")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

    @DisplayName("FAQ 등록")
    @Test
    public void save() throws Exception {
        FaqRegReq faqRegReq = new FaqRegReq();
        faqRegReq.setCategoryCode("PTT001");
        faqRegReq.setExpo(true);
        faqRegReq.setTitle("FAQ 제목입니다.");
        faqRegReq.setContents("FAQ 내용입니다.");
        faqRegReq.setRegId("mod_test");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/faq")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(faqRegReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }

    @DisplayName("FAQ 수정")
    @Test
    public void modify() throws Exception {
        FaqModReq faqModReq = new FaqModReq();
        faqModReq.setCategoryCode("PTT001");
        faqModReq.setExpo(true);
        faqModReq.setTitle("FAQ 제목입니다.");
        faqModReq.setContents("FAQ 내용입니다.");
        faqModReq.setModId("mod_test");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.put("/faq/2")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(faqModReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }

}
