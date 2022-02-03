package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Role;
import com.exflyer.oddi.admin.models.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long>, JpaSpecificationExecutor<Role> {

  @Modifying
  @Transactional
  @Query(value = "delete from role_menu where role_seq = :seq", nativeQuery = true)
  void deleteByRoleSeq(Long seq);
}
