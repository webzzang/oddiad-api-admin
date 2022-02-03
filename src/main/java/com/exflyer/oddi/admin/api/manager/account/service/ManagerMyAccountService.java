package com.exflyer.oddi.admin.api.manager.account.service;

import com.exflyer.oddi.admin.api.manager.account.dao.ManagerAccountMapper;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountDetail;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModPasswordReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModReq;
import com.exflyer.oddi.admin.api.manager.auth.service.PassWordEncrypt;
import com.exflyer.oddi.admin.api.manager.menu.dto.ManagerMenu;
import com.exflyer.oddi.admin.api.manager.menu.dto.ManagerSubMenu;
import com.exflyer.oddi.admin.api.manager.menu.dto.RoleMenuDto;
import com.exflyer.oddi.admin.api.manager.role.dao.RoleMenuMapper;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Manager;
import com.exflyer.oddi.admin.repository.ManagerRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerMyAccountService {

  @Autowired
  private ManagerRepository managerRepository;

  @Autowired
  private ManagerAccountMapper managerAccountMapper;

  @Autowired
  private RoleMenuMapper roleMenuMapper;

  @Autowired
  private PassWordEncrypt passWordEncrypt;

  public List<ManagerMenu> findMenu(String managerId) throws ApiException {
    Optional<Manager> managerOptional = managerRepository.findById(managerId);
    Manager manager = managerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    Long roleSeq = manager.getRoleSeq();
    return findMenuTree(roleSeq);
  }


  public List<ManagerMenu> findMenuTree(Long roleSeq) {
    // 권한 메뉴 정보
    List<RoleMenuDto> roleMenuList = roleMenuMapper.findAccessAbleMenuByRoleSeq(roleSeq);

    List<ManagerMenu> menuResList = roleMenuList.stream()
      .map(roleMenu -> {
        ManagerMenu menuRes = new ManagerMenu();
        menuRes.setGroupId(roleMenu.getGroupId());
        menuRes.setGroupName(roleMenu.getGroupName());
        menuRes.setIcon(roleMenu.getGroupIcon());
        return menuRes;
      })
      .distinct()
      .collect(Collectors.toList());

    for (ManagerMenu menuRes : menuResList) {
      List<ManagerSubMenu> subMenus = new ArrayList<>();
      for (RoleMenuDto roleMenuDto : roleMenuList) {
        if (StringUtils.equalsIgnoreCase(menuRes.getGroupId(), roleMenuDto.getGroupId())) {
          subMenus.add(new ManagerSubMenu(roleMenuDto));
        }
      }
      menuRes.setManagerSubMenus(subMenus);
    }
    return menuResList;
  }

  public ManagerAccountDetail findDetail(String id) throws ApiException {
    ManagerAccountDetail managerDetailResult = managerAccountMapper.findDetailById(id);
    if (managerDetailResult == null) {
      throw new ApiException(ApiResponseCodes.NOT_FOUND);
    }
    return managerDetailResult;
  }

  public void modify(ManagerMyAccountModReq managerMyAccountModReq) throws ApiException {
    Optional<Manager> managerOptional = managerRepository.findById(managerMyAccountModReq.getId());
    Manager manager = managerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    manager.settingByMyAccountModReq(managerMyAccountModReq);
    managerRepository.save(manager);
  }

  public void modifyPassword(ManagerMyAccountModPasswordReq modPasswordReq) throws ApiException {
    // 신규 비밀번호와 이전 비밀번호가 동일 하면 오류
    if(modPasswordReq.equalsOldAndNewPassword()){
      throw new ApiException(ApiResponseCodes.BAD_REQUEST);
    }

    Optional<Manager> managerOptional = managerRepository.findById(modPasswordReq.getId());
    Manager manager = managerOptional.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    passWordEncrypt.checkPassword(modPasswordReq.getOldPassword(), manager.getPassword());

    // 신규 비밀번호 암호화
    modPasswordReq.setNewPassword(passWordEncrypt.encryptPassword(modPasswordReq.getNewPassword()));

    manager.settingByPasswordModReq(modPasswordReq);
    managerRepository.save(manager);
  }

  public void continuePassword(String id) throws ApiException {
    Optional<Manager> optionalManager = managerRepository.findById(id);
    Manager manager = optionalManager.orElseThrow(() -> new ApiException(ApiResponseCodes.NOT_FOUND));
    manager.setPasswordModDate(LocalDateUtils.krNow());
    managerRepository.save(manager);
  }
}
