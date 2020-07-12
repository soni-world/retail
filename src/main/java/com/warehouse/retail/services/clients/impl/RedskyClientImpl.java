package com.warehouse.retail.services.clients.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.retail.services.clients.RedskyClient;
import com.warehouse.retail.services.clients.dtos.ProductDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author: sonikumari.b
 */
public class RedskyClientImpl implements RedskyClient {

   private static final Logger LOGGER = LoggerFactory.getLogger(RedskyClientImpl.class);

   private final RestTemplate restTemplate;

   public RedskyClientImpl(RestTemplate restTemplate) {

      this.restTemplate = restTemplate;
   }

   @Override
   public String getProductTitleByProductId(String productId) {
      LOGGER.info("Calling external client for product id {}", productId);
      String title = "";
      HttpEntity<Object> httpEntity = new HttpEntity<>(new HttpHeaders());
      String response = restTemplate
            .exchange(createUrl(productId), HttpMethod.GET, httpEntity, String.class).getBody();
      LOGGER.info("ProductDetails response from external client {}", response);
      try {
         title =  getProductTitle(response);
      } catch (IOException e) {
         e.printStackTrace();
         LOGGER.error("Error while calling to external client", e.getMessage());
      }
      return title;
   }

   private String getProductTitle(String response) throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> productdata = objectMapper.readValue(response, Map.class);
      Map<String, Object> productInfo = (Map<String, Object>) productdata.get("product");
      Map<String, Object> itemInfo = (Map<String, Object>) productInfo.get("item");
      Map<String, Object> productDescription = (Map<String, Object>) itemInfo.get("product_description");

      return productDescription.get("title").toString();
   }

   private String createUrl(String productId) {

      return "https://redsky.target.com/v2/pdp/tcin/" + productId + "?excludes=taxonomy,price,promotion,bulk_ship,"
            + "rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
   }
}
