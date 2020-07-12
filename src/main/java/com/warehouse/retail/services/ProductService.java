package com.warehouse.retail.services;

import com.warehouse.retail.services.dtos.ProductResponse;
import com.warehouse.retail.services.dtos.Product;

/**
 * @author: sonikumari.b
 */
public interface ProductService {

   Product getProductDetails(Integer id);

   void updateProduct(Product apiToServiceUpdateProductRequest);

}
