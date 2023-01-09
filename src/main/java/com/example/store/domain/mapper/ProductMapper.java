package com.example.store.domain.mapper;

import com.example.store.application.payload.ProductRequest;
import com.example.store.application.payload.ProductResponse;
import com.example.store.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product mapProductRequest(ProductRequest productRequest);

    ProductResponse mapProduct(Product product);
//}


}