package com.warehouse.retail.services.dal.mappers;

import com.warehouse.retail.services.dal.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

/**
 * @author: sonikumari.b
 */
@Mapper(componentModel = "spring")
public abstract class DalToMongoMapper {

   @Mapping(target = "productId", source = "id")
   public abstract Product dtoToDalProduct(com.warehouse.retail.services.dtos.Product product);

   @Mapping(source = "productId", target = "id")
   public abstract com.warehouse.retail.services.dtos.Product dalToDtoProduct(Product product);

}
