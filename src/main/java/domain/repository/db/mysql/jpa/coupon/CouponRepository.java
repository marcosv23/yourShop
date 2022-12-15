package domain.repository.db.mysql.jpa.coupon;

import domain.entity.coupon.Coupon;
import domain.repository.ICouponRepository;
import infra.database.CouponDBModel;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


@Service
public class CouponRepository implements ICouponRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Coupon getCouponByName(String name) {
        var query = entityManager
                .createQuery("SELECT c FROM CouponDBModel  c WHERE c.name = : name", CouponDBModel.class);
        query.setParameter("name",name);
        CouponDBModel dbResult =query.getSingleResult();
        return Coupon.builder()
                .name(dbResult.getName())
                .percentage(dbResult.getPercentage())
                .expirationDate(dbResult.getExpirationDate())
                .build();
    }
}
