package com.exflyer.oddi.admin.api.file.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.exflyer.oddi.admin.api.file.dto.AwsS3Result;
import com.exflyer.oddi.admin.api.file.dto.FileDownloadInfo;
import com.exflyer.oddi.admin.api.file.dto.FileUploadResult;
import com.exflyer.oddi.admin.api.file.dto.MediaConvertResult;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Files;
import com.exflyer.oddi.admin.repository.FilesRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class FileService {

  @Autowired
  private AwsS3Service awsS3Service;

  @Autowired
  private FilesRepository filesRepository;



  public FileUploadResult uploadFile(MultipartFile file) throws ApiException {
    return uploadFile(file, null);
  }

  public FileUploadResult uploadFile(MultipartFile file, String managerId) throws ApiException {
    String originalFileName = file.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFileName);

    String uniqFileName = createUniqFileName(extension);
    ObjectMetadata metadata = createObjectMeta(file);
    AwsS3Result awsS3Result = awsS3Service.uploadVideo(file, uniqFileName, metadata);
    Files files = saveFiles(managerId, originalFileName, extension, awsS3Result, file.getContentType());
    return new FileUploadResult(files);
  }

  public FileUploadResult uploadAttachments(MultipartFile file, String managerId) throws ApiException {
    String originalFileName = file.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFileName);

    String uniqFileName = createUniqFileName(extension);
    ObjectMetadata metadata = createObjectMeta(file);

    AwsS3Result awsS3Result = awsS3Service.uploadAttachments(file, uniqFileName, metadata);
    Files files = saveFiles(managerId, originalFileName, extension, awsS3Result, file.getContentType());
    return new FileUploadResult(files);
  }



  public FileUploadResult uploadBanner(MultipartFile file, String managerId) throws ApiException {
    String originalFileName = file.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFileName);
    String uniqFileName = createUniqFileName(extension);
    ObjectMetadata metadata = createObjectMeta(file);
    AwsS3Result awsS3Result = awsS3Service.uploadBanner(file, uniqFileName, metadata);
    Files files = saveFiles(managerId, originalFileName, extension, awsS3Result, file.getContentType());
    return new FileUploadResult(files);
  }

  private ObjectMetadata createObjectMeta(MultipartFile file) {
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(file.getContentType());
    return metadata;
  }

  public void updateMappingDone(List<Long> fileSeqList) {
    filesRepository.updateUseAble(fileSeqList);

  }

  public String getFilePath(Long fileSeq) {
    Optional<Files> filesOptional = filesRepository.findById(fileSeq);
    Files files = filesOptional.orElseGet(Files::new);
    return StringUtils.defaultString(files.getPath(), "");
  }


  public FileDownloadInfo fileDownload(Long fileSeq)  throws ApiException {
    Optional<Files> filesOptional = filesRepository.findById(fileSeq);
    Files files = filesOptional.orElseGet(Files::new);

    FileDownloadInfo fileDownloadInfo =  awsS3Service.downloadFileByFileSeq(files);
    return fileDownloadInfo;
  }

  public Files fileInfo(Long fileSeq) {
    Optional<Files> filesOptional = filesRepository.findById(fileSeq);
    Files files = filesOptional.orElseGet(Files::new);
    return files;
  }

  private Boolean checkImageByExtension(String extension) {
    String extensionLower = extension.toLowerCase();
    return StringUtils.contains(extensionLower, "gif")
      || StringUtils.contains(extensionLower, "jpg")
      || StringUtils.contains(extensionLower, "jpeg")
      || StringUtils.contains(extensionLower, "bmp")
      || StringUtils.contains(extensionLower, "svg")
      || StringUtils.contains(extensionLower, "png");
  }

  private String createUniqFileName(String extension) {
    return UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
  }

  private Files saveFiles(String managerId, String originalFileName, String extension, AwsS3Result awsS3Result
  , String contentType) {
    Files files = new Files(managerId);
    files.setExtension(extension);
    files.setS3FileKey(awsS3Result.getFileKey());
    files.setS3Bucket(awsS3Result.getBucketName());
    files.setPath(awsS3Result.getPath());
    files.setName(originalFileName);
    files.setContentType(contentType);
    filesRepository.save(files);
    return files;
  }


  @Transactional
  public void delete(List<Long> fileSeqList) {

    List<Files> fileList = filesRepository.findAllById(fileSeqList);
    awsS3Service.delete(fileList);
    filesRepository.deleteAll(fileList);
  }
}
