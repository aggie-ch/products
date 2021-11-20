package io.github.aggie.orders;

import io.github.aggie.common.web.FastMoneyMapper;
import io.github.aggie.common.web.IdTransferObject;
import io.github.aggie.products.Product;
import io.github.aggie.products.ProductMapper;
import io.github.aggie.products.ProductService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {FastMoneyMapper.class, ProductMapper.class})
public abstract class OrderMapper {

    @Autowired
    private ProductService productService;

    public Order toOrder(OrderTransferObject orderTransferObject) {
        List<Product> products = orderTransferObject.getProducts().stream()
                .map(IdTransferObject::getId)
                .map(productService::getById)
                .collect(Collectors.toList());
        return new Order(products);
    }
}
