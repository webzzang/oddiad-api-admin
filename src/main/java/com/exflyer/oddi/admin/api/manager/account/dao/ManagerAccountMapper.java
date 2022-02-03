package com.exflyer.oddi.admin.api.manager.account.dao;

import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountDetail;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountListSearchReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccounts;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAccountMapper {

  Page<ManagerAccounts> findBySearchReq(ManagerAccountListSearchReq searchReq);

  ManagerAccountDetail findDetailById(String id);
}
