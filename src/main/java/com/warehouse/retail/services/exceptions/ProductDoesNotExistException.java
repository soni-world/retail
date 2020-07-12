package com.warehouse.retail.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

/**
 * @author: sonikumari.b
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found.")
public class ProductDoesNotExistException extends RuntimeException {

   private static final long serialVersionUID = -2843210038987875529L;


   public ProductDoesNotExistException() {

      super(" Product Not Found. ");
   }
}
