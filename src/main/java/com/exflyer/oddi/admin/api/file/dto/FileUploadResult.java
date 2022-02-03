package com.exflyer.oddi.admin.api.file.dto;

import com.exflyer.oddi.admin.models.Files;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUploadResult {

  private Long seq;

  private String url;

  private String originName;

  private String uniqFileName;

  public FileUploadResult(Files files) {
    this.url = files.getPath();
    this.originName = files.getName();
    this.seq = files.getSeq();
    this.uniqFileName = files.getS3FileKey();
  }
}
