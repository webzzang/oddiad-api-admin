package com.exflyer.oddi.admin.api.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.product.partnerConfig.dto.OddiZoneConfigReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionCouponReq;
import com.exflyer.oddi.admin.api.product.promotion.dto.PromotionReq;
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
class PromotionApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("프로모션 쿠폰발행정보 카운트 조회")
  @Test
  public void couponDetail() throws Exception {
    PromotionCouponReq promotionCouponReq = new PromotionCouponReq();
    promotionCouponReq.setUsable(true);
    promotionCouponReq.setSeq(2L);
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/promotion/coupon-count")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(promotionCouponReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("프로모션 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/promotion/search-codes")
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

  @DisplayName("프로모션 목록 조회")
  @Test
  public void search() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/promotion")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
//                .param("name", "일반")
//                .param("searchRegStartDate", "20211101")
//                .param("searchRegEndDate", "20211105")
                .param("searchExpiredStartDate", "20211201")
                .param("searchExpiredEndDate", "20220101")
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("프로모션 기본정보 상세 조회")
  @Test
  public void detail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/promotion/2")
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

  /*@DisplayName("프로모션 쿠폰발행정보 상세 조회")
  @Test
  public void couponDetail() throws Exception {
    PromotionCouponReq promotionCouponReq = new PromotionCouponReq();
    promotionCouponReq.setSeq(2L);
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/promotion/coupon")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(promotionCouponReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("프로모션 등록")
  @Test
  public void promotionSave() throws Exception {
    PromotionReq addReq = new PromotionReq();
    addReq.setCouponCnt(10000);
    addReq.setExpiredDate("20211231");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/promotion/coupon/2")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("프로모션 등록화면 - 쿠폰발행")
  @Test
  public void addPromotionCoupon() throws Exception {
    PromotionReq addReq = new PromotionReq();
    addReq.setName("[프로모션 등록 및 쿠폰등록 같이]머스타드 가입자 할인 일반 쿠폰");
    addReq.setDiscountPrice(30000);
    addReq.setContents("내용");
    addReq.setExpiredDate("20211111");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/promotion/coupon")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("프로모션 수정화면 - 쿠폰발행")
  @Test
  public void modifiPromotionCoupon() throws Exception {
    PromotionReq addReq = new PromotionReq();
    addReq.setExpiredDate("20211212");
    addReq.setCouponCnt(1);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/promotion/coupon/25")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  /*@DisplayName("프로모션 수정화면 - 기본수정")
  @Test
  public void modifiPromotion() throws Exception {
    PromotionReq addReq = new PromotionReq();
    addReq.setName("[수정] 머스타드 가입자 할인 일반 쿠폰");
    addReq.setDiscountPrice(18000);
    addReq.setContents("[수정] 내용");
    addReq.setExpiredDate("20220101");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.put("/promotion/6")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("가입자 쿠폰관리 - 저장")
  @Test
  public void addMemberCoupon1() throws Exception {
    PromotionReq addReq = new PromotionReq();
    addReq.setName("[가입자쿠폰] 회원가입 쿠폰");
    addReq.setDiscountPrice(1000);
    addReq.setMemberCouponCode("MCT002");
    addReq.setMemberCouponExpiredDay(7);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/promotion/member/coupon")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(addReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("가입자 쿠폰조회")
  @Test
  public void addMemberCoupon() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/promotion/member/coupon")
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
