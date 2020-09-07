package com.jiao.order.parse.entity.coupon;


import com.jiao.order.parse.entity.BaseAutoIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户的优惠券账户实体
 */
@Entity
@Table(name = "t_coupon_account")
public class CouponAccountEntity extends BaseAutoIdEntity {

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 面值
     */

    private long faceValue = 0;

    /**
     * 优惠券ID
     */
    private long couponId;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券领取时间
     */
    private long getTime;
    /**
     * 优惠券生效时间
     */
    private long effectTime;
    /**
     * 优惠券失效时间
     */
    private long expirationTime;
    /**
     * 优惠券使用时间
     */
    private long useTime;
    /**
     * 优惠券使用类型 见 CouponAccountUseType
     */
    private int useStat;
    /**
     * 推广人ID
     */
    private long promoterId;

    /**
     * 备注信息
     */
    private String remark;

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "coupon_id")
    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }
    @Column(name = "coupon_name")
    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    @Column(name = "get_time")
    public long getGetTime() {
        return getTime;
    }

    public void setGetTime(long getTime) {
        this.getTime = getTime;
    }
    @Column(name = "effect_time")
    public long getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(long effectTime) {
        this.effectTime = effectTime;
    }
    @Column(name = "expiration_time")
    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
    @Column(name = "use_time")
    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }
    @Column(name = "use_stat")
    public int getUseStat() {
        return useStat;
    }

    public void setUseStat(int useStat) {
        this.useStat = useStat;
    }
    @Column(name = "promoter_id")
    public long getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(long promoterId) {
        this.promoterId = promoterId;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "face_value")
    public long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(long faceValue) {
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        return "CouponAccountEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", getTime=" + getTime +
                ", effectTime=" + effectTime +
                ", expirationTime=" + expirationTime +
                ", useTime=" + useTime +
                ", useStat=" + useStat +
                ", promoterId=" + promoterId +
                '}';
    }
}
