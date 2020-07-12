package com.warehouse.retail.services.dal.mappers;

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
public class DalToMongoMapperImpl extends DalToMongoMapper {

    @Override
    public com.warehouse.retail.services.dal.model.Product dtoToDalProduct(Product product) {
        if ( product == null ) {
            return null;
        }

        com.warehouse.retail.services.dal.model.Product product1 = new com.warehouse.retail.services.dal.model.Product();

        product1.setProductId( product.getId() );
        product1.setName( product.getName() );
        Map<String, Object> map = product.getCurrentPrice();
        if ( map != null ) {
            product1.setCurrentPrice( new HashMap<String, Object>( map ) );
        }
        else {
            product1.setCurrentPrice( null );
        }

        return product1;
    }

    @Override
    public Product dalToDtoProduct(com.warehouse.retail.services.dal.model.Product product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setId( product.getProductId() );
        product1.setName( product.getName() );
        Map<String, Object> map = product.getCurrentPrice();
        if ( map != null ) {
            product1.setCurrentPrice( new HashMap<String, Object>( map ) );
        }
        else {
            product1.setCurrentPrice( null );
        }

        return product1;
    }
}
