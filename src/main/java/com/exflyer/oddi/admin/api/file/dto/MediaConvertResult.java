package com.exflyer.oddi.admin.api.file.dto;

import lombok.Data;

@Data
public class MediaConvertResult {

  private String filePath;

  public MediaConvertResult(String filePath) {
    this.filePath = filePath;
  }
}
