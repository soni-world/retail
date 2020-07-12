package com.warehouse.retail.apis.controller;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.warehouse.retail.apis.dtos.ProductResponse;
import com.warehouse.retail.apis.dtos.ProductUpdateRequest;
import com.warehouse.retail.apis.dtos.Response;
import com.warehouse.retail.apis.mappers.ApiServiceDtoMapper;
import com.warehouse.retail.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: sonikumari.b
 */
@Api(protocols = "http", tags = "ProductController")
@ThreadSafe
@RestController
@ParametersAreNonnullByDefault
@RequestMapping("/api/v1/products")
public class ProductController {

   private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

   private final ProductService productService;
   private final ApiServiceDtoMapper mapper;


   @Autowired
   public ProductController(ProductService productService, ApiServiceDtoMapper mapper) {

      this.productService = checkNotNull(productService);
      this.mapper = checkNotNull(mapper);
   }

   @ApiOperation(nickname = "/{id}", value = "Fetch Product", notes = "Returns 200 if product found")
   @ApiResponses({
         @ApiResponse(code = HttpStatus.OK_200, message = "Product is successfully fetched", response =
               ProductResponse.class),
         @ApiResponse(code = HttpStatus.BAD_REQUEST_400, message = "Mandatory attributes are missing"),
         @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "Internal server error."
               + " An unknown error occurred in the application") })
   @Timed
   @ExceptionMetered
   @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ProductResponse> fetchProduct(@NotNull @PathVariable("id") Integer id) {

      LOGGER.info("Fetch product called with id {} ", id);
      com.warehouse.retail.services.dtos.Product response = productService.getProductDetails(id);
      return new ResponseEntity<>(mapper.serviceToApiDtoUpdateProductResponse(response), org.springframework.http.HttpStatus.OK);
   }


   @ApiOperation(nickname = "/{id}", value = "Update Product", notes = "Returns 200 if product updated successfully ")
   @ApiResponses({
         @ApiResponse(code = HttpStatus.OK_200, message = "Product is successfully updated", response = Response.class),
         @ApiResponse(code = HttpStatus.BAD_REQUEST_400, message = "Mandatory attributes are missing"),
         @ApiResponse(code = HttpStatus.CONFLICT_409, message = "A concurrent operation resulted in"
               + " a conflict. Retry the request"),
         @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "Internal server error."
               + " An unknown error occurred in the application") })
   @Timed
   @ExceptionMetered
   @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =
         MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Response> updateProduct(@NotNull @PathVariable("id") Integer id, @NotNull @RequestBody @Valid
         ProductUpdateRequest productUpdateRequest) {

      LOGGER.info("Update product called with {} ", productUpdateRequest);
      productService.updateProduct(mapper.apiToServiceUpdateProductRequest(id, productUpdateRequest));
      return new ResponseEntity<>(new Response("Successfuly updated"), org.springframework.http.HttpStatus.OK);
   }
}
