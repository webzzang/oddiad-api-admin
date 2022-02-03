package com.exflyer.oddi.admin.api.adv.make.dto;

import lombok.Data;

@Data
public class DevicePushReq {

    private String action;

    private String device_id;

    private String screen_type;

}
