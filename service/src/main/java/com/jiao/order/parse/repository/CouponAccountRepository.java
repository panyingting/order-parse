package com.jiao.order.parse.repository;

import com.jiao.order.parse.entity.coupon.CouponAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponAccountRepository  extends JpaRepository<CouponAccountEntity, Integer> {

    List<CouponAccountEntity> findByUserName(String userName);

    List<CouponAccountEntity> findByUserNameAndCouponId(String userName, long couponId);

    CouponAccountEntity getById(int id);
}
