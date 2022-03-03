package domain.entity;

import exceptions.InvalidCouponException;
import exceptions.InvalidCpfException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
public class Order {
    private String cpf;
    private String description;
    private BigDecimal totalPrice;
    private List<OrderItem> items;
    private BigDecimal orderShipping;
    private static final CpfService cpfService = new CpfService();

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

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void addCoupon(Coupon coupon, Instant date) {
        if (!validateCoupon(coupon, date)) throw new InvalidCouponException();
        totalPrice = calcPriceWithDiscount(coupon);
    }

    public boolean validateCoupon(Coupon coupon, Instant date) {
        return Coupon.isValidCoupon(coupon, date);
    }

    public BigDecimal calcPrice() {
        return totalPrice = items.stream().map(OrderItem::getPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public BigDecimal calcPriceWithDiscount(Coupon coupon) {
        totalPrice = calcPrice();
        var discount = Coupon.calculateDiscount(totalPrice, coupon);
        return totalPrice.subtract(discount);
    }

    public boolean validateCpf(String cpf) {
        if (!cpfService.validate(cpf)) throw new InvalidCpfException();
        else return true;
    }

    public BigDecimal calcFreight(BigDecimal distanceKm) {
        return items
                .stream()
                .map(item -> FreightCalculator.calculate(item, distanceKm))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }
}
