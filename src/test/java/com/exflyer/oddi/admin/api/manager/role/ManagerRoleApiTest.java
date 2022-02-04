package com.exflyer.oddi.admin.api.manager.role;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.exflyer.oddi.admin.api.manager.role.dto.RoleAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleMenuAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleModReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleNotificationAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleSubMenuAddReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.share.TokenGenerator;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
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
class ManagerRoleApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Autowired
  private TokenGenerator tokenGenerator;

  /*@DisplayName("관리자 권한 조회")
  @Test
  public void findRole() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/manager/role")
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

  @DisplayName("관리자 권한 상세 조회")
  @Test
  public void findDetailRole() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/manager/role/2")
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

  /*@DisplayName("관리자 권한 초기 정보")
  @Test
  public void findInitInfo() throws Exception {
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.get("/manager/role/init-info")
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

  @DisplayName("관리자 권한 생성")
  @Test
  public void addRole() throws Exception {
    List<RoleNotificationAddReq> lst = new ArrayList<>();
    RoleNotificationAddReq roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT001");
    roleNotificationAddReq.setDevicePush(true);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT002");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT003");
    roleNotificationAddReq.setDevicePush(true);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT004");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT005");
    roleNotificationAddReq.setDevicePush(true);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT006");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);

    RoleAddReq roleAddReq = new RoleAddReq();
    roleAddReq.setName("이름212");
    roleAddReq.setMemo("memo212");
    roleAddReq.setUsable(true);
    roleAddReq.setManagerNotificationList(lst);

    List<RoleMenuAddReq> roleMenus = new ArrayList<>();
    RoleMenuAddReq roleMenuAddReq = new RoleMenuAddReq();
    RoleSubMenuAddReq roleSubMenuAddReq = new RoleSubMenuAddReq();
    roleSubMenuAddReq.setMenuId("CST001");
    roleSubMenuAddReq.setSearchAuthority(true);
    roleSubMenuAddReq.setDelAuthority(true);
    roleSubMenuAddReq.setModAuthority(true);
    roleSubMenuAddReq.setRegAuthority(true);
    roleMenuAddReq.setSubMenus(Arrays.asList(roleSubMenuAddReq));
    roleMenus.add(roleMenuAddReq);

    roleAddReq.setRoleMenus(roleMenus);
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.post("/manager/role")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(roleAddReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }

  /*@DisplayName("관리자 권한 수정")
  @Test
  public void modifyRole() throws Exception {

    List<RoleNotificationAddReq> lst = new ArrayList<>();
    RoleNotificationAddReq roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT001");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT002");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT003");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT004");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT005");
    roleNotificationAddReq.setDevicePush(false);
    lst.add(roleNotificationAddReq);
    roleNotificationAddReq = new RoleNotificationAddReq();
    roleNotificationAddReq.setDeviceStateCode("DDT006");
    roleNotificationAddReq.setDevicePush(true);
    lst.add(roleNotificationAddReq);

    RoleModReq roleAddReq = new RoleModReq();
    roleAddReq.setName("이름111");
    roleAddReq.setMemo("memo111");
    roleAddReq.setUsable(true);
    roleAddReq.setManagerNotificationList(lst);

    List<RoleMenuAddReq> roleMenus = new ArrayList<>();
    RoleMenuAddReq roleMenuAddReq = new RoleMenuAddReq();
    RoleSubMenuAddReq roleSubMenuAddReq = new RoleSubMenuAddReq();
    roleSubMenuAddReq.setMenuId("CST001");
    roleSubMenuAddReq.setSearchAuthority(true);
    roleSubMenuAddReq.setDelAuthority(true);
    roleSubMenuAddReq.setModAuthority(true);
    roleSubMenuAddReq.setRegAuthority(true);
    roleMenuAddReq.setSubMenus(Arrays.asList(roleSubMenuAddReq));
    roleMenus.add(roleMenuAddReq);

    roleAddReq.setRoleMenus(roleMenus);
    MvcResult mvcResult = mockMvc
      .perform(
        MockMvcRequestBuilders.put("/manager/role/18")
          .header(TokenGenerator.Header, tokenGenerator.getTestToken())
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(roleAddReq))
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    Map result = gson.fromJson(mvcResult.getResponse().getContentAsString(), Map.class);
    String responseCode = result.get("code").toString();

    log.info("responseCode : {}", responseCode);

    AssertionsForInterfaceTypes.assertThat(responseCode).isEqualTo(ApiResponseCodes.SUCCESS.getCode());
  }*/

}
