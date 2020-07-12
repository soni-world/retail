package com.warehouse.retail.services.clients;

import com.warehouse.retail.services.clients.dtos.ProductDetails;

/**
 * @author: sonikumari.b
 */
public interface RedskyClient {

   String getProductTitleByProductId(String productId);

}
