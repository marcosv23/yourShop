package domain.entity.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import domain.entity.coupon.Coupon;
import domain.entity.FreightCalculator;
import domain.entity.item.Item;
import application.exceptions.ForbiddenActionException;
import application.exceptions.InvalidCouponException;
import application.exceptions.InvalidCpfException;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String cpf;

    private  String code;
    private String description;
    private BigDecimal price;
    private List<Item> items;
    private BigDecimal shipping;
    private static final Cpf CPF = new Cpf();

    public Order(String cpf) {
        if (validateCpf(cpf)) {
            this.cpf = cpf;
        }
        this.items = new ArrayList<>();
    }

    public Order(String cpf, String description) {
        if (validateCpf(cpf)) {
            this.cpf = cpf;
        }
        this.description = description;
        this.items = new ArrayList<>();
    }
    public void addItem(Item item) {
        if(items.stream().anyMatch(i->i.equals(item))) throw  new ForbiddenActionException("There is not permitted to add the same item twice");
        this.items.add(item);
    }

    public void addCoupon(Coupon coupon) {
        if (!coupon.isValidCoupon()) throw new InvalidCouponException();
        price = calcPriceWithDiscounts(coupon);
    }

    public BigDecimal calcPrice() {
        return items.stream().map(Item::calcTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public  BigDecimal calculateCouponDiscount(BigDecimal price, Coupon coupon) {
        return price.multiply(coupon.getDecimalDiscount()).setScale(2, RoundingMode.FLOOR);
    }

    public BigDecimal calcPriceWithDiscounts(Coupon coupon) {
        price = calcPrice();
        var couponDiscount = calculateCouponDiscount(price, coupon);
        return price.subtract(couponDiscount);
    }

    public boolean validateCpf(String cpf) {
        if (!CPF.validate(cpf)) throw new InvalidCpfException();
        else return true;
    }

    public BigDecimal calcFreight(BigDecimal distanceKm) {
        return items
                .stream()
                .map(item -> FreightCalculator.calculate(item, distanceKm))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }
}
