package infra.database;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDBModel {
    private  String id;
    private  String description;
    private  BigDecimal height;
    private  BigDecimal width;
    private  BigDecimal depth;
    private  BigDecimal weight;
    private  BigDecimal unitPrice;
}
