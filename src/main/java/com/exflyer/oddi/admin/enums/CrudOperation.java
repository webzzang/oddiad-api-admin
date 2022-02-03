package com.exflyer.oddi.admin.enums;

public enum CrudOperation {
  CREATE("create"),
  READ("read"),
  UPDATE("update"),
  DELETE("delete");

  private String value;

  CrudOperation(String value) {
    this.value = value;
  }
}
