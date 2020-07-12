package com.warehouse.retail.apis.mappers;

import com.warehouse.retail.apis.dtos.ProductResponse;
import com.warehouse.retail.apis.dtos.ProductUpdateRequest;
import com.warehouse.retail.services.dtos.Product;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-11T22:08:49+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class ApiServiceDtoMapperImpl extends ApiServiceDtoMapper {

    @Override
    public Product apiToServiceUpdateProductRequest(Integer id, ProductUpdateRequest productUpdateRequest) {
        if ( id == null && productUpdateRequest == null ) {
            return null;
        }

        Product product = new Product();

        if ( productUpdateRequest != null ) {
            product.setId( productUpdateRequest.getId() );
            product.setName( productUpdateRequest.getName() );
            Map<String, Object> map = productUpdateRequest.getCurrentPrice();
            if ( map != null ) {
                product.setCurrentPrice( new HashMap<String, Object>( map ) );
            }
            else {
                product.setCurrentPrice( null );
            }
        }

        return product;
    }

    @Override
    public ProductResponse serviceToApiDtoUpdateProductResponse(Product response) {
        if ( response == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( response.getId() );
        productResponse.setName( response.getName() );
        Map<String, Object> map = response.getCurrentPrice();
        if ( map != null ) {
            productResponse.setCurrentPrice( new HashMap<String, Object>( map ) );
        }
        else {
            productResponse.setCurrentPrice( null );
        }

        return productResponse;
    }
}
