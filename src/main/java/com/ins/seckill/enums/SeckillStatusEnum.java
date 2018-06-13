package com.ins.seckill.enums;

/**
 * 秒杀状态信息
 *
 * @author blue
 */
public enum SeckillStatusEnum {

    /**
     * 秒杀成功
     */
    SUCCESS(1, "秒杀成功"),

    /**
     * 秒杀结束
     */
    END(0, "秒杀结束"),

    /**
     * 重复秒杀
     */
    REPEAT_KILL(-1, "重复秒杀"),

    /**
     * 系统异常
     */
    INNER_ERROR(-2, "系统异常"),

    /**
     * 数据篡改
     */
    DATA_REPEAT(-3, "数据篡改");

    private final int status;

    private final String statuInfo;

    SeckillStatusEnum(int status, String statuInfo) {
        this.status = status;
        this.statuInfo = statuInfo;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets statu info.
     *
     * @return the statu info
     */
    public String getStatuInfo() {
        return statuInfo;
    }


    /**
     * 根据索引返回对应的秒杀状态信息
     *
     * @param index the index
     * @return the seckill status enum
     */
    public static SeckillStatusEnum stateOf(int index) {
        for (SeckillStatusEnum statusEnum : values()) {
            if (statusEnum.getStatus() == index) {
                return statusEnum;
            }
        }
        return null;
    }
}
