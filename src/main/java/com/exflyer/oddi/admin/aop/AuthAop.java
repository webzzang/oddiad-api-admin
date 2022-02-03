package com.exflyer.oddi.admin.aop;


import com.exflyer.oddi.admin.annotaions.MenuValidApi;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.api.manager.auth.service.AdminAuthService;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.jwt.dto.JwtVerifyResult;
import com.exflyer.oddi.admin.jwt.service.JwtService;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class AuthAop {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AdminAuthService adminAuthService;

  @Around("@annotation(com.exflyer.oddi.admin.annotaions.LoginNeedApi)")
  public Object validLogin(ProceedingJoinPoint joinPoint) throws Throwable {

    // token 추출
    JwtVerifyResult jwtVerifyResult = extractAdminAuthInAccessToken();

    Object[] args = createAdminAuthArgs(joinPoint, new AdminAuth(jwtVerifyResult.getId()));

    return joinPoint.proceed(args);
  }


  @Around("@annotation(com.exflyer.oddi.admin.annotaions.MenuValidApi)")
  public Object validMenuReq(ProceedingJoinPoint joinPoint) throws Throwable {

    // token 추출
    JwtVerifyResult jwtVerifyResult = extractAdminAuthInAccessToken();

    // 접근 가능 메뉴 확인
    boolean isAvailableAct = isAvailableAct(joinPoint, jwtVerifyResult.getId());
    if (!isAvailableAct) {
      throw new ApiException(ApiResponseCodes.FORBIDDEN);
    }
    Object[] args = createAdminAuthArgs(joinPoint, new AdminAuth(jwtVerifyResult.getId()));

    return joinPoint.proceed(args);
  }

  private HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  private boolean isAvailableAct(ProceedingJoinPoint joinPoint, String managerId) throws ApiException {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    MenuValidApi adminApiAnnotation = method.getAnnotation(MenuValidApi.class);
    return adminAuthService.isAvailableAct(managerId, adminApiAnnotation);
  }

  private JwtVerifyResult extractAdminAuthInAccessToken() throws ApiException {
    HttpServletRequest request = getHttpServletRequest();
    String accessTokenHeader = request.getHeader("x-access-token");
    validationHeader(accessTokenHeader);
    return jwtService.verify(accessTokenHeader);
  }

  private void validationHeader(String accessTokenHeader) throws ApiException {
    if (StringUtils.isBlank(accessTokenHeader)) {
      log.info(">>>> accessTokenHeader blank");
      throw new ApiException(ApiResponseCodes.AUTHENTIFICATION);
    }
  }

  private Object[] createAdminAuthArgs(ProceedingJoinPoint joinPoint, AdminAuth adminAuth) {

    return Arrays.stream(joinPoint.getArgs())
      .map(
        data -> {
          if (data instanceof AdminAuth) {
            data = adminAuth;
          }
          return data;
        })
      .toArray();
  }
}
