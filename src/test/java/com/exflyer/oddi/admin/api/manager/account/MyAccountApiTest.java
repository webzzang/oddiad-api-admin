package com.exflyer.oddi.admin.api.manager.account;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModPasswordReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModReq;
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
class MyAccountApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("관리자 메뉴 조회")
  @Test
  public void findManagerMenu() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/my/menu")
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

  /*@DisplayName("관리자 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/my")
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

  /*@DisplayName("관리자 내 계정 수정")
  @Test
  public void modify() throws Exception {
    ManagerMyAccountModReq managerMyAccountModReq = new ManagerMyAccountModReq();
    managerMyAccountModReq.setName("최지순 수정");
    managerMyAccountModReq.setPhoneNumber("01012345678");
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/my")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(managerMyAccountModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("관리자 내 계정 비밀번호 변경")
  @Test
  public void modifyPassword() throws Exception {
    ManagerMyAccountModPasswordReq passwordReq = new ManagerMyAccountModPasswordReq();
    passwordReq.setNewPassword("123456");
//    passwordReq.setNewPassword("skdltmskdltm");
    passwordReq.setOldPassword("123456");
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/my/password")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(passwordReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    //    $2a$10$pKnGVhwIY/Z9VhsJwIgBsedWPoMpXv6tqtnECU4lvlqvneoy3oYTG
    //    $2a$10$beOjbI.ATuk1sjPLx4UaNeMmmMdirgRsGLD9VAhULXVE7RLLDzoXO
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

}
