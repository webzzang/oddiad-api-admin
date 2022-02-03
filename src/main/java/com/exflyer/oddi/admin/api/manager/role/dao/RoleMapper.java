package com.exflyer.oddi.admin.api.manager.role.dao;

import com.exflyer.oddi.admin.api.manager.role.dto.RoleCondition;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleConditionResult;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleMenuRes;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleNotificationRes;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {

  Page<RoleConditionResult> findByCondition(RoleCondition condition);
  List<RoleMenuRes> findRoleMenu(Long seq);
  List<RoleNotificationRes> findManagerNofification(Long seq);
}
