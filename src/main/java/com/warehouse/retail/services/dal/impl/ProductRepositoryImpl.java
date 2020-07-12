package com.warehouse.retail.services.dal.impl;

import com.warehouse.retail.services.dal.ProductRepository;
import com.warehouse.retail.services.dal.mappers.DalToMongoMapper;
import com.warehouse.retail.services.dtos.Product;
import com.warehouse.retail.services.dal.repository.MongoProductRepository;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: sonikumari.b
 */
public class ProductRepositoryImpl implements ProductRepository {

   private final MongoProductRepository mongoProductRepository;
   private final DalToMongoMapper mapper;

   public ProductRepositoryImpl(MongoProductRepository mongoProductRepository, DalToMongoMapper mapper) {

      this.mongoProductRepository = checkNotNull(mongoProductRepository);
      this.mapper = checkNotNull(mapper);
   }

   @Override
   public Optional<Product> getProductByProductId(Integer productId) {
       return mongoProductRepository.getProductByProductId(productId).map(mapper::dalToDtoProduct);
   }

   @Override
   public void updateProduct(Product product) {
      mongoProductRepository.save(mapper.dtoToDalProduct(product));
   }
}
