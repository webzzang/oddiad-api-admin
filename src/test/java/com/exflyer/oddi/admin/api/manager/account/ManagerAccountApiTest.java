package com.exflyer.oddi.admin.api.manager.account;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountModReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountRegReq;
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
class ManagerAccountApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("관리자 계정 조회 코드 목록 조회")
  @Test
  public void findCodeList() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/account/codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }*/

  /*@DisplayName("관리자 계정 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/account")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
//          .param("id", " jisoon0923@gmail.com ")
          .param("searchRegStartDate", " 20211018 ")
//          .param("searchRegEndDate", "20211018")
//          .param("searchLoginStartDate", "20211001")
//          .param("searchLoginEndDate", "20211231")
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("관리자 계정 중복 여부")
  @Test
  public void isDuplication() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/account/test/duplication")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    log.info("responseCode : {}", responseCode);
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("관리자 계정 상세 조회")
  @Test
  public void findDetailById() throws Exception {

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/account/js.y@nextnow.com")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/


  /*@DisplayName("관리자 계정 비밀번호 초기화")
  @Test
  public void initPassword() throws Exception {

//    $2a$10$pKnGVhwIY/Z9VhsJwIgBsedWPoMpXv6tqtnECU4lvlqvneoy3oYTG
//    $2a$10$51rXtejtaX73ksGDofQEhOwDYUJkTCasfZIT5L47BUni.KAMNH/uS
//    $2a$10$ziXhzUjWU0f.LCjCyTWFb.iU/Lxp58s9DT/ub8fNIgZJrb9E6INAm
//    $2a$10$pKnGVhwIY/Z9VhsJwIgBsedWPoMpXv6tqtnECU4lvlqvneoy3oYTG
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/account/webzzang79@gmail.com/password-init")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("관리자 계정 수정")
  @Test
  public void modify() throws Exception {
    ManagerAccountModReq managerAccountModReq = new ManagerAccountModReq();
    managerAccountModReq.setRoleSeq(2L);
    managerAccountModReq.setMemo("메모를 수정 합니다.!");
    managerAccountModReq.setStateCode("MSC003");
    managerAccountModReq.setName("최지순 수정");
    managerAccountModReq.setPhoneNumber("01051227638");

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/account/jisoon0923@gmail.com")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(managerAccountModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/


  @DisplayName("관리자 계정 등록")
  @Test
  public void add() throws Exception {
    ManagerAccountRegReq managerAccountRegReq = new ManagerAccountRegReq();
    managerAccountRegReq.setRoleSeq(2L);
    managerAccountRegReq.setMemo("메모를 수정 합니다.!");
    managerAccountRegReq.setId("webzzang798@gmail.com");
    managerAccountRegReq.setName("이승훈");
    managerAccountRegReq.setPhoneNumber("01062677609");
    managerAccountRegReq.setStateCode("MSC001");

    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.post("/account")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(managerAccountRegReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }


}
