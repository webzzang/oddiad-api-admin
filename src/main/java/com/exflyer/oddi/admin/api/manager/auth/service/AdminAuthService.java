package com.exflyer.oddi.admin.api.manager.auth.service;


import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dao.AdminAuthDao;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.enums.CrudOperation;
import com.exflyer.oddi.admin.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthService {

  @Autowired
  private AdminAuthDao adminAuthDao;

  public boolean isAvailableAct(String managerId, MenuValidApi adminApiAnnotation) throws ApiException {
    String menuCode = adminApiAnnotation.menuCode();
    CrudOperation operation = adminApiAnnotation.operation();
    Boolean createAction = false, readAction = false, updateAction = false, deleteAction = false;
    if (operation == CrudOperation.CREATE) {
      createAction = true;
    } else if (operation == CrudOperation.READ) {
      readAction = true;
    } else if (operation == CrudOperation.UPDATE) {
      updateAction = true;
    } else if (operation == CrudOperation.DELETE) {
      deleteAction = true;
    } else {
      throw new ApiException(ApiResponseCodes.FORBIDDEN);
    }
    return adminAuthDao
      .isAvailableAct(managerId, menuCode, createAction, readAction, updateAction, deleteAction);
  }
}
