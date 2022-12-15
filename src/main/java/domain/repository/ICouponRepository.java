package domain.repository;

import domain.entity.coupon.Coupon;
import infra.memory.CouponMemoryModel;

public interface ICouponRepository {
     Coupon getCouponByName(String name);
}
