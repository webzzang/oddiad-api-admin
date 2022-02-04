package com.exflyer.oddi.admin.api.notification;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.notification.send.dto.NotificationReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
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
class NotificationApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("문자메세지 광고처 조회")
  @Test
  public void findLiveStreamSearchPartner() throws Exception {

//    SearchPartner searchPartner = new SearchPartner();
//    searchPartner.setMallName("테스트");
//    searchPartner.setChannelType("PTT001");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/send-sms/search-partner")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                //.content(gson.toJson(searchPartner))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  /*@DisplayName("문자메세지 코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/send-sms/search-codes")
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

  /*@DisplayName("문자메세지 발송그룹 검색")
  @Test
  public void findByGroupTargetSearchReq() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/send-sms/search-group-target")
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

  /*@DisplayName("회원 검색")
  @Test
  public void findByMemberSearchReq() throws Exception {
    MemberSearchReq memberSearchReq = new MemberSearchReq();
    //memberSearchReq.setEmail("zU/CAQc2B9KGz95poSB2cIV1Pcx7a1nZGhGaUDPp2MI=");
    //memberSearchReq.setName("유진");
    //memberSearchReq.setPhoneNumber("eIqQ8Qj5eWeJZfyRNgIv6w==");
    memberSearchReq.setStatusCode("CTS001");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/send-sms/search-member")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(memberSearchReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("sms발송")
  @Test
  public void sendSms() throws Exception {
    NotificationReq sendReq = new NotificationReq();
    sendReq.setName("오디존 파트너(선택)");
    sendReq.setContents("오디존 파트너(선택) 광고성 예약문자 내용입니다.");
    sendReq.setMessageSendType("NST001"); //즉시
//    sendReq.setTargetCode("NTC001"); //전체회원
//    sendReq.setMessageTypeCode("MTC001"); // 광고성
    sendReq.setMessageTypeCode("MTC002"); // 정보성
//    sendReq.setMessageSendType("NST002"); //예약
//    sendReq.setReservationDate("20211207121200");
//    sendReq.setTargetCode("NTC002"); //전체광고주
//    sendReq.setTargetCode("NTC003"); // 전체오디존 광고주
//    sendReq.setTargetCode("NTC004"); // 전체지하철 광고주
//    sendReq.setTargetCode("NTC005"); // 오디존 파트너(선택)
//    List lst = new ArrayList();
//    Map map = new HashMap();
//    map.put("partnerSeq", 35L);
//    map.put("partnerName", "뽀후니뽀글이");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 2L);
//    map.put("partnerName", "라라월드");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 3L);
//    map.put("partnerName", "mall_name 01");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 6L);
//    map.put("partnerName", "테스트 몰");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 8L);
//    map.put("partnerName", "테스트 몰");
//    lst.add(map);
//    sendReq.setPartnerList(lst);

//    sendReq.setTargetCode("NTC006"); // 지하철 파트너(선택)
//    List lst = new ArrayList();
//    Map map = new HashMap();
//    map.put("partnerSeq", 7L);
//    map.put("partnerName", "테스트 몰");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 15L);
//    map.put("partnerName", "지하철 테스트");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 16L);
//    map.put("partnerName", "지하철 테스트2");
//    lst.add(map);
//    map = new HashMap();
//    map.put("partnerSeq", 17L);
//    map.put("partnerName", "지하철 테스트3");
//    lst.add(map);
//    sendReq.setPartnerList(lst);
    sendReq.setTargetCode("NTC007"); // 회원(선택)
    List lst = new ArrayList();
    Map map = new HashMap();
    map.put("memberName", "이승훈");
    map.put("memberId", "NWRjMGM2MmEtMDlkMi00NzYwLWI1OWUtOGRiZmZjN2Y0ZTg3");
    lst.add(map);
//    map = new HashMap();
//    map.put("memberName", "감우성");
//    map.put("memberId", "6mlOYH0szxTapCNhPmsQDzCncvCjRtkzQU9OWpsKnTU=");
//    lst.add(map);
//    map = new HashMap();
//    map.put("memberName", "강주성");
//    map.put("memberId", "ex4U4KrMQQEP9oEM2wtLMw==");
//    lst.add(map);
    sendReq.setMemberIdList(lst);

//    sendReq.setTargetCode("NTC008"); // 전화번호(선택)
//    List lst = new ArrayList();
//    lst.add("01011112222");
//    lst.add("01033334444");
//    lst.add("01055556666");
//    sendReq.setSendPhoneNumberList(lst);

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.post("/send-sms")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(sendReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }






}
