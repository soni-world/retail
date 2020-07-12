package com.warehouse.retail.apis.config.spring;


import com.warehouse.retail.services.dal.impl.ProductRepositoryImpl;
import com.warehouse.retail.services.dal.mappers.DalToMongoMapper;
import com.warehouse.retail.services.dal.repository.MongoProductRepository;
import com.warehouse.retail.services.dal.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@ComponentScan({"com.warehouse.retail.services.dal.mappers"})
@Import({ MongoConfig.class })
public class RepositoryConfig {

   @Inject
   private MongoProductRepository mongoProductRepository;

   @Bean
   public DalToMongoMapper dalToMongoMapper() {

      return Mappers.getMapper(DalToMongoMapper.class);
   }


   @Bean
   public ProductRepository productRepository() {

      return new ProductRepositoryImpl(mongoProductRepository, dalToMongoMapper());
   }

}
