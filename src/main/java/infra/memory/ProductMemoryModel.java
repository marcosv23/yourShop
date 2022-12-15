package infra.memory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductMemoryModel {
    private  String id;
    private  String description;
    private  BigDecimal height;
    private  BigDecimal width;
    private  BigDecimal depth;
    private  BigDecimal weight;
    private  BigDecimal price;
}
