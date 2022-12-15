package infra.database;

;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
public class CouponDBModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    private  BigDecimal percentage;
    private  String name;
    private  Instant expirationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        System.out.println();
        if (o == null || getClass() != o.getClass()) return false;
        CouponDBModel that = (CouponDBModel) o;
        return Objects.equals(percentage, that.percentage) && Objects.equals(name, that.name) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentage, name, expirationDate);
    }
}
