package com.exflyer.oddi.admin.annotaions;


import com.exflyer.oddi.admin.enums.CrudOperation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuValidApi {

  String menuCode();

  CrudOperation operation();

}
