package com.exflyer.oddi.admin.api.file.dto;

import lombok.Data;

@Data
public class FileDownloadInfo {

    private String name;

    private byte[] contents;

}
