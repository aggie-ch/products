package io.github.aggie.payments;

import io.github.aggie.common.FastMoneyUserType;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@TypeDef(name = "fastMoney", typeClass = FastMoneyUserType.class, defaultForType = FastMoney.class)
@Table(name = "payments", indexes = @Index(name = "payment_status", columnList = "status"))
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value")
    })
    private FastMoney money;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Payment otherEntity = (Payment) otherObject;
        return this.id != null && Objects.equals(id, otherEntity.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
