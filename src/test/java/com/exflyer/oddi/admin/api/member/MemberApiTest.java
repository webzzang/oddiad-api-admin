package com.exflyer.oddi.admin.api.member;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.member.dto.MemberModReq;
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
class MemberApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("관리자 계정 조회 코드 목록 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/member/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }

  @DisplayName("회원 계정 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/member")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .param("id", "jisoon0923@gmail.com")
          .param("searchRegStartDate", "20211022")
          .param("searchRegEndDate", "20211022")
          .param("name", "최지순")
          .param("statusCode", "CTS003")
          .param("phoneNumber", "01012345678")
          .param("email", "jisoon0923@gmail.com")
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("회원 계정 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/member/NWRjMGM2MmEtMDlkMi00NzYwLWI1OWUtOGRiZmZjN2Y0ZTg3")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  /*@DisplayName("회원 계정 변경")
  @Test
  public void modify() throws Exception {
    MemberModReq memberModReq = new MemberModReq();
    memberModReq.setStateCode("CTS001");
    memberModReq.setMemo("이것은 메모 입니다.");
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/member/jisoon0923@gmail.com")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(memberModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

}
