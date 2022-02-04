package com.exflyer.oddi.admin.api.sales;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionReq;
import com.exflyer.oddi.admin.api.sales.payment.dto.SearchPaymentHistoryReq;
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
class PaymentHistoryApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("결제내역 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/payment-history/search-codes")
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

  @DisplayName("결제내역 목록 조회")
  @Test
  public void search() throws Exception {

    SearchPaymentHistoryReq searchReq = new SearchPaymentHistoryReq();
    //searchReq.setAdvName("아름");
//    searchReq.setSearchRegStartDate("20211216");
//    searchReq.setSearchRegEndDate("20211216");
    searchReq.setSearchAdvStStartDate("20220101");
    searchReq.setSearchAdvStEndDate("20220101");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/payment-history")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(searchReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }


  @DisplayName("결제내역 목록 갯수 조회")
  @Test
  public void searchCount() throws Exception {

    SearchPaymentHistoryReq searchReq = new SearchPaymentHistoryReq();
    //searchReq.setAdvName("아름");
//    searchReq.setSearchRegStartDate("20211216");
//    searchReq.setSearchRegEndDate("20211216");
    searchReq.setSearchAdvStStartDate("20220101");
    searchReq.setSearchAdvStEndDate("20220101");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/payment-history-count")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(searchReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

}
