package com.exflyer.oddi.admin.api.partner;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.partner.dto.OddiZoneAddReq;
import com.exflyer.oddi.admin.api.partner.dto.OddiZonePartnerListSearchReq;
import com.exflyer.oddi.admin.api.partner.dto.PartnerDeviceAddReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.models.PartnerSubway;
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
class OddiZonePartnerApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  
  /*@DisplayName("코드 조회")
  @Test
  public void findCodes() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/partner/oddi-zone/codes")
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

  /*@DisplayName("오디존 목록 조회")
  @Test
  public void addOddiZone() throws Exception {
//    OddiZonePartnerListSearchReq oddiZonePartnerListSearchReq = new OddiZonePartnerListSearchReq();
//    oddiZonePartnerListSearchReq.setSubwayCode("SLT004");

    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/partner/subway")
                .header(TokenGenerator.Header, tokenGenerator.getTestToken())
//                .contentType(MediaType.APPLICATION_JSON)
                .param("subwayCode", "SLT004")
//                .content(gson.toJson(oddiZonePartnerListSearchReq))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();
    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

  @DisplayName("오디존 상세 조회")
  @Test
  public void detail() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(
            MockMvcRequestBuilders.get("/partner/oddi-zone/1")
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



    /*@DisplayName("오디존 신규 저장")
    @Test
    public void add() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("0yr62c");
        partnerDeviceAddReq.setName("타임스퀘어 1층");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("1y1sd3");
        partnerDeviceAddReq.setName("타임스퀘어 2층");
        lst.add(partnerDeviceAddReq);

        List<Long> fileLst = new ArrayList<>();
        fileLst.add(40L);
        fileLst.add(41L);
        fileLst.add(42L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("대박맛집");
        tagLst.add("대박맛집2");
        tagLst.add("대박맛집3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        //oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("서울특별시 금천구 가산동 가산디지털1로");
        oddiZoneAddReq.setDetailAddr("225 에이스가산포휴 3층 305호");
        oddiZoneAddReq.setOldAddr("서울특별시 금천구 가산동 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("설명");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("내 광고처");
        oddiZoneAddReq.setMemo("메모");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("월~토");
        oddiZoneAddReq.setOwnerName("양준성");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("요약");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/partner/oddi-zone")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(oddiZoneAddReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/


    /*@DisplayName("지하철 신규 저장")
    @Test
    public void add() throws Exception {
        List<Long> fileLst = new ArrayList<>();
        fileLst.add(40L);
        fileLst.add(41L);
        fileLst.add(42L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("대박맛집");
        tagLst.add("대박맛집2");
        tagLst.add("대박맛집3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        oddiZoneAddReq.setChannelType("PTT002");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("서울특별시 금천구 가산동 가산디지털1로");
        oddiZoneAddReq.setDetailAddr("225 에이스가산포휴 3층 305호");
        oddiZoneAddReq.setOldAddr("서울특별시 금천구 가산동 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("설명");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("지하철 신규저장");
        oddiZoneAddReq.setMemo("메모");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("월~토");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("요약");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/partner/subway")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(oddiZoneAddReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/



    /*@DisplayName("지하철 수정")
    @Test
    public void modify() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("ycl6hl");
        partnerDeviceAddReq.setName("첫번째 장비 테스트_수정");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("wo2vel");
        partnerDeviceAddReq.setName("두번째 장비 테스트_수정");
        lst.add(partnerDeviceAddReq);

        List<PartnerSubway> lst2 = new ArrayList<>();

        PartnerSubway partnerSubway = new PartnerSubway();
        partnerSubway.setSubwayCode("SLT001");
        partnerSubway.setPartnerSeq(7L);
        partnerSubway.setRegDate(LocalDateUtils.krNow());
        lst2.add(partnerSubway);

        partnerSubway = new PartnerSubway();
        partnerSubway.setSubwayCode("SLT002");
        partnerSubway.setPartnerSeq(7L);
        partnerSubway.setRegDate(LocalDateUtils.krNow());
        lst2.add(partnerSubway);

        List<Long> fileLst = new ArrayList<>();
        fileLst.add(40L);
        fileLst.add(43L);
        fileLst.add(42L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("인스타그램 맛집12");
        tagLst.add("인스타그램 맛집22");
        tagLst.add("인스타그램 맛집32");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setAddr("서울특별시 금천구 가산동 가산디지털1로");
        oddiZoneAddReq.setDetailAddr("225 에이스가산포휴 3층 305호");
        oddiZoneAddReq.setOldAddr("서울특별시 금천구 가산동 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("설명_수정");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("테스트 몰");
        oddiZoneAddReq.setMemo("메모_수정");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("월~토");
        oddiZoneAddReq.setOwnerName("양준성");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("요약");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");
        oddiZoneAddReq.setSubwayList(lst2);

        //test 시에는 주석해제 할것!!!
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.put("/partner/subway/7")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(oddiZoneAddReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/


    /*@DisplayName("오디존 수정")
    @Test
    public void modify() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("ycl6hl");
        partnerDeviceAddReq.setName("[수정]첫번째 장비 테스트_수정");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("wo2vel");
        partnerDeviceAddReq.setName("[수정]두번째 장비 테스트_수정");
        lst.add(partnerDeviceAddReq);

        List<Long> fileLst = new ArrayList<>();
        fileLst.add(42L);
        fileLst.add(41L);
        fileLst.add(40L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("[수정]대박맛집");
        tagLst.add("[수정]대박맛집2");
        tagLst.add("[수정]대박맛집3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        //oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("서울특별시 금천구 가산동 가산디지털1로");
        oddiZoneAddReq.setDetailAddr("225 에이스가산포휴 3층 305호");
        oddiZoneAddReq.setOldAddr("서울특별시 금천구 가산동 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("설명");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("[수정]내 광고처");
        oddiZoneAddReq.setMemo("메모");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("월~토");
        oddiZoneAddReq.setOwnerName("양준성");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("요약");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");

        //test 시에는 주석해제 할것!!!
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();

        MvcResult mvcResult = mockMvc
            .perform(
                MockMvcRequestBuilders.put("/partner/oddi-zone/26")
                    .header(TokenGenerator.Header, tokenGenerator.getTestToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(oddiZoneAddReq))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        String responseCode = result.get("code").toString();
        AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
    }*/

}
