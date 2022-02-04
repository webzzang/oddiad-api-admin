package com.exflyer.oddi.admin.api.adv;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeDetailSearchReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeExpoReq;
import com.exflyer.oddi.admin.api.adv.make.dto.AdvMakeModReq;
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
class AdvMakeApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  @DisplayName("지하철 운영시간 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/advMake/subway")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }

  /*@DisplayName("광고편성 코드 목록 조회")
  @Test
  public void findCodesForSearching() throws Exception {
    mockMvc
      .perform(
        MockMvcRequestBuilders.get("/advMake/search-codes")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
  }

  @DisplayName("광고편성 목록 조회")
  @Test
  public void findBySearReq() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/advMake")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
            .param("channelType", "PTT001")
            .param("mallName", "테스트")
            .param("deviceName", "첫번째")
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("광고편성 상세 조회")
  @Test
  public void findDetail() throws Exception {

    MvcResult mvcResult = mockMvc
      .perform(
        //MockMvcRequestBuilders.get("/advMake/1/55wlqc")
          //MockMvcRequestBuilders.get("/advMake/1/3n3ldp")
          MockMvcRequestBuilders.get("/advMake/35/tmwwej")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/
  /*@DisplayName("지하철 광고편성 저장")
    @Test
    public void modify() throws Exception {
      List<AdvFile> lst = new ArrayList<>();
      List<AdvMakeExpoReq> lst2 = new ArrayList<>();

      AdvFile advFile = new AdvFile();
      advFile.setType("AFT001");
      advFile.setAdvSeq(57L);
      advFile.setFileSeq(603L);
      advFile.setRegDate(LocalDateUtils.krNow());
      advFile.setViewOrder(1);
      lst.add(advFile);

    advFile = new AdvFile();
    advFile.setAdvSeq(58L);
    advFile.setType("AFT001");
    advFile.setFileSeq(604L);
    advFile.setRegDate(LocalDateUtils.krNow());
    advFile.setViewOrder(2);
    lst.add(advFile);

    advFile = new AdvFile();
    advFile.setAdvSeq(188L);
    advFile.setType("AFT001");
    advFile.setFileSeq(605L);
    advFile.setRegDate(LocalDateUtils.krNow());
    advFile.setViewOrder(3);
    lst.add(advFile);

      AdvMakeExpoReq advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(57L);
      advMakeExpoReq.setExpo(true);
      advMakeExpoReq.setViewOrder(1);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(58L);
      advMakeExpoReq.setExpo(true);
      advMakeExpoReq.setViewOrder(2);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(188L);
      advMakeExpoReq.setExpo(false);
      advMakeExpoReq.setViewOrder(3);
      lst2.add(advMakeExpoReq);

      AdvMakeModReq advMakeModReq = new AdvMakeModReq();
      advMakeModReq.setDisplayDiv("divisions_2");
      advMakeModReq.setSideContentsType("weather");
      advMakeModReq.setAdvFileList(lst);
      advMakeModReq.setAdvExpoList(lst2);
      advMakeModReq.setDefaultAdvExpo(false);
      advMakeModReq.setChannelType("PTT002");

      Gson gson = new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();


      MvcResult mvcResult = mockMvc
      .perform(
          MockMvcRequestBuilders.put("/advMake/7/null")
              .header(TokenGenerator.Header, tokenGenerator.getTestToken())
              .contentType(MediaType.APPLICATION_JSON)
              .content(gson.toJson(advMakeModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
      Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
      String responseCode = result.get("code").toString();
      AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/

    /*@DisplayName("오디존 광고편성 저장")
    @Test
    public void modify() throws Exception {
      List<AdvFile> lst = new ArrayList<>();
      List<AdvMakeExpoReq> lst2 = new ArrayList<>();

      AdvFile advFile = new AdvFile();
      advFile.setType("AFT001");
      advFile.setAdvSeq(75L);
      advFile.setFileSeq(603L);
      advFile.setRegDate(LocalDateUtils.krNow());
      advFile.setViewOrder(1);
      lst.add(advFile);

      advFile = new AdvFile();
      advFile.setAdvSeq(76L);
      advFile.setType("AFT001");
      advFile.setFileSeq(604L);
      advFile.setRegDate(LocalDateUtils.krNow());
      advFile.setViewOrder(2);
      lst.add(advFile);

      advFile = new AdvFile();
      advFile.setAdvSeq(60L);
      advFile.setType("AFT001");
      advFile.setFileSeq(605L);
      advFile.setRegDate(LocalDateUtils.krNow());
      advFile.setViewOrder(3);
      lst.add(advFile);

      AdvMakeExpoReq advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(49L);
      advMakeExpoReq.setExpo(true);
      advMakeExpoReq.setViewOrder(1);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(60L);
      advMakeExpoReq.setExpo(true);
      advMakeExpoReq.setViewOrder(2);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(75L);
      advMakeExpoReq.setExpo(false);
      advMakeExpoReq.setViewOrder(3);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(76L);
      advMakeExpoReq.setExpo(false);
      advMakeExpoReq.setViewOrder(4);
      lst2.add(advMakeExpoReq);

      advMakeExpoReq = new AdvMakeExpoReq();
      advMakeExpoReq.setAdvSeq(78L);
      advMakeExpoReq.setExpo(false);
      advMakeExpoReq.setViewOrder(5);
      lst2.add(advMakeExpoReq);

      AdvMakeModReq advMakeModReq = new AdvMakeModReq();
      advMakeModReq.setDisplayDiv("divisions_2");
      advMakeModReq.setSideContentsType("weather");
      advMakeModReq.setAdvFileList(lst);
      advMakeModReq.setAdvExpoList(lst2);
      advMakeModReq.setDefaultAdvExpo(false);
      advMakeModReq.setChannelType("PTT001");

      Gson gson = new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();


      MvcResult mvcResult = mockMvc
      .perform(
          MockMvcRequestBuilders.put("/advMake/1/3n3ldp")
              .header(TokenGenerator.Header, tokenGenerator.getTestToken())
              .contentType(MediaType.APPLICATION_JSON)
              .content(gson.toJson(advMakeModReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
      Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
      String responseCode = result.get("code").toString();
      AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/

  /*@DisplayName("광고편성 기기송출")
  @Test
  public void modify() throws Exception {
    List<AdvFile> lst = new ArrayList<>();
    List<AdvMakeExpoReq> lst2 = new ArrayList<>();

    AdvFile advFile = new AdvFile();
    advFile.setType("AFT001");
    advFile.setAdvSeq(75L);
    advFile.setFileSeq(603L);
    advFile.setRegDate(LocalDateUtils.krNow());
    advFile.setViewOrder(1);
    lst.add(advFile);

    advFile = new AdvFile();
    advFile.setAdvSeq(76L);
    advFile.setType("AFT001");
    advFile.setFileSeq(604L);
    advFile.setRegDate(LocalDateUtils.krNow());
    advFile.setViewOrder(2);
    lst.add(advFile);

    advFile = new AdvFile();
    advFile.setAdvSeq(60L);
    advFile.setType("AFT001");
    advFile.setFileSeq(605L);
    advFile.setRegDate(LocalDateUtils.krNow());
    advFile.setViewOrder(3);
    lst.add(advFile);

    AdvMakeExpoReq advMakeExpoReq = new AdvMakeExpoReq();
    advMakeExpoReq.setAdvSeq(49L);
    advMakeExpoReq.setExpo(true);
    advMakeExpoReq.setViewOrder(1);
    lst2.add(advMakeExpoReq);

    advMakeExpoReq = new AdvMakeExpoReq();
    advMakeExpoReq.setAdvSeq(60L);
    advMakeExpoReq.setExpo(true);
    advMakeExpoReq.setViewOrder(2);
    lst2.add(advMakeExpoReq);

    advMakeExpoReq = new AdvMakeExpoReq();
    advMakeExpoReq.setAdvSeq(75L);
    advMakeExpoReq.setExpo(false);
    advMakeExpoReq.setViewOrder(3);
    lst2.add(advMakeExpoReq);

    advMakeExpoReq = new AdvMakeExpoReq();
    advMakeExpoReq.setAdvSeq(76L);
    advMakeExpoReq.setExpo(false);
    advMakeExpoReq.setViewOrder(4);
    lst2.add(advMakeExpoReq);

    advMakeExpoReq = new AdvMakeExpoReq();
    advMakeExpoReq.setAdvSeq(78L);
    advMakeExpoReq.setExpo(false);
    advMakeExpoReq.setViewOrder(5);
    lst2.add(advMakeExpoReq);

    AdvMakeModReq advMakeModReq = new AdvMakeModReq();
    advMakeModReq.setDisplayDiv("divisions_2");
    advMakeModReq.setSideContentsType("weather");
    advMakeModReq.setAdvFileList(lst);
    advMakeModReq.setAdvExpoList(lst2);
    advMakeModReq.setDefaultAdvExpo(false);
    advMakeModReq.setChannelType("PTT001");

    Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();


    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/advMake/1/3n3ldp")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(advMakeModReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/


}
