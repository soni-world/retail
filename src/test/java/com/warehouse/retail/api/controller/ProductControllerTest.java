package com.warehouse.retail.api.controller;

import com.warehouse.retail.apis.controller.ProductController;
import com.warehouse.retail.apis.dtos.ProductResponse;
import com.warehouse.retail.apis.mappers.ApiServiceDtoMapper;
import com.warehouse.retail.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


/**
 * @author: sonikumari.b
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

   @Mock
   private ProductService productService;

   @Mock
   private ApiServiceDtoMapper mapper;

   private ProductController controller;

   @Before
   public void setUp() {

      controller = new ProductController(productService, mapper);
   }

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionWhenProductServiceIsNull() {

      new ProductController(null, mapper);
   }

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionWhenMapperIsNull() {

      new ProductController(productService, null);
   }

   @Test
   public void shouldGetProductById() {

      Integer id = 13860428;
      Map<String, Object> priceData = new HashMap<>();
      priceData.put("value", 53);
      priceData.put("currency_code", "USD");
      ProductResponse productMockResponse = new ProductResponse(13860428, "test_data", priceData);
      com.warehouse.retail.services.dtos.Product mockProduct = mock(com.warehouse.retail.services.dtos.Product.class);

      when(productService.getProductDetails(13860428)).thenReturn(mockProduct);
      when(mapper.serviceToApiDtoUpdateProductResponse(mockProduct)).thenReturn(productMockResponse);


      ResponseEntity<ProductResponse> result = controller.fetchProduct(id);
      assertReflectionEquals(productMockResponse, result.getBody());
   }
}
