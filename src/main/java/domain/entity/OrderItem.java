package domain.entity;

import exceptions.InvalidAttributeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItem {
    private final int quantity;
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
    
    
    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }
    
    public BigDecimal calcTotalPrice() {
        return price = unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.FLOOR);
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
    
    public static class OrderItemBuilder {
        private int quantity;
        private BigDecimal unitPrice;
        private String description;
        private BigDecimal height;
        private BigDecimal width;
        private BigDecimal depth;
        private BigDecimal weight;
        private BigDecimal price;
        private BigDecimal volume;
        private BigDecimal density;
        private BigDecimal freight;
        
        OrderItemBuilder() {
        }
        
        public OrderItemBuilder quantity(int quantity) {
            if(quantity <=0) throw new InvalidAttributeException("Invalid quantity: "+ quantity);
            this.quantity = quantity;
            return this;
        }
        
        public OrderItemBuilder unitPrice(BigDecimal unitPrice) {
            if(unitPrice.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid unitPrice: "+ unitPrice);
            this.unitPrice = unitPrice;
            return this;
        }
        
        public OrderItemBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public OrderItemBuilder height(BigDecimal height) {
            if( height.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid heigth: "+ unitPrice);
            this.height = height;
            return this;
        }
        
        public OrderItemBuilder width(BigDecimal width) {
            if( width.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid width: "+ width);
            this.width = width;
            return this;
        }
        
        public OrderItemBuilder depth(BigDecimal depth) {
            if( depth.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid depth: "+ depth);
            this.depth = depth;
            return this;
        }
        
        public OrderItemBuilder weight(BigDecimal weight) {
            if( weight.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid weight: "+ weight);
            this.weight = weight;
            return this;
        }
        
        public OrderItemBuilder price(BigDecimal price) {
            if( price.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid price: "+ price);
            this.price = price;
            return this;
        }
        
        public OrderItemBuilder volume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }
        
        public OrderItemBuilder density(BigDecimal density) {
            this.density = density;
            return this;
        }
        
        public OrderItemBuilder freight(BigDecimal freight) {
            this.freight = freight;
            return this;
        }
    
        /**
         *  quantity    items number
         *  unitPrice   unit price for each item
         *  description brief description
         *  height      in kg
         *  width       in cm
         *  depth       in cm
         *  weight      in cm
         */
        public OrderItem build() {
            return new OrderItem(quantity, unitPrice, description, height, width, depth, weight, price, volume, density, freight);
        }
        
        @Override
        public String toString() {
            return "OrderItem.OrderItemBuilder(quantity=" + this.quantity + ", unitPrice=" + this.unitPrice + ", description=" + this.description + ", height=" + this.height + ", width=" + this.width + ", depth=" + this.depth + ", weight=" + this.weight + ", price=" + this.price + ", volume=" + this.volume + ", density=" + this.density + ", freight=" + this.freight + ")";
        }
    }
}
