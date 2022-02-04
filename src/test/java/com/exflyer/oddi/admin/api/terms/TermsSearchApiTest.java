package com.exflyer.oddi.admin.api.terms;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.terms.codes.TermsStatusCodes;
import com.exflyer.oddi.admin.api.terms.dto.TermsAddReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsModReq;
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
class TermsSearchApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("서비스 이용 약관 조회")
  @Test
  public void findServiceTerms() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/terms/service")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("서비스 이용 약관 상세 조회")
  @Test
  public void findDetailServiceTerms() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/terms/service/1")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("서비스 이용 약관 등록")
  @Test
  public void addServiceTerms() throws Exception {

    TermsAddReq termsAddReq = new TermsAddReq();
    termsAddReq.setTitle("서비스 이용 약관 1");
    termsAddReq.setContents("서비스 이용 약관 입니다.");
    termsAddReq.setAdvTerms(false);
    termsAddReq.setStatusCode(TermsStatusCodes.USING.getCode());
    termsAddReq.setVersion("1.0.0");
    termsAddReq.setMemo("서비스 이용 약관 메모");

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.post("/terms/service")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(termsAddReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("서비스 이용 약관 수정")
  @Test
  public void modServiceTerms() throws Exception {

    TermsModReq termsModReq = new TermsModReq();
    termsModReq.setTitle("서비스 이용 수정 1");
    termsModReq.setContents("서비스 이용 수정 입니다.");
    termsModReq.setAdvTerms(false);
    termsModReq.setStatusCode(TermsStatusCodes.USING.getCode());
    termsModReq.setVersion("1.0.1");
    termsModReq.setMemo("서비스 이용 약관 수정 메모");

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/terms/service/2")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(termsModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("광고 신청 약관 폐기")
  @Test
  public void garbageServiceTerms() throws Exception {

    TermsModReq termsModReq = new TermsModReq();
    termsModReq.setTitle("서비스 이용 수정 1");
    termsModReq.setContents("서비스 이용 수정 입니다.");
    termsModReq.setAdvTerms(false);
    termsModReq.setStatusCode(TermsStatusCodes.USING.getCode());
    termsModReq.setVersion("1.0.1");
    termsModReq.setMemo("서비스 이용 약관 수정 메모");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/terms/adv/garbage/16")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(termsModReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

}
