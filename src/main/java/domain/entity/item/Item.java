package domain.entity.item;

import application.exceptions.InvalidAttributeException;
import lombok.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {
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

    @Setter
    private String id ="";


    public static ItemBuilder builder() {
        return new ItemBuilder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return unitPrice.equals(item.unitPrice)  && height.equals(item.height) && width.equals(item.width)  && weight.equals(item.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, description, height, width, depth, weight);
    }

    //----
    
    public static class ItemBuilder {
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

        private String id;
        
        ItemBuilder() {
        }

        public ItemBuilder id(String id){
            this.id = id;
            return this;
        }
        public ItemBuilder quantity(int quantity) {
            if(quantity <=0) throw new InvalidAttributeException("Invalid quantity: "+ quantity);
            this.quantity = quantity;
            return this;
        }
        
        public ItemBuilder unitPrice(BigDecimal unitPrice) {
            if(unitPrice.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid unitPrice: "+ unitPrice);
            this.unitPrice = unitPrice;
            return this;
        }
        
        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public ItemBuilder height(BigDecimal height) {
            if( height.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid heigth: "+ unitPrice);
            this.height = height;
            return this;
        }
        
        public ItemBuilder width(BigDecimal width) {
            if( width.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid width: "+ width);
            this.width = width;
            return this;
        }
        
        public ItemBuilder depth(BigDecimal depth) {
            if( depth.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid depth: "+ depth);
            this.depth = depth;
            return this;
        }
        
        public ItemBuilder weight(BigDecimal weight) {
            if( weight.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid weight: "+ weight);
            this.weight = weight;
            return this;
        }
        
        public ItemBuilder price(BigDecimal price) {
            if( price.intValueExact() <=0 ) throw new InvalidAttributeException("Invalid price: "+ price);
            this.price = price;
            return this;
        }
        
        public ItemBuilder volume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }
        
        public ItemBuilder density(BigDecimal density) {
            this.density = density;
            return this;
        }
        
        public ItemBuilder freight(BigDecimal freight) {
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
        public Item build() {
            return new Item(quantity, unitPrice, description, height, width, depth, weight, price, volume, density, freight,id);
        }
        
        @Override
        public String toString() {
            return "OrderItem.OrderItemBuilder(quantity=" + this.quantity + ", unitPrice=" + this.unitPrice + ", description=" + this.description + ", height=" + this.height + ", width=" + this.width + ", depth=" + this.depth + ", weight=" + this.weight + ", price=" + this.price + ", volume=" + this.volume + ", density=" + this.density + ", freight=" + this.freight + ")";
        }

        @Override
        public int hashCode() {
            return Objects.hash(description, height, width, depth, weight, price);
        }
    }
}
