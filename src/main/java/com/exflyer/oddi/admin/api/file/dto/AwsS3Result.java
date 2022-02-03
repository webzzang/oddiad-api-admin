package com.exflyer.oddi.admin.api.file.dto;

import lombok.Data;

@Data
public class AwsS3Result {

  private String bucketName;

  private String fileKey;

  private String path;

}
