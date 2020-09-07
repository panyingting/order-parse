package com.jiao.order.parse.entity;

import javax.persistence.*;

@Entity
@Table
public class BaseAutoIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long ct;
    private long ut;
    private long ver;
    private byte del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCt() {
        return ct;
    }

    public void setCt(long ct) {
        this.ct = ct;
    }

    public long getUt() {
        return ut;
    }

    public void setUt(long ut) {
        this.ut = ut;
    }

    public long getVer() {
        return ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    public byte getDel() {
        return del;
    }

    public void setDel(byte del) {
        this.del = del;
    }
}
