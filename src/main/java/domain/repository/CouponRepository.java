package domain.repository;

import infra.database.CouponDBModel;

public interface CouponRepository {
     CouponDBModel getCouponByName(String name);
}
