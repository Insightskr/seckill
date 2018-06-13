package com.ins.seckill.entity;

import java.util.Date;

/**
 * @author blue
 */
public class SucceedKilled {
    private long seckillId;

    private long userId;

    private short status;

    private Date createTime;

    /**
     * 引入秒杀实体，实现多对一
     * 因为一个秒杀成功的实体，可能对应着多条成功记录
     */
    private Seckill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SucceedKilledDao{" +
                "seckillId=" + seckillId +
                ", userId=" + userId +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
