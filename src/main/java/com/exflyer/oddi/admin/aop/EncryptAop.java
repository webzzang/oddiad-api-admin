package com.exflyer.oddi.admin.aop;


import com.exflyer.oddi.admin.annotaions.DecryptField;
import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.AesEncryptor;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class EncryptAop {

  @Autowired
  private AesEncryptor aesEncryptor;


  @Around("@annotation(com.exflyer.oddi.admin.annotaions.OddiEncrypt)")
  public Object encryptOddiEncryptAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public Object encryptGetMapping(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
  public Object encryptPostMapping(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  @Around("@annotation(org.springframework.web.bind.annotation.PutMapping)")
  public Object encryptPutMapping(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
  public Object encryptDeleteMapping(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public Object encryptRequestMapping(ProceedingJoinPoint joinPoint) throws Throwable {
    beforeMethodEncryptFiled(joinPoint);
    Object result = joinPoint.proceed();
    afterMethodDecrypt(joinPoint, result);
    return result;
  }

  private boolean afterMethodDecrypt(ProceedingJoinPoint joinPoint, Object result) throws IllegalAccessException {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    if (signature.getReturnType() == ApiResponseDto.class) {
      ApiResponseDto apiResponseDto = (ApiResponseDto) result;
      if (apiResponseDto.getData() == null) {
        return true;
      }
      if (PagingResult.class == apiResponseDto.getData().getClass()) {
        PagingResult pagingResult = (PagingResult) apiResponseDto.getData();
        for (Object pagingData : pagingResult.getData()) {
          decryptFields(pagingData, pagingData.getClass());
        }
      } else {
        Object singleData = apiResponseDto.getData();
        decryptFields(singleData, singleData.getClass());
      }
    }
    return false;
  }

  private void beforeMethodEncryptFiled(ProceedingJoinPoint joinPoint) throws IllegalAccessException {
    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
      for (Field field : arg.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        for (Annotation declaredAnnotation : field.getDeclaredAnnotations()) {
          if (declaredAnnotation.annotationType() == EncryptField.class) {
            String encryptTargetValue = (String) field.get(arg);
            if (StringUtils.isNotBlank(encryptTargetValue)) {
              String encryptedValue = aesEncryptor.encrypt(encryptTargetValue);
              field.set(arg, encryptedValue);
            }
          }
        }
      }
    }
  }

  private void decryptFields(Object arg, Class<?> argClass)
    throws IllegalAccessException {
    
    for (Field field : argClass.getDeclaredFields()) {
      field.setAccessible(true);
      for (Annotation declaredAnnotation : field.getDeclaredAnnotations()) {
        if (declaredAnnotation.annotationType() == DecryptField.class) {
          if (field.getType().equals(String.class)) {
            String decryptTargetValue = (String) field.get(arg);
            String decryptedValue = aesEncryptor.decrypt(decryptTargetValue);
            field.set(arg, decryptedValue);
          } else {
            decryptFields(field.get(arg), field.getType());
          }
        }
      }
    }
  }
}
