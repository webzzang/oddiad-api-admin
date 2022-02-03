package com.exflyer.oddi.admin.api.manager.auth.service;


import com.exflyer.oddi.admin.api.manager.auth.code.ManagerStateCodes;
import com.exflyer.oddi.admin.api.manager.auth.dto.CertificationResult;
import com.exflyer.oddi.admin.api.manager.auth.dto.SignInReq;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.jwt.dto.JwtResult;
import com.exflyer.oddi.admin.jwt.service.JwtService;
import com.exflyer.oddi.admin.models.Manager;
import com.exflyer.oddi.admin.repository.ManagerRepository;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInService {

  @Autowired
  private ManagerRepository managerRepository;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private PassWordEncrypt passWordEncrypt;

  private final int PASSWORD_CHANGED_LIMIT_DAY = 180;


  public CertificationResult signIn(SignInReq signInReq) throws ApiException {

    Manager manager = findMangerBySignReq(signInReq.getEmail());

    boolean isValidPassword = passWordEncrypt.checkPassword(signInReq.getPassword(), manager.getPassword());
    if (!isValidPassword) {
      manager.setPasswordErrorCount(manager.getPasswordErrorCount()+1);
      managerRepository.save(manager);
      throw new ApiException(ApiResponseCodes.AUTHENTIFICATION_ACCOUNT);
    }

    if(manager.getState().equals(ManagerStateCodes.STOP_MANAGER_STATE.getCode())){
      throw new ApiException(ApiResponseCodes.STOP_MANAGER_STATE);
    }else if(manager.getState().equals(ManagerStateCodes.DORMANT_MANAGER_STATE.getCode())){
      throw new ApiException(ApiResponseCodes.DORMANT_MANAGER_STATE);
    }

    if(manager.getPasswordErrorCount() > 5){
      throw new ApiException(ApiResponseCodes.PASSWORD_FAIL_OVER_COUNT_MANAGER);
    }

    // 비밀번호가 일치 하면 일단 로그인으로 판단.
    JwtResult jwtResult = jwtService.createAccessToken(manager.getId());

    if (manager.isInitPassword()) {
      return new CertificationResult(jwtResult, ApiResponseCodes.INIT_PASSWORD);
    }

    if (isOverPasswordChangedDate(manager)) {
      return new CertificationResult(jwtResult, ApiResponseCodes.PASSWORD_CHANGE_DAY_OVER);
    }

    manager.setPasswordErrorCount(0);
    manager.setLoginDate(LocalDateUtils.krNow());
    managerRepository.save(manager);
    return new CertificationResult(jwtResult, ApiResponseCodes.SUCCESS);
  }

  public boolean isOverPasswordChangedDate(Manager manager) throws ApiException {
    if (manager.getPasswordModDate() == null) {
      return true;
    }
    LocalDateTime nowDateTime = LocalDateUtils.krNow();
    long changedPasswordDuration = Duration.between(manager.getPasswordModDate(), nowDateTime)
      .toDays();
    return changedPasswordDuration >= PASSWORD_CHANGED_LIMIT_DAY;
  }

  private Manager findMangerBySignReq(String email) throws ApiException {
    Optional<Manager> managerOptional = managerRepository.findById(email);
    return managerOptional
      .orElseThrow(() -> new ApiException(ApiResponseCodes.AUTHENTIFICATION));
  }

}
