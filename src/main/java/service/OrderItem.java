package service;

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
    private BigDecimal shippingValue;
    private static final BigDecimal FACTOR_CONVERSION_CM3_TOM3 = new BigDecimal("1000000");
    private static final BigDecimal FACTOR_TO_DIVIDE_DENSITY = new BigDecimal("100");
    private static final BigDecimal MINIMAL_SHIPPING_VALUE = new BigDecimal("10.00");


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
        this.unitPrice = new BigDecimal(unitPrice);
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

    public BigDecimal calcVolume() {
        volume = toM3(width.multiply(height).multiply(depth));
        return volume;
    }

    public BigDecimal toM3(BigDecimal volumeInC3) {
        return volumeInC3.divide(FACTOR_CONVERSION_CM3_TOM3);
    }

    public BigDecimal calcShippingValue(BigDecimal distanceKm) {
        volume = calcVolume();
        density = calcDensity();
        var normalValue = shippingCalcExpression(distanceKm);
        return shippingValue = receivesMinimalShipping(normalValue) ? MINIMAL_SHIPPING_VALUE : normalValue;
    }

    public BigDecimal shippingCalcExpression(BigDecimal distanceKm) {
        return distanceKm.multiply(volume).multiply(divideDensityByFactor())
                .setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal divideDensityByFactor() {
        return density.divide(FACTOR_TO_DIVIDE_DENSITY, 1, RoundingMode.DOWN);
    }

    /**
     * volume must be in m³ and weight must be in kg
     *
     * @return the density in kg/m³
     */
    public BigDecimal calcDensity() {
        return density = weight.divide(calcVolume(), 1, RoundingMode.DOWN);
    }

    public boolean receivesMinimalShipping(BigDecimal supposedValue) {
        return supposedValue.compareTo(MINIMAL_SHIPPING_VALUE) < 0;
    }


}
