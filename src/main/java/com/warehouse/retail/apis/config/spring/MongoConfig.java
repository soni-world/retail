package com.warehouse.retail.apis.config.spring;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * @author: sonikumari.b
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.warehouse.retail.services.dal"})
public class MongoConfig {

   @Value("${mongodb.mongoUri}")
   private String mongoUri;

   @Bean
   public MongoDbFactory mongoDbFactory() throws UnknownHostException {
      return new SimpleMongoDbFactory(new MongoClientURI(mongoUri));
   }

   @Bean
   public MongoTemplate mongoTemplate() throws UnknownHostException {
      MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

      return mongoTemplate;

   }

}
