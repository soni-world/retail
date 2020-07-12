package com.warehouse.retail.apis.config.spring;

import com.warehouse.retail.services.ProductService;
import com.warehouse.retail.services.clients.RedskyClient;
import com.warehouse.retail.services.clients.impl.RedskyClientImpl;
import com.warehouse.retail.services.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

/**
 * @author: sonikumari.b
 */
@Configuration
@Import({ RepositoryConfig.class, HttpClientConfig.class })
public class BusinessServiceConfig {

   @Inject
   private RepositoryConfig repositoryConfig;

   @Inject
   private HttpClientConfig httpClientConfig;

   @Bean
   public RedskyClient redskyClient() {

      return new RedskyClientImpl(httpClientConfig.restTemplate());
   }

   @Bean
   public ProductService productService() {

      return new ProductServiceImpl(repositoryConfig.productRepository(), redskyClient());
   }
}
