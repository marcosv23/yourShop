package unit.domain.entity;

import domain.entity.Coupon;
import domain.entity.Order;
import domain.entity.OrderItem;
import exceptions.InvalidAttributeException;
import exceptions.InvalidCouponException;
import exceptions.InvalidCpfException;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private static Order order;
    private static Order order2;
    private static final BigDecimal DISTANCE_1000_KM = new BigDecimal("1000");
    private static final String VALID_CPF = "404.038.210-29";
    private static final String INVALID_CPF = "404.038.210-27";
    private static final String INVALID_CPF_MESSAGE = "There is not permitted to make an order with invalid CPF";
    private static final String INVALID_COUPON_MESSAGE = "Coupon is invalid or expired";
    private static final String ZONE = "America/Sao_Paulo";
    
    @BeforeAll
    static void initTests() {
        order = new Order(VALID_CPF, "Music items");
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        var item1 = new OrderItem(2, new BigDecimal("100.00"), "item1", height, width, depth, weight);
        var item2 = new OrderItem(1, new BigDecimal("100.00"), "item2", height, width, depth, weight);
        var item3 = new OrderItem(1, new BigDecimal("100.00"), "item3", height, width, depth, weight);
        order.addItem(item1);
        order.addItem(item2);
        order.addItem(item3);
        
        order2 = new Order(VALID_CPF, "Music items");
        var height2 = new BigDecimal("20");
        var width2 = new BigDecimal("15");
        var depth2 = new BigDecimal("10");
        var weight2 = new BigDecimal("1");
        var item4 = new OrderItem(1, new BigDecimal("100.00"), "Guitar", height2, width2, depth2, weight2);
        order2.addItem(item4);
    }

    @Test
    @DisplayName("Deve permitir criar um pedido com um cpf válido")
    void shouldCreateEmptyOrderWithValidCPF() {
        var order = new Order(VALID_CPF);
        assertTrue(order.validateCpf(order.getCpf()));
    }
    

    @Test
    @DisplayName("Deve calcular o valor de um pedido")
    void shouldCalcOrderPrice() {
        assertEquals(new BigDecimal("400.00"), order.calcPrice());
    }

    @Test
    @DisplayName("Não deve criar pedido com cpf inválido")
    void shouldNotCreateOrderWithInValidCPF() {
        Exception exception = assertThrows(InvalidCpfException.class, () -> {
            new Order(INVALID_CPF, "Music items");
        });
        assertTrue(exception.getMessage().contains(INVALID_CPF_MESSAGE));
    }

    @Test
    @DisplayName("Deve calcular preço de pedido com cupon de 100% OFF")
    void shouldCalcDiscountFor10OFFCoupon() {
        var coupon = Coupon.builder()
                        .percentage(new BigDecimal(100))
                        .description("PASSOVER")
                        .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                        .build();
        assertEquals(new BigDecimal("0.00"),order.calcPriceWithDiscounts(coupon));
    }

    @Test
    @DisplayName("Deve criar um pedido com cupom de 15% OFF")
    void shouldCalcDiscountFor15OFFCoupon() {
        var coupon = Coupon.builder()
                .percentage(new BigDecimal(15))
                .description("PASSOVER")
                .expirationDate(Instant.now().plus(10, ChronoUnit.DAYS))
                .build();
        assertEquals( new BigDecimal("340.00"),order.calcPriceWithDiscounts(coupon));
    }

    @Test
    @DisplayName("Deve retornar exceção quando um cupom inválido for adicionado")
    void shouldReturnExceptionWhenHasInvalidCoupon() {
        Exception exception = assertThrows(InvalidCouponException.class, () -> {
            var yesterdayDate = Instant.now().minus(1, ChronoUnit.DAYS);
            var coupon = Coupon.builder()
                    .percentage(new BigDecimal(15))
                    .description("PASSOVER")
                    .expirationDate(yesterdayDate)
                    .build();
            order.addCoupon(coupon);
        });
        assertTrue(exception.getMessage().contains(INVALID_COUPON_MESSAGE));
    }

    @Test
    @DisplayName("Deve permitir criar um pedido com um cupom válido")
    void shouldValidCoupon() {
        var coupon = Coupon.builder()
                .percentage(new BigDecimal(15))
                .description("PASSOVER")
                .expirationDate(Instant.now().plus(1, ChronoUnit.DAYS))
                .build();
        order.addCoupon(coupon);
        assertEquals(new BigDecimal("340.00"),order.getTotalPrice());
    }

    @Test
    @DisplayName("Deve retornar valor convencional de frete")
    void shouldCalcOrderShippingAndReturnNormalShippingValue() {
        assertEquals(new BigDecimal("30.00"), order.calcFreight(DISTANCE_1000_KM));
    }

    @Test
    @DisplayName("Deve retornar valor mínimo de frete")
    void shouldCalcOrderShippingAndReturnMinimalShippingValue() {
        assertEquals(new BigDecimal("10.00"), order2.calcFreight(DISTANCE_1000_KM));
    }
    
    @Test
    @DisplayName("Não deve permitir preço negativo para um item")
    void shouldNotAllowInvalidUnitPrice(){
        assertThrows(InvalidAttributeException.class,
                ()->OrderItem.builder().unitPrice(new BigDecimal("-1")).build());
    }
    
    @Test
    @DisplayName("Não deve permitir quantidade negativa ou zero para um item")
    void shouldNotAllowInvalidQuantity(){
        assertThrows(InvalidAttributeException.class,
                ()->OrderItem.builder().quantity(0).build());
    }
    
    @Test
    @DisplayName("Não deve permitir altura negativa para um item")
    void shouldNotAllowInvalidHeight(){
        assertThrows(InvalidAttributeException.class,
                ()->OrderItem.builder().height(new BigDecimal("-1")).build());
    }
    @Test
    @DisplayName("Não deve permitir peso negativo para um item")
    void shouldNotAllowInvalidHWeight(){
        assertThrows(InvalidAttributeException.class,
                ()->OrderItem.builder().weight(new BigDecimal("-1")).build());
    }
    @Test
    @DisplayName("Não deve permitir largura negativa para um item")
    void shouldNotAllowInvalidHWidth(){
        assertThrows(InvalidAttributeException.class,
                ()->OrderItem.builder().width(new BigDecimal("-1")).build());
    }

}
