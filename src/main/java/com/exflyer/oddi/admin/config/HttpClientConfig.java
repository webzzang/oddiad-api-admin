package com.exflyer.oddi.admin.config;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class HttpClientConfig {

  @Bean
  public HttpClient httpClient() {
    try {

      PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
      connManager.setMaxTotal(200);

      RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(60 * 1000)
        .setConnectionRequestTimeout(60 * 1000)
        .setSocketTimeout(60 * 1000).build();

      CloseableHttpClient httpClient =
        HttpClients.custom()
          .setRetryHandler(
            (exception, executionCount, context) -> {
              if (executionCount > 1) {
                log.warn("Maximum tries reached for client http pool ");
                return false;
              }
              if (exception instanceof org.apache.http.NoHttpResponseException) {
                log.warn("No response from server on " + executionCount + " call");
                return true;
              }
              return false;
            })
          .setConnectionManager(connManager)
          .setDefaultRequestConfig(
            RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
          .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
          .setDefaultRequestConfig(config)
          .build();

      return httpClient;
    } catch (Exception e) {
      log.error("ssl httpclient exception", e);
      return HttpClientBuilder.create().build();
    }
  }

}
