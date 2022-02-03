package com.exflyer.oddi.admin.api.manager.auth.dao;

import com.exflyer.oddi.admin.share.dto.OddiLoginConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginConfigMapper {

  OddiLoginConfig find();
}
