package com.ins.seckill.exception;

/**
 * 秒杀相关业务异常
 *
 * @author blue
 */
public class SeckillException extends RuntimeException{


    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
