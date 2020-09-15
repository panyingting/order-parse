package com.jiao.order.parse.entity.coupon;


import com.jiao.order.parse.entity.BaseAutoIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 优惠券实体
 */
@Entity
@Table(name = "t_coupon")
public class CouponEntity extends BaseAutoIdEntity {

    private static final long serialVersionUID = -635696521739321661L;
    /**
     * 名称
     */
    private String name;

    /**
     * 1满减 2折扣， 3红包
     */
    private byte type;
    /**
     * 状态
     */
    private byte stat;
    /**
     * 领取后生效时间 整数 天 默认 0
     */
    private int fixedDays;
    /**
     * 面值
     */
    private long faceValue;
    /**
     * 折扣
     */
    private int discountValue;
    /**
     * 优惠券 限额 默认 0 不限制
     */
    private long limitMoney;
    /**
     * 优惠券有效期 起始时间
     */
    private long limitStartTime;
    /**
     * 优惠券有效期 结束时间
     */
    private long limitEndTime;

    /**
     * 是否累计， 0-不累计：只有商品价格到达限额了才能使用优惠券，
     *          1- 累计：是累加商品价格到达限额了都可用；
     */
    private int accumulate ;

    /**
     * 描述，（红包时放置红包金额组）
     */
    private String desc;

    /**
     * 初始优惠券数
     */
    private int initNum;

    /**
     * 已发放优惠券数
     */
    private int deliveryNum;

    private String idEncode;

    /**
     * 默认系统优惠券 默认1 否则0；
     */
    private Integer isDefault;


    @Column(name = "face_value")
    public long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(long faceValue) {
        this.faceValue = faceValue;
    }

    @Column(name = "discount_value")
    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Column(name = "stat")
    public byte getStat() {
        return stat;
    }

    public void setStat(byte stat) {
        this.stat = stat;
    }

    @Column(name = "fixed_days")
    public int getFixedDays() {
        return fixedDays;
    }

    public void setFixedDays(int fixedDays) {
        this.fixedDays = fixedDays;
    }

    @Column(name = "limit_money")
    public long getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(long limitMoney) {
        this.limitMoney = limitMoney;
    }

    @Column(name = "limit_start_time")
    public long getLimitStartTime() {
        return limitStartTime;
    }

    public void setLimitStartTime(long limitStartTime) {
        this.limitStartTime = limitStartTime;
    }

    @Column(name = "limit_end_time")
    public long getLimitEndTime() {
        return limitEndTime;
    }

    public void setLimitEndTime(long limitEndTime) {
        this.limitEndTime = limitEndTime;
    }

    @Column(name="accumulate")
    public int getAccumulate() {
        return accumulate;
    }

    public void setAccumulate(int accumulate) {
        this.accumulate = accumulate;
    }

    @Column(name="init_num")
    public int getInitNum() {
        return initNum;
    }

    public void setInitNum(int initNum) {
        this.initNum = initNum;
    }

    @Column(name="delivery_num")
    public int getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(int deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    @Column(name = "id_encode")
    public String getIdEncode() {
        return idEncode;
    }

    public void setIdEncode(String idEncode) {
        this.idEncode = idEncode;
    }

    @Column(name="is_default")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}
