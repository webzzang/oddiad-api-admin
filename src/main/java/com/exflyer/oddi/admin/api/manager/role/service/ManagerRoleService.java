package com.exflyer.oddi.admin.api.manager.role.service;

import com.exflyer.oddi.admin.api.manager.role.dao.RoleMapper;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleCondition;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleConditionResult;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleDetail;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleMenuAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleMenuRes;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleMenuWithSubMenu;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleModReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleNotificationRes;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleSubMenuAddReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.PagingOrderEnums;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.models.ManagerNotification;
import com.exflyer.oddi.admin.models.Role;
import com.exflyer.oddi.admin.models.RoleMenu;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.repository.ManagerNotificationRepository;
import com.exflyer.oddi.admin.repository.RoleMenuRepository;
import com.exflyer.oddi.admin.repository.RoleRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
public class ManagerRoleService {

  @Autowired
  private RoleMapper roleMapper;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private RoleMenuRepository roleMenuRepository;
  @Autowired
  private ManagerNotificationRepository managerNotificationRepository;
  @Autowired
  private CodeRepository codeRepository;
  @PersistenceContext
  EntityManager em;

  public PagingResult<RoleConditionResult> findRoleOfPaging(RoleCondition condition) {
    PageHelper.startPage(condition.getPageNo(), condition.getPageSize());
    PageHelper.orderBy(StringUtils.defaultString(condition.getOrderBy(), PagingOrderEnums.REG_DATE.getOrderName()));
    Page<RoleConditionResult> result = roleMapper.findByCondition(condition);
    return PagingResult.createResultDto(result);
  }

  public RoleDetail findDetail(Long seq) throws ApiException {

    Optional<Role> optionalRole = roleRepository.findById(seq);
    Role role = optionalRole.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    RoleDetail roleDetailRes = new RoleDetail(role);
    List<RoleMenuRes> roleMenu = roleMapper.findRoleMenu(role.getSeq());
    List<RoleNotificationRes> managerNotificationList = roleMapper.findManagerNofification(role.getSeq());
    if (CollectionUtils.isEmpty(roleMenu)) {
      roleMenu = roleMapper.findRoleMenu(null);
    }
    roleDetailRes.setRoleMenus(findRoleMenu(roleMenu));
    roleDetailRes.setManagerNotificationList(managerNotificationList);
    return roleDetailRes;
  }

  private List<RoleMenuWithSubMenu> findRoleMenu(List<RoleMenuRes> roleMenuList) {
    List<RoleMenuWithSubMenu> roleMenuWithSubMenus = roleMenuList.stream()
      .map(roleMenu -> {
        RoleMenuWithSubMenu roleMenuWithSubMenu = new RoleMenuWithSubMenu();
        roleMenuWithSubMenu.setGroupId(roleMenu.getGroupId());
        roleMenuWithSubMenu.setGroupName(roleMenu.getGroupName());
        return roleMenuWithSubMenu;
      })
      .distinct()
      .collect(Collectors.toList());

    for (RoleMenuWithSubMenu roleMenuWithSubMenu : roleMenuWithSubMenus) {
      for (RoleMenuRes roleMenu : roleMenuList) {
        if (StringUtils.equalsIgnoreCase(roleMenuWithSubMenu.getGroupId(), roleMenu.getGroupId())) {
          roleMenuWithSubMenu.getSubMenus().add(roleMenu);
        }
      }
    }
    return roleMenuWithSubMenus;
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void add(RoleAddReq roleAddReq) throws ApiException {

// 중복 체크
    Boolean isDuplication = isDuplication(roleAddReq.getName());

    if (isDuplication) {
      throw new ApiException(ApiResponseCodes.DUPLICATE);
    }
    Role role = new Role(roleAddReq);
    roleRepository.save(role);
    em.persist(role);

    // 관리자 시스템 알림 문자수신
    if(roleAddReq.getManagerNotificationList() != null) {
      setManagerNotification(role.getSeq(), roleAddReq, null);
    }

    List<RoleMenu> roleMenus = new ArrayList<>();
    for (RoleMenuAddReq roleMenuAddReq : roleAddReq.getRoleMenus()) {
      for (RoleSubMenuAddReq subMenu : roleMenuAddReq.getSubMenus()) {
        roleMenus.add(new RoleMenu(subMenu, role.getSeq(), roleAddReq.getRegId()));
      }
    }
    roleMenuRepository.saveAll(roleMenus);
  }

  public boolean isDuplication(String roleName) {
    Role role = roleRepository.findByName(roleName);
    return role != null;
  }

  @Transactional(rollbackFor = {Exception.class, Error.class})
  public void modify(RoleModReq modReq) throws ApiException {
    Optional<Role> roleModelOptional = roleRepository.findById(modReq.getRoleSeq());
    Role roleModel = roleModelOptional
      .orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    roleModel.setInfoByModReq(modReq);
    roleRepository.save(roleModel);

    //관리자 시스템 알림 문자수신 일단주석처리

    if(modReq.getManagerNotificationList() != null) {
      managerNotificationRepository.deleteManagerNotification(modReq.getRoleSeq());

      // 관리자 시스템 알림 문자수신
      setManagerNotification(modReq.getRoleSeq(), null, modReq);
    }

    roleMenuRepository.deleteByRoleSeq(modReq.getRoleSeq());
    List<RoleMenu> roleMenus = new ArrayList<>();
    for (RoleMenuAddReq roleMenuAddReq : modReq.getRoleMenus()) {
      for (RoleSubMenuAddReq subMenu : roleMenuAddReq.getSubMenus()) {
        roleMenus.add(new RoleMenu(subMenu, roleModel.getSeq(), modReq.getModId()));
      }
    }
    roleMenuRepository.saveAll(roleMenus);
  }


  public RoleDetail findInitInfo() {
    RoleDetail roleDetailRes = new RoleDetail();
    List<RoleMenuRes> roleMenu = roleMapper.findRoleMenu(null);
    List<RoleNotificationRes> managerNotificationList = roleMapper.findManagerNofification(null);
    roleDetailRes.setRoleMenus(findRoleMenu(roleMenu));
    roleDetailRes.setManagerNotificationList(managerNotificationList);
    return roleDetailRes;
  }

  private void setManagerNotification(Long seq, RoleAddReq roleAddReq, RoleModReq modReq){

    // 관리자 시스템 알림 문자수신
    List<Code> deviceDangerCode = codeRepository.findByGroupCodeAndUsable("DDT", true);

    for(Code code : deviceDangerCode) {
      ManagerNotification managerNotification = new ManagerNotification();
      managerNotification.setRoleSeq(seq);
      managerNotification.setDeviceStateCode(code.getCode());
      managerNotificationRepository.save(managerNotification);
    }

    if(roleAddReq != null) {
      roleAddReq.getManagerNotificationList().forEach(datas -> {
        managerNotificationRepository
            .updateManagerNotification(datas.getDevicePush(), seq,
                datas.getDeviceStateCode());
      });
    }

    if(modReq != null) {
      modReq.getManagerNotificationList().forEach(datas -> {
        managerNotificationRepository
            .updateManagerNotification(datas.getDevicePush(), seq,
                datas.getDeviceStateCode());
      });
    }
  }
}
