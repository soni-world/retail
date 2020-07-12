package com.warehouse.retail.services.impl;

import com.warehouse.retail.services.ProductService;
import com.warehouse.retail.services.clients.RedskyClient;
import com.warehouse.retail.services.dal.ProductRepository;
import com.warehouse.retail.services.dtos.Product;
import com.warehouse.retail.services.exceptions.ProductDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: sonikumari.b
 */
@ParametersAreNonnullByDefault
@ThreadSafe
@Service
public class ProductServiceImpl implements ProductService {

   private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

   private final ProductRepository productRepository;
   private final RedskyClient redskyClient;

   public ProductServiceImpl(ProductRepository productRepository, RedskyClient redskyClient) {

      this.productRepository = checkNotNull(productRepository);
      this.redskyClient = checkNotNull(redskyClient);
   }

   @Override
   public Product getProductDetails(Integer id) {
      Product productDetail =  productRepository.getProductByProductId(id).orElseThrow(() -> new ProductDoesNotExistException());
      productDetail.setName(redskyClient.getProductTitleByProductId(id.toString()));

      return productDetail;
   }

   @Override
   public void updateProduct(Product product) {
      Product productDetail =  productRepository.getProductByProductId(product.getId()).orElseThrow(() -> new ProductDoesNotExistException());
      productDetail.setCurrentPrice(product.getCurrentPrice());
      productRepository.updateProduct(productDetail);
   }
}
