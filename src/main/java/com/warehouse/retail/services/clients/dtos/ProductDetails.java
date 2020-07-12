package com.warehouse.retail.services.clients.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author: sonikumari.b
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails {

   @ApiModelProperty(name = "product")
   @JsonProperty(value = "product")
   Map<String, Object> product;

   public Map<String, Object> getProduct() {

      return product;
   }

   public void setProduct(Map<String, Object> product) {

      this.product = product;
   }

   @Override
   public String toString() {

      return "ProductDetails{" + "product=" + product + '}';
   }
}
