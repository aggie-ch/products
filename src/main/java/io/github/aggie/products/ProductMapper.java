package io.github.aggie.products;

import io.github.aggie.common.PagedResult;
import io.github.aggie.common.web.FastMoneyMapper;
import io.github.aggie.common.web.PagedResultTransferObject;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface ProductMapper {

    Product toProduct(ProductTransferObject productTransferObject);

    ProductTransferObject toProductTransferObject(Product product);

    PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage);

    @ValueMapping(source = "BOOK", target = "EBOOK")
    @ValueMapping(source = "AUDIO", target = "MUSIC")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductTypeTransferObject toProductTypeTransferObject(ProductType productType);

    @InheritInverseConfiguration
    ProductType toProductType(ProductTypeTransferObject productTypeTransferObject);

}
