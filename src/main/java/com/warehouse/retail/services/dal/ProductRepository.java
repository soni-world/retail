package com.warehouse.retail.services.dal;

import com.warehouse.retail.services.dtos.Product;

import java.util.Optional;

/**
 * @author: sonikumari.b
 */
public interface ProductRepository {

   Optional<Product> getProductByProductId(Integer productId);

   void updateProduct(Product product);
}
