package infra.database;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDBModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private  UUID id;
    private  String code;

    private BigDecimal price;

    private String cpf;
    private  String description;
    private BigDecimal shipping;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductDBModel> items;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CouponDBModel> coupon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDBModel that = (OrderDBModel) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(shipping, that.shipping) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, shipping, items);
    }
}
