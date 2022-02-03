package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

  List<Role> findByUsable(boolean using);

  Role findByName(String roleName);
}
