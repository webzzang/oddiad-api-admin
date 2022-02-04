package com.exflyer.oddi.admin.api.manager.auth.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.manager.auth.dto.SignInReq;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
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
class SignInApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  private String BASE_CONTEXT = "/signin";

  @DisplayName("관리자 로그인")
  @Test
  public void signManager() throws Exception {
    SignInReq signInReq = new SignInReq();
    signInReq.setEmail("webzzang79@gmail.com");
    signInReq.setPassword("skdltm77&&");

    // when
    MvcResult result =
      mockMvc
        .perform(
          MockMvcRequestBuilders.post(BASE_CONTEXT)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(gson.toJson(signInReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }

  /*@DisplayName("관리자 로그인 실패")
  @Test
  public void signFail() throws Exception {
    SignInReq signInReq = new SignInReq();
    signInReq.setEmail("js.y@nextnow.com");
    signInReq.setPassword("123456");

    // when
    MvcResult result =
      mockMvc
        .perform(
          MockMvcRequestBuilders.post(BASE_CONTEXT)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(gson.toJson(signInReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }*/
}
