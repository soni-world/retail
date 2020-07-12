package com.warehouse.retail.apis.config.spring;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: sonikumari.b
 */
public class HttpClientConfig {

   @Value("${httpClient.maxTotalConnections}")
   private int maxTotalConnections;

   @Value("${httpClient.maxConnectionsPerRoute}")
   private int maxConnectionsPerRoute;

   @Value("${httpClient.connectTimeoutMillis}")
   private int connectTimeoutMillis;


   @Value("${httpClient.connectionTTLMillis}")
   private int connectionTTLMillis;


   @Bean
   public HttpClient httpClient() {

      PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(connectionTTLMillis,
            TimeUnit.MILLISECONDS);

      connectionManager.setMaxTotal(maxTotalConnections);
      connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);

      RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeoutMillis).build();

      return HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
   }

   @Bean
   public RestTemplate restTemplate() {

      return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
   }

}
