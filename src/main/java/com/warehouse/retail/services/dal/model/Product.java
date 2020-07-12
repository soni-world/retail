package com.warehouse.retail.services.dal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * @author: sonikumari.b
 */
@Document(collection = "product")
public class Product {

   @Id
   Integer productId;

   @Transient
   @Field("name")
   String name;

   @Field("current_price")
   Map<String, Object> currentPrice;

   public Integer getProductId() {

      return productId;
   }

   public void setProductId(Integer productId) {

      this.productId = productId;
   }

   public String getName() {

      return name;
   }

   public void setName(String name) {

      this.name = name;
   }

   public Map<String, Object> getCurrentPrice() {

      return currentPrice;
   }

   public void setCurrentPrice(Map<String, Object> currentPrice) {

      this.currentPrice = currentPrice;
   }

   @Override
   public String toString() {

      return "Product{" + "productId=" + productId + ", name='" + name + '\'' + ", currentPrice=" + currentPrice + '}';
   }
}

