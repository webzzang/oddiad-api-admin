package com.exflyer.oddi.admin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DevicePushConfig {

    @Value("${device-push.host-url}")
    private String hostUrl;

}
