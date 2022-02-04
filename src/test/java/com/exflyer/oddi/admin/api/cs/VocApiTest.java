package com.exflyer.oddi.admin.api.cs;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.cs.voc.dto.VocListSearchReq;
import com.exflyer.oddi.admin.api.cs.voc.dto.VocModReq;
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
class VocApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("1:1문의 코드 목록 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/voc/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }

  @DisplayName("1:1문의 목록 조회")
  @Test
  public void findBySearReq() throws Exception {

    VocListSearchReq req = new VocListSearchReq();

    req.setAnswerType(true);

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/voc")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(gson.toJson(req))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("1:1문의 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/voc/21")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

    /*@DisplayName("1:1문의 답변등록")
    @Test
    public void modify() throws Exception {
        VocModReq vocModReq = new VocModReq();
        vocModReq.setAnswer("답변 테스트");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.put("/voc/1")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(vocModReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/

}
