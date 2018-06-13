package com.ins.seckill.dto;

import com.ins.seckill.entity.SucceedKilled;
import com.ins.seckill.enums.SeckillStatusEnum;

/**
 * 封装秒杀执行后的结果
 *
 * @author blue
 */
public class SeckillExecution {

    private long seckillId;

    /**
     * 秒杀执行结果状态标识码
     */
    private int status;

    /**
     * 秒杀执行结果状态信息
     */
    private String statusInfo;

    /**
     * 秒杀成功对象
     */
    private SucceedKilled succeedKilled;

    public SeckillExecution(long seckillId, SeckillStatusEnum statusEnum, SucceedKilled succeedKilled) {
        this(seckillId, statusEnum);
        this.succeedKilled = succeedKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatusEnum statusEnum) {
        this.seckillId = seckillId;
        this.status = statusEnum.getStatus();
        this.statusInfo = statusEnum.getStatuInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SucceedKilled getSucceedKilled() {
        return succeedKilled;
    }

    public void setSucceedKilled(SucceedKilled succeedKilled) {
        this.succeedKilled = succeedKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                ", succeedKilled=" + succeedKilled +
                '}';
    }
}
