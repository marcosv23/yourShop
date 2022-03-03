package domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class OrderItem {
    private final int amount;
    private final BigDecimal unitPrice;
    private final String description;
    private final BigDecimal height;
    private final BigDecimal width;
    private final BigDecimal depth;
    private final BigDecimal weight;
    private BigDecimal price;
    private BigDecimal volume;
    private BigDecimal density;
    private BigDecimal freight;
    private static final BigDecimal FACTOR_CONVERSION_CM3_TOM3 = new BigDecimal("1000000");


    /**
     * @param amount      items amount
     * @param unitPrice   unit price for each item
     * @param description brief description
     * @param height      in kg
     * @param width       in cm
     * @param depth       in cm
     * @param weight      in cm
     */
    public OrderItem(int amount, double unitPrice, String description, BigDecimal height, BigDecimal width, BigDecimal depth, BigDecimal weight) {
        this.amount = amount;
        this.unitPrice = BigDecimal.valueOf(unitPrice);
        this.depth = depth;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.price = calcTotalPrice();
        this.description = description;
    }

    public BigDecimal calcTotalPrice() {
        return price = unitPrice.multiply(BigDecimal.valueOf(amount));
    }

    public BigDecimal getVolume() {
        volume = toM3(width.multiply(height).multiply(depth));
        return volume;
    }


    /**
     * volume must be in m³ and weight must be in kg
     *
     * @return the density in kg/m³
     */
    public BigDecimal getDensity() {
        return density = weight.divide(getVolume(), 1, RoundingMode.DOWN);
    }

    public BigDecimal toM3(BigDecimal volumeInC3) {
        return volumeInC3.divide(FACTOR_CONVERSION_CM3_TOM3);
    }

}
