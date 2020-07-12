package com.warehouse.retail.apis.config.spring;

import com.warehouse.retail.apis.controller.ProductController;
import com.warehouse.retail.apis.mappers.ApiServiceDtoMapper;
import com.warehouse.retail.services.dal.mappers.DalToMongoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

/**
 * @author: sonikumari.b
 */
@Configuration
@ComponentScan({"com.warehouse.retail.apis.mappers","com.warehouse.retail.services.dal.mappers"})
@Import({ BusinessServiceConfig.class})
public class ApplicationConfig {

   @Inject
   private BusinessServiceConfig businessServiceConfig;


   @Bean
   public ApiServiceDtoMapper mapper() {

      return Mappers.getMapper(ApiServiceDtoMapper.class);
   }

   @Bean
   public ProductController productController() {

      return new ProductController(businessServiceConfig.productService(), mapper());
   }
}
