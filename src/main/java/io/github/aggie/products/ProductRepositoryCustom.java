package io.github.aggie.products;

import java.util.Optional;

public interface ProductRepositoryCustom {

    Optional<Product> findByDescription(String description);

}
