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

  
  /*@DisplayName("์ฝ๋ ์กฐํ")
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

  /*@DisplayName("์ค๋์กด ๋ชฉ๋ก ์กฐํ")
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

  @DisplayName("์ค๋์กด ์์ธ ์กฐํ")
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



    /*@DisplayName("์ค๋์กด ์?๊ท ์?์ฅ")
    @Test
    public void add() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("0yr62c");
        partnerDeviceAddReq.setName("ํ์์คํ์ด 1์ธต");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("1y1sd3");
        partnerDeviceAddReq.setName("ํ์์คํ์ด 2์ธต");
        lst.add(partnerDeviceAddReq);

        List<Long> fileLst = new ArrayList<>();
        fileLst.add(40L);
        fileLst.add(41L);
        fileLst.add(42L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("๋๋ฐ๋ง์ง");
        tagLst.add("๋๋ฐ๋ง์ง2");
        tagLst.add("๋๋ฐ๋ง์ง3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        //oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ ๊ฐ์ฐ๋์งํธ1๋ก");
        oddiZoneAddReq.setDetailAddr("225 ์์ด์ค๊ฐ์ฐํฌํด 3์ธต 305ํธ");
        oddiZoneAddReq.setOldAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("์ค๋ช");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("๋ด ๊ด๊ณ?์ฒ");
        oddiZoneAddReq.setMemo("๋ฉ๋ชจ");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("์~ํ?");
        oddiZoneAddReq.setOwnerName("์์ค์ฑ");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("์์ฝ");
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


    /*@DisplayName("์งํ์ฒ? ์?๊ท ์?์ฅ")
    @Test
    public void add() throws Exception {
        List<Long> fileLst = new ArrayList<>();
        fileLst.add(40L);
        fileLst.add(41L);
        fileLst.add(42L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("๋๋ฐ๋ง์ง");
        tagLst.add("๋๋ฐ๋ง์ง2");
        tagLst.add("๋๋ฐ๋ง์ง3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        oddiZoneAddReq.setChannelType("PTT002");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ ๊ฐ์ฐ๋์งํธ1๋ก");
        oddiZoneAddReq.setDetailAddr("225 ์์ด์ค๊ฐ์ฐํฌํด 3์ธต 305ํธ");
        oddiZoneAddReq.setOldAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("์ค๋ช");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("์งํ์ฒ? ์?๊ท์?์ฅ");
        oddiZoneAddReq.setMemo("๋ฉ๋ชจ");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("์~ํ?");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("์์ฝ");
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



    /*@DisplayName("์งํ์ฒ? ์์?")
    @Test
    public void modify() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("ycl6hl");
        partnerDeviceAddReq.setName("์ฒซ๋ฒ์งธ ์ฅ๋น ํ์คํธ_์์?");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("wo2vel");
        partnerDeviceAddReq.setName("๋๋ฒ์งธ ์ฅ๋น ํ์คํธ_์์?");
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
        tagLst.add("์ธ์คํ๊ทธ๋จ ๋ง์ง12");
        tagLst.add("์ธ์คํ๊ทธ๋จ ๋ง์ง22");
        tagLst.add("์ธ์คํ๊ทธ๋จ ๋ง์ง32");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ ๊ฐ์ฐ๋์งํธ1๋ก");
        oddiZoneAddReq.setDetailAddr("225 ์์ด์ค๊ฐ์ฐํฌํด 3์ธต 305ํธ");
        oddiZoneAddReq.setOldAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("์ค๋ช_์์?");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("ํ์คํธ ๋ชฐ");
        oddiZoneAddReq.setMemo("๋ฉ๋ชจ_์์?");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("์~ํ?");
        oddiZoneAddReq.setOwnerName("์์ค์ฑ");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("์์ฝ");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");
        oddiZoneAddReq.setSubwayList(lst2);

        //test ์์๋ ์ฃผ์ํด์? ํ?๊ฒ!!!
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


    /*@DisplayName("์ค๋์กด ์์?")
    @Test
    public void modify() throws Exception {
        List<PartnerDeviceAddReq> lst = new ArrayList<>();
        PartnerDeviceAddReq partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("ycl6hl");
        partnerDeviceAddReq.setName("[์์?]์ฒซ๋ฒ์งธ ์ฅ๋น ํ์คํธ_์์?");
        lst.add(partnerDeviceAddReq);

        partnerDeviceAddReq = new PartnerDeviceAddReq();
        partnerDeviceAddReq.setDeviceId("wo2vel");
        partnerDeviceAddReq.setName("[์์?]๋๋ฒ์งธ ์ฅ๋น ํ์คํธ_์์?");
        lst.add(partnerDeviceAddReq);

        List<Long> fileLst = new ArrayList<>();
        fileLst.add(42L);
        fileLst.add(41L);
        fileLst.add(40L);

        List<String> tagLst = new ArrayList<>();
        tagLst.add("[์์?]๋๋ฐ๋ง์ง");
        tagLst.add("[์์?]๋๋ฐ๋ง์ง2");
        tagLst.add("[์์?]๋๋ฐ๋ง์ง3");

        OddiZoneAddReq oddiZoneAddReq = new OddiZoneAddReq();
        //oddiZoneAddReq.setDeviceId(lst);
        oddiZoneAddReq.setChannelType("PTT001");
        oddiZoneAddReq.setRegId("test");
        oddiZoneAddReq.setAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ ๊ฐ์ฐ๋์งํธ1๋ก");
        oddiZoneAddReq.setDetailAddr("225 ์์ด์ค๊ฐ์ฐํฌํด 3์ธต 305ํธ");
        oddiZoneAddReq.setOldAddr("์์ธํน๋ณ์ ๊ธ์ฒ๊ตฌ ๊ฐ์ฐ๋ 517-4");
        oddiZoneAddReq.setAdvCaseExpo(false);
        oddiZoneAddReq.setBadgeCode("BDG001");
        oddiZoneAddReq.setDayExpoCount(2);
        oddiZoneAddReq.setDescription("์ค๋ช");
        oddiZoneAddReq.setFileSeq(fileLst);
        oddiZoneAddReq.setMallName("[์์?]๋ด ๊ด๊ณ?์ฒ");
        oddiZoneAddReq.setMemo("๋ฉ๋ชจ");
        oddiZoneAddReq.setOperation(true);
        oddiZoneAddReq.setOperationEndTime("220000");
        oddiZoneAddReq.setOperationStartTime("080000");
        oddiZoneAddReq.setOperationWeek("์~ํ?");
        oddiZoneAddReq.setOwnerName("์์ค์ฑ");
        oddiZoneAddReq.setOwnerPhoneNumber("010-1234-5678");
        oddiZoneAddReq.setSlotPrice(12000);
        oddiZoneAddReq.setSlotVideoTime(12);
        oddiZoneAddReq.setSummary("์์ฝ");
        oddiZoneAddReq.setTags(tagLst);
        oddiZoneAddReq.setTotalSlot(10);
        oddiZoneAddReq.setZipcode("123456");

        //test ์์๋ ์ฃผ์ํด์? ํ?๊ฒ!!!
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
