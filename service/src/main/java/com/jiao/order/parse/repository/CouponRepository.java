package com.jiao.order.parse.repository;

import com.jiao.order.parse.entity.MessageRegular;
import com.jiao.order.parse.entity.coupon.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<CouponEntity, Integer> {


    CouponEntity getById(int id);

}
