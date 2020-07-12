package com.warehouse.retail.services.dal.repository;

import com.warehouse.retail.services.dal.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: sonikumari.b
 */
@Repository
public interface MongoProductRepository extends MongoRepository<Product, Integer> {

   Optional<Product> getProductByProductId(Integer productId);
   Product save(Product product);
}
