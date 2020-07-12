package com.warehouse.retail.apis.dtos;

import java.util.Map;

/**
 * @author: sonikumari.b
 */
public class ProductResponse {

   Integer id;
   String name;
   Map<String, Object> currentPrice;

   public ProductResponse() {

   }

   public ProductResponse(Integer id, String name, Map<String, Object> currentPrice) {

      this.id = id;
      this.name = name;
      this.currentPrice = currentPrice;
   }

   public Integer getId() {

      return id;
   }

   public void setId(Integer id) {

      this.id = id;
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

      return "ProductResponse{" + "id=" + id + ", name='" + name + '\'' + ", currentPrice=" + currentPrice + '}';
   }

}
