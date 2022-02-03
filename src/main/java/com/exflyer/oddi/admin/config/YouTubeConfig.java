package com.exflyer.oddi.admin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class YouTubeConfig {

    @Value("${youtube.apiUrl}")
    private String apiUrl;

    @Value("${youtube.apiKey}")
    private String apiKey;

    @Value("${youtube.clientKey}")
    private String clientKey;

    @Value("${youtube.play-list-id}")
    private String playListId;

    @Value("${youtube.part}")
    private String part;


}
