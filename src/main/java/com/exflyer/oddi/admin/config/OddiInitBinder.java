package com.exflyer.oddi.admin.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class OddiInitBinder {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
    binder.registerCustomEditor(String.class, stringtrimmer);
  }
}
