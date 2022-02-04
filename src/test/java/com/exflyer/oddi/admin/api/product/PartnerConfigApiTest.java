package com.exflyer.oddi.admin.api.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneDefaultFileReq;
import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneDefaultReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.models.ProductFile;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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
class PartnerConfigApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*
  @DisplayName("오디존설정 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/product/config/oddi-zone/search-codes")
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

  @DisplayName("오디존 설정 조회")
  @Test
  public void oddiDetail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/product/config/oddi-zone")
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

  /*@DisplayName("지하철 설정 상세 조회")
  @Test
  public void subwayDetail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/product/config/subway")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("오디존 설정 저장")
  @Test
  public void oddiSave() throws Exception {
    OddiZoneConfigReq oddiZoneConfigReq = new OddiZoneConfigReq();

      List<OddiZoneDefaultFileReq> lst = new ArrayList<>();
      OddiZoneDefaultFileReq oddiZoneDefaultFileReq = new OddiZoneDefaultFileReq();
      oddiZoneDefaultFileReq.setDefaultAdvFileSeq(600L);
      oddiZoneDefaultFileReq.setDefaultAdvType("AFT001");
      lst.add(oddiZoneDefaultFileReq);

      oddiZoneDefaultFileReq = new OddiZoneDefaultFileReq();
      oddiZoneDefaultFileReq.setDefaultAdvFileSeq(601L);
      oddiZoneDefaultFileReq.setDefaultAdvType("AFT001");
      lst.add(oddiZoneDefaultFileReq);

      oddiZoneDefaultFileReq = new OddiZoneDefaultFileReq();
      oddiZoneDefaultFileReq.setDefaultAdvFileSeq(602L);
      oddiZoneDefaultFileReq.setDefaultAdvType("AFT001");
      lst.add(oddiZoneDefaultFileReq);

    oddiZoneConfigReq.setDefaultAdvFileList(lst);
//    oddiZoneConfigReq.setSlotCount(11);
//    oddiZoneConfigReq.setSlotVideoTime(15);
//    oddiZoneConfigReq.setAdvStartDate(3);
//    oddiZoneConfigReq.setDesignRequest(false);
//    oddiZoneConfigReq.setAdvName("오디존 기본광고_수정");
//    oddiZoneConfigReq.setDefaultAdvFileSeq(68L);
//    oddiZoneConfigReq.setDisplayDiv("divisions_1");
//    oddiZoneConfigReq.setSideDisplayServiceCode("weather");
//    oddiZoneConfigReq.setBottomDisplayServiceCode("BDT001");


    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/product/config/od-zone")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(oddiZoneConfigReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }
/*
  @DisplayName("지하철 설정 저장")
  @Test
  public void subwaySave() throws Exception {
    OddiZoneConfigReq oddiZoneConfigReq = new OddiZoneConfigReq();

//    oddiZoneConfigReq.setSlotCount(10);
//    oddiZoneConfigReq.setSlotVideoTime(30);
    oddiZoneConfigReq.setOperationStartTime("100000");
    oddiZoneConfigReq.setOperationEndTime("220000");
//    oddiZoneConfigReq.setAdvEndDate(10);
//    oddiZoneConfigReq.setDesignRequest(true);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/product/config/subway")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(oddiZoneConfigReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("오디존 기본설정 저장")
  @Test
  public void odZoneAdvDefaultSaveSave() throws Exception {
    OddiZoneDefaultReq dddiZoneDefaultReq = new OddiZoneDefaultReq();

    dddiZoneDefaultReq.setAdvName("수정광고");
    dddiZoneDefaultReq.setDefaultAdvFileSeq(Long.valueOf(370));
    dddiZoneDefaultReq.setDefaultAdvType("ADT001");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/product/config/od-zone/defaultAdv")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(dddiZoneDefaultReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

*/

    /*@DisplayName("오디존 설정 조회")
    @Test
    public void oddiDetail2() throws Exception {
        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.get("/product/config/oddi-zone")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/
}
