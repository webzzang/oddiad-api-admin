package com.exflyer.oddi.admin.api.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.product.bundle.dto.PartnerProductAddReq;
import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.models.ProductFile;
import com.exflyer.oddi.admin.models.ProductPartner;
import com.exflyer.oddi.admin.share.GsonLocalDateTimeAdapter;
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
class ProductBundleApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("묶음 상품 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/product/bundle/search-codes")
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

  @DisplayName("묶음상품 파트너 목록 조회")
  @Test
  public void findPartnerList() throws Exception {

    //Long [] partnerSeq = {1L,3L,6L};

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/product/bundle/search-partner")
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

  /*@DisplayName("묶음상품 목록 조회")
  @Test
  public void find() throws Exception {
//    OddiZonePartnerListSearchReq oddiZonePartnerListSearchReq = new OddiZonePartnerListSearchReq();
//    oddiZonePartnerListSearchReq.setSubwayCode("SLT004");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/product/bundle")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
//                .contentType(MediaType.APPLICATION_JSON)
                .param("operation", "1")
                .param("name", "가로")
                .param("mall_name", "월드")
//                .content(gson.toJson(oddiZonePartnerListSearchReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  @DisplayName("묶음상품 상세 조회")
  @Test
  public void detail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/product/bundle/1")
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



    /*@DisplayName("묶음상품 신규 저장")
    @Test
    public void add() throws Exception {

      List<Long> lst = new ArrayList<>();
      lst.add(1L);
      lst.add(14L);

      List<ProductFile> fileLst = new ArrayList<>();
      ProductFile productFile = new ProductFile();
      productFile.setFileSeq(60L);
      fileLst.add(productFile);
      productFile = new ProductFile();
      productFile.setFileSeq(61L);
      fileLst.add(productFile);
      productFile = new ProductFile();
      productFile.setFileSeq(62L);
      fileLst.add(productFile);

      ProductBundleReq addReq = new ProductBundleReq();
      addReq.setName("행리단길");
      addReq.setPartnerSeq(lst);
      addReq.setPrice(150000);
      addReq.setDescription("행리단길입니다~테스트");
      addReq.setProductFileList(fileLst);
      addReq.setOperation(true);
      addReq.setAdvCaseExpo(true);
      addReq.setMemo("메모 테스트입니다.");
      addReq.setBadgeCode("BDG001");

      MvcResult mvcResult = mockMvc
          .perform(
              MockMvcRequestBuilders.post("/product/bundle")
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



    /*@DisplayName("묶음상품 수정")
    @Test
    public void modify() throws Exception {


      List<Long> lst = new ArrayList<>();
      lst.add(6L);
      lst.add(8L);

      List<ProductFile> fileLst = new ArrayList<>();
      ProductFile productFile = new ProductFile();
      productFile.setFileSeq(60L);
      fileLst.add(productFile);
      productFile = new ProductFile();
      productFile.setFileSeq(61L);
      fileLst.add(productFile);
      productFile = new ProductFile();
      productFile.setFileSeq(68L);
      fileLst.add(productFile);

      ProductBundleReq addReq = new ProductBundleReq();
      addReq.setName("행리단길_수정");
      addReq.setPartnerSeq(lst);
      addReq.setPrice(150000);
      addReq.setDescription("행리단길입니다~테스트_수정");
      addReq.setProductFileList(fileLst);
      addReq.setOperation(true);
      addReq.setAdvCaseExpo(true);
      addReq.setMemo("메모 테스트입니다._수정");

      //test 시에는 주석해제 할것!!!
      Gson gson = new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();

      MvcResult mvcResult = mockMvc
          .perform(
              MockMvcRequestBuilders.put("/product/bundle/4")
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

}
