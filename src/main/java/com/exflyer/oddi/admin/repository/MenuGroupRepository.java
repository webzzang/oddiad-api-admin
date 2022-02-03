package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, String>, JpaSpecificationExecutor<MenuGroup> {

}
