package com.exflyer.oddi.admin.api.file.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.exflyer.oddi.admin.api.file.config.AwsS3PublicConfig;
import com.exflyer.oddi.admin.api.file.dto.AwsS3Result;
import com.exflyer.oddi.admin.api.file.dto.FileDownloadInfo;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class AwsS3Service {

  private AmazonS3 s3PublicClient;

  @Autowired
  private AwsS3PublicConfig awsS3PublicConfig;


  @PostConstruct
  public void setS3PublicClient() {
    s3PublicClient = AmazonS3ClientBuilder.standard()
      .withRegion(Regions.AP_NORTHEAST_2)
      .build();
  }

  public AwsS3Result uploadBanner(MultipartFile multipartFile, String uniqFileName, ObjectMetadata metadata)
    throws ApiException {
    try {
      String bucketName = awsS3PublicConfig.getBucket() + awsS3PublicConfig.getBannerDir();
      String fileKey = uniqFileName;
      s3PublicClient
        .putObject(
          new PutObjectRequest(bucketName, fileKey, multipartFile.getInputStream(), metadata));
      AwsS3Result awsS3Result = new AwsS3Result();
      String path = awsS3PublicConfig.getUrlHost() + awsS3PublicConfig.getBannerDir() + uniqFileName;
      awsS3Result.setPath(path);
      awsS3Result.setBucketName(bucketName);
      awsS3Result.setFileKey(uniqFileName);
      return awsS3Result;
    } catch (Exception e) {
      log.error("aws s3 exception", e);
      throw new ApiException(ApiResponseCodes.AWS_S3_FAIL);
    }
  }


  public AwsS3Result uploadVideo(MultipartFile multipartFile, String uniqFileName, ObjectMetadata metadata)
    throws ApiException {
    String bucketName = awsS3PublicConfig.getBucket() + awsS3PublicConfig.getVideoDir();
    String fileKey = uniqFileName;
    try {
      s3PublicClient
        .putObject(
          new PutObjectRequest(bucketName, fileKey, multipartFile.getInputStream(), metadata));
      String path = awsS3PublicConfig.getUrlHost() + awsS3PublicConfig.getVideoDir() + "/" + uniqFileName;
      AwsS3Result awsS3Result = new AwsS3Result();
      awsS3Result.setPath(path);
      awsS3Result.setBucketName(bucketName);
      awsS3Result.setFileKey(uniqFileName);
      return awsS3Result;
    } catch (Exception e) {
      log.error("aws s3 exception", e);
      throw new ApiException(ApiResponseCodes.AWS_S3_FAIL);
    }
  }

  public AwsS3Result uploadAttachments(MultipartFile multipartFile, String uniqFileName, ObjectMetadata metadata)
    throws ApiException {
    String bucketName = awsS3PublicConfig.getBucket() + awsS3PublicConfig.getAttachments();
    String fileKey = uniqFileName;
    try {
      s3PublicClient
        .putObject(
          new PutObjectRequest(bucketName, fileKey, multipartFile.getInputStream(), metadata));
      String path = awsS3PublicConfig.getUrlHost() + awsS3PublicConfig.getAttachments() + "/" + uniqFileName;
      AwsS3Result awsS3Result = new AwsS3Result();
      awsS3Result.setPath(path);
      awsS3Result.setBucketName(bucketName);
      awsS3Result.setFileKey(uniqFileName);
      return awsS3Result;
    } catch (Exception e) {
      log.error("aws s3 exception", e);
      throw new ApiException(ApiResponseCodes.AWS_S3_FAIL);
    }
  }

  public void delete(List<Files> fileList) {
    for (Files files : fileList) {
      s3PublicClient.deleteObject(files.getS3Bucket(), files.getS3FileKey());
    }
  }


  public FileDownloadInfo downloadFileByFileSeq(Files files) throws ApiException {

    FileDownloadInfo downloadInfo = new FileDownloadInfo();
    downloadInfo.setName(files.getName());

    try {

      downloadInfo.setContents(IOUtils
          .toByteArray(
              s3PublicClient.getObject(files.getS3Bucket(), files.getS3FileKey()).getObjectContent()));
    } catch (Exception e) {
      log.error("aws s3 exception", e);
      throw new ApiException(ApiResponseCodes.AWS_S3_FAIL);
    }
    return downloadInfo;
  }

}
