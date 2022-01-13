package service;

import exceptions.InvalidCouponException;
import exceptions.InvalidCpfException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import integrations.services.CouponCheckerAPI;
import lombok.*;

@Getter
@Setter
public class Order {
    private String cpf;
    private String description;
    private Coupon coupon;
    private BigDecimal totalPrice;
    private List<OrderItem> items;
    private BigDecimal orderShipping;
    private static final CpfService cpfService = new CpfService();
    private static final CouponCheckerAPI couponCheckerAPI = new CouponCheckerAPI();

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

    public void addCoupon(Coupon coupon) {
        if (validateCoupon(coupon)) {
            this.coupon = coupon;
        } else throw new InvalidCouponException();
        totalPrice = calcPriceWithDiscount(coupon);
    }

    public boolean validateCoupon(Coupon coupon) {
        return couponCheckerAPI.isValidCoupon(String.valueOf(coupon));
    }

    public BigDecimal calcPrice() {
        return totalPrice = items.stream().map(OrderItem::getPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public BigDecimal calcPriceWithDiscount(Coupon coupon) {
        totalPrice = calcPrice();
        var discount = totalPrice.multiply(coupon.getPercentage());
        return totalPrice.subtract(discount);
    }

    public boolean validateCpf(String cpf) {
        if (!cpfService.validate(cpf)) throw new InvalidCpfException();
        else return true;
    }

    public BigDecimal calcOrderShipping(BigDecimal distanceKm) {
        orderShipping = items.stream().map(item -> item.calcShippingValue(distanceKm)).reduce(BigDecimal.valueOf(0), BigDecimal::add);
        return orderShipping;
    }

    public Order(String cpf) {
        this.cpf = cpf;
    }

}
