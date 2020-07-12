package com.warehouse.retail.apis.mappers;

import com.warehouse.retail.apis.dtos.ProductResponse;
import com.warehouse.retail.apis.dtos.ProductUpdateRequest;
import com.warehouse.retail.services.dtos.Product;
import org.mapstruct.Mapper;

/**
 * @author: sonikumari.b
 */
@Mapper(componentModel = "spring")
public abstract class ApiServiceDtoMapper {

   public abstract Product apiToServiceUpdateProductRequest(Integer id, ProductUpdateRequest productUpdateRequest);

   public abstract ProductResponse serviceToApiDtoUpdateProductResponse(com.warehouse.retail.services.dtos.Product response);
}
