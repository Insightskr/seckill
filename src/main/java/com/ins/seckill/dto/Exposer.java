package com.ins.seckill.dto;

/**
 * 暴露秒杀地址的 DTO
 *
 * @author blue
 */
public class Exposer {

    /**
     * 是否暴露秒杀地址
     */
    private boolean exposed;

    private long seckillId;

    private String md5;

    /**
     * 系统当前时间,单位为毫秒
     */
    private long now;

    /**
     * 秒杀开始时间
     */
    private long start;

    /**
     * 秒杀结束时间
     */
    private long end;

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, String md5) {
        this(exposed,seckillId);
        this.md5 = md5;
    }

    public Exposer(boolean exposed, long seckillId,long now, long start, long end) {
        this(exposed,seckillId);
        this.now = now;
        this.start = start;
        this.end = end;
    }


    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
