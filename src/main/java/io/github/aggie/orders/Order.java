package io.github.aggie.orders;

import io.github.aggie.common.BaseEntity;
import io.github.aggie.payments.LocalMoney;
import io.github.aggie.payments.Payment;
import io.github.aggie.products.Product;
import lombok.*;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@Table(name = "orders")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    @NonNull
    private List<Product> products;
    @OneToOne
    @Valid
    private Payment payment;
    private Instant timestamp;

    public FastMoney getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(LocalMoney.zero(), FastMoney::add);
    }
}
