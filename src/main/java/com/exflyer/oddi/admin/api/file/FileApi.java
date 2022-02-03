package com.exflyer.oddi.admin.api.file;

import com.exflyer.oddi.admin.annotaions.LoginNeedApi;
import com.exflyer.oddi.admin.api.file.dto.FileDownloadInfo;
import com.exflyer.oddi.admin.api.file.dto.FileUploadResult;
import com.exflyer.oddi.admin.api.file.dto.MediaConvertResult;
import com.exflyer.oddi.admin.api.file.service.FileService;
import com.exflyer.oddi.admin.api.manager.auth.dto.AdminAuth;
import com.exflyer.oddi.admin.enums.ApiResponseCodes;
import com.exflyer.oddi.admin.exceptions.ApiException;
import com.exflyer.oddi.admin.models.Files;
import com.exflyer.oddi.admin.share.dto.ApiResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "File", protocols = "http", hidden = true)
@RestController
public class FileApi {

  @Autowired
  private FileService fileService;

  @ApiOperation(value = "단일 파일 업로드 API", notes = "단일 파일 업로드 API 입니다.")
  @PostMapping(value = "/file/single")
  @LoginNeedApi
  public ApiResponseDto upload(@RequestPart MultipartFile file, AdminAuth adminAuth) throws ApiException {
    FileUploadResult fileUploadResult = fileService.uploadAttachments(file, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, fileUploadResult);
  }


  @ApiOperation(value = "광고 영상 업로드 API", notes = "광고 영상 파일 업로드 API 입니다.")
  @PostMapping(value = "/file/video")
  @LoginNeedApi
  public ApiResponseDto uploadVideo(@RequestPart MultipartFile file, AdminAuth adminAuth) throws ApiException {
    FileUploadResult fileUploadResult = fileService.uploadFile(file, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, fileUploadResult);
  }

  @ApiOperation(value = " 배너 업로드", notes = " 배너 업로드 API 입니다.")
  @PostMapping(value = "/file/banner")
  @LoginNeedApi
  public ApiResponseDto uploadBanner(@RequestPart MultipartFile file, AdminAuth adminAuth) throws ApiException {
    FileUploadResult fileUploadResult = fileService.uploadBanner(file, adminAuth.getId());
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, fileUploadResult);
  }

  @ApiOperation(value = "파일 사용 플래그 처리", notes = "파일 사용 플래그 처리 API 입니다.")
  @PutMapping(value = "/file/mapping-done/{fileSeqList}")
  @LoginNeedApi
  public ApiResponseDto updateMappingDone(@PathVariable List<Long> fileSeqList) throws ApiException {
    fileService.updateMappingDone(fileSeqList);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "파일 삭제", notes = "파일 삭제 처리 API 입니다.")
  @DeleteMapping(value = "/file/{fileSeqList}")
  @LoginNeedApi
  public ApiResponseDto deleteFile(@PathVariable List<Long> fileSeqList) throws ApiException {
    fileService.delete(fileSeqList);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS);
  }

  @ApiOperation(value = "파일 다운", notes = "파일 다운 처리 API 입니다.")
  @GetMapping(value = "/file/{fileSeq}")
  @LoginNeedApi
  public ResponseEntity downloadFile(@PathVariable Long fileSeq) throws ApiException, IOException {
    FileDownloadInfo file = fileService.fileDownload(fileSeq);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    httpHeaders.setContentLength(file.getContents().length);
    httpHeaders.setContentDispositionFormData(
        "attachment", URLEncoder.encode(file.getName(), "UTF-8"));
    return new ResponseEntity<>(file.getContents(), httpHeaders, HttpStatus.OK);
  }

  @ApiOperation(value = "파일 정보조회", notes = "파일 정보조회 API 입니다.")
  @GetMapping(value = "/file/info/{fileSeq}")
  @LoginNeedApi
  public ApiResponseDto fileInfo(@PathVariable Long fileSeq)  {
    Files file = fileService.fileInfo(fileSeq);
    return new ApiResponseDto(ApiResponseCodes.SUCCESS, file);
  }

}
