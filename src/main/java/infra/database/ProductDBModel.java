package infra.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class ProductDBModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private  String id;
    private  String description;
    private  BigDecimal height;
    private  BigDecimal width;

    @Column(name = "depth_")
    private  BigDecimal depth;
    private  BigDecimal weight;
    private  BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDBModel that = (ProductDBModel) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(height, that.height) && Objects.equals(width, that.width) && Objects.equals(depth, that.depth) && Objects.equals(weight, that.weight) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, height, width, depth, weight, price);
    }
}
