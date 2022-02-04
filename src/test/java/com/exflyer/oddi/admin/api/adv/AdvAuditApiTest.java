package com.exflyer.oddi.admin.api.adv;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditModReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.models.AdvFile;
import com.exflyer.oddi.admin.share.GsonLocalDateTimeAdapter;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
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
class AdvAuditApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

    @DisplayName("광고심사 코드 목록 조회")
    @Test
    public void findCodesForSearching() throws Exception {
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/advAudit/search-codes")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
    }

  /*

  @DisplayName("광고심사 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/advAudit")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("광고심사 상세 조회")
  @Test
  public void findDetail() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/advAudit/32")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

    /*@DisplayName("광고심사 저장")
    @Test
    public void modify() throws Exception {
      List<AdvFile> lst = new ArrayList<>();

      AdvFile advFile = new AdvFile();
      advFile.setType("AFT001");
      advFile.setAdvSeq(1L);
      advFile.setFileSeq(88L);
      advFile.setRegDate(LocalDateUtils.krNow());
      lst.add(advFile);

      advFile = new AdvFile();
      advFile.setAdvSeq(1L);
      advFile.setType("AFT001");
      advFile.setFileSeq(90L);
      advFile.setRegDate(LocalDateUtils.krNow());
      lst.add(advFile);

      advFile = new AdvFile();
      advFile.setAdvSeq(1L);
      advFile.setType("AFT001");
      advFile.setFileSeq(86L);
      advFile.setRegDate(LocalDateUtils.krNow());
      lst.add(advFile);

      AdvAuditModReq advAuditModReq = new AdvAuditModReq();
      advAuditModReq.setMemo("메모 테스트입니다.");
      advAuditModReq.setAdvFileList(lst);

      //test 시에는 주석해제 할것!!!
      Gson gson = new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();


      MvcResult mvcResult = mockMvc
      .perform(
          MockMvcRequestBuilders.put("/advAudit/1")
              .header(TokenGenerator.Header, tokenGenerator.getTestToken())
              .contentType(MediaType.APPLICATION_JSON)
              .content(gson.toJson(advAuditModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
      Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
      String responseCode = result.get("code").toString();
      AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/

    /*@DisplayName("광고심사 승인/보류")
    @Test
    public void audit() throws Exception {
        AdvAuditModReq advAuditModReq = new AdvAuditModReq();

        //승인 파라메터
//        advAuditModReq.setAuditEnum(1);

        //보류 파라메터
        advAuditModReq.setAuditEnum(2);
        advAuditModReq.setRejectionCode("RJT001");
        advAuditModReq.setRejectionReason("컨텐츠 내용 부적합으로 인한 보류!!");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.put("/advAudit/audit/516")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(advAuditModReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/
    /*@DisplayName("광고심사 결제취소")
    @Test
    public void paymentCancel() throws Exception {
      MvcResult mvcResult = mockMvc
          .perform(
              MockMvcRequestBuilders.put("/advAudit/cancel/370/M2Y4NzdiZGItM2U3NS00ZTgzLWE4NDktMjFhMTgxMDBhZDBk")
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
