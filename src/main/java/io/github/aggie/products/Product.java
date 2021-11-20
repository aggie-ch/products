package io.github.aggie.products;

import io.github.aggie.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;

@NamedQuery(name = Product.SELECT_PRODUCTS, query = "select p from Product p")
@Table(name = "products", indexes = @Index(name = "product_type", columnList = "type"))
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    public static final String SELECT_PRODUCTS = "selectProducts";

    private String name;
    private String description;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value")
    })
    private FastMoney price;
    @Enumerated(EnumType.STRING)
    private ProductType type;
}
