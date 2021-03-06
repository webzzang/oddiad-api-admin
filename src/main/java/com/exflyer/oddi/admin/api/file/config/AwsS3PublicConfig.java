package com.exflyer.oddi.admin.api.file.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AwsS3PublicConfig {

  @Value("${aws.s3.public.bucket}")
  private String bucket;

  @Value("${aws.s3.public.video-dir}")
  private String videoDir;

  @Value("${aws.s3.public.attachments-dir}")
  private String attachments;

  @Value("${aws.s3.public.banner-dir}")
  private String bannerDir;

  @Value("${aws.s3.public.url-host}")
  private String urlHost;

  @Value("${aws.s3.public.region}")
  private String region;

}
