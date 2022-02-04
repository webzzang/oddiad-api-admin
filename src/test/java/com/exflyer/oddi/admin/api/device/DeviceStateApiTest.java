package com.exflyer.oddi.admin.api.device;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.device.dto.SearchDeviceHistoryReq;
import com.exflyer.oddi.admin.api.device.dto.SearchDeviceStateReq;
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
class DeviceStateApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("기기관리 사용 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/device/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("기기관리 리스트 조회")
  @Test
  public void findSearchDeviceState() throws Exception {

    SearchDeviceStateReq searchDeviceStateReq = new SearchDeviceStateReq();
//    searchDeviceStateReq.setDeviceLevel("DLT001");
//    searchDeviceStateReq.setDeviceName("타임");
//    searchDeviceStateReq.setDeviceType("DVT001");
//    searchDeviceStateReq.setMallName("타임");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/device")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(searchDeviceStateReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("기기관리 상태이력 리스트 조회")
  @Test
  public void findSearchDeviceHistory() throws Exception {

    SearchDeviceHistoryReq searchDeviceHistoryReq = new SearchDeviceHistoryReq();
//    searchDeviceHistoryReq.setDeviceLevel("DLT001");
//    searchDeviceHistoryReq.setDeviceState("start");
//    searchDeviceHistoryReq.setSearchRegStartDate("20211201");
//    searchDeviceHistoryReq.setSearchRegEndDate("20211210");
    searchDeviceHistoryReq.setPageNo(2);
    searchDeviceHistoryReq.setPageSize(10);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/device/0yr62c")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(searchDeviceHistoryReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("기기관리 상세 조회")
  @Test
  public void detail() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/device/info/0yr62c")
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







}
