package com.warehouse.retail.services;

import com.google.common.collect.ImmutableList;
import com.warehouse.retail.services.clients.RedskyClient;
import com.warehouse.retail.services.dal.ProductRepository;
import com.warehouse.retail.services.dtos.Product;
import com.warehouse.retail.services.exceptions.ProductDoesNotExistException;
import com.warehouse.retail.services.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author: sonikumari.b
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

   @Mock
   private ProductRepository productRepository;

   @Mock
   private RedskyClient redskyClient;

   private ProductServiceImpl service;

   @Before
   public void setUp() {

      service = new ProductServiceImpl(productRepository, redskyClient);
   }

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionIfProductRepositoryIsNull() {

      new ProductServiceImpl(null, redskyClient);
   }

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionIfRedskyClientIsNull() {

      new ProductServiceImpl(productRepository, null);
   }


   @Test
   public void shouldFetchProduct(){
      Integer id = 13860428;
      Map<String, Object> priceData = new HashMap<>();
      priceData.put("value", 53);
      priceData.put("currency_code", "USD");

      Product productRequest = new Product(id, "test_data", priceData);
      Product dbProductDetail = mock(Product.class);
      when(productRepository.getProductByProductId(productRequest.getId())).thenReturn(
            java.util.Optional.ofNullable(dbProductDetail));
      service.getProductDetails(id);
      verify(productRepository).getProductByProductId(id);
   }


   @Test
   public void shouldUpdateProductPrice(){

      Integer id = 13860428;
      Map<String, Object> priceData = new HashMap<>();
      priceData.put("value", 53);
      priceData.put("currency_code", "USD");

      Product productRequest = new Product(id, "test_data", priceData);
      Product dbProductDetail = mock(Product.class);
      when(productRepository.getProductByProductId(productRequest.getId())).thenReturn(
            java.util.Optional.ofNullable(dbProductDetail));
      service.updateProduct(productRequest);
      verify(productRepository).updateProduct(dbProductDetail);
   }
}