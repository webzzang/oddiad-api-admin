package com.exflyer.oddi.admin.api.manager.role.dao;

import com.exflyer.oddi.admin.api.manager.menu.dto.RoleMenuDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapper {

  boolean isCreateActionAccessAble(@Param("managerId") String managerId, @Param("menuCode") String menuCode);

  boolean isReadActionAccessAble(@Param("managerId") String managerId, @Param("menuCode") String menuCode);

  boolean isUpdateActionAccessAble(@Param("managerId") String managerId, @Param("menuCode") String menuCode);

  boolean isDeleteActionAccessAble(@Param("managerId") String managerId, @Param("menuCode") String menuCode);

  List<RoleMenuDto> findAccessAbleMenuByRoleSeq(Long roleSeq);
}
