package com.warehouse.retail.apis.dtos;

/**
 * @author: sonikumari.b
 */
public class Response {

   String message;

   public Response() {

   }

   public Response(String message) {

      this.message = message;
   }

   public String getMessage() {

      return message;
   }

   public void setMessage(String message) {

      this.message = message;
   }
}
