package com.ins.seckill.service;

import com.ins.seckill.dto.Exposer;
import com.ins.seckill.dto.SeckillExecution;
import com.ins.seckill.entity.Seckill;
import com.ins.seckill.exception.RepeatKillException;
import com.ins.seckill.exception.SeckillCloseException;
import com.ins.seckill.exception.SeckillException;

import java.util.List;

/**
 * 秒杀服务接口
 *
 * @author blue
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return 所有秒杀记录 seckill list
     */
    List<Seckill> listSeckill();

    /**
     * 根据ide查询单个秒杀记录
     *
     * @param seckillId 秒杀商品id
     * @return 单个秒杀记录 by id
     */
    Seckill getById(long seckillId);

    /**
     * 输出秒杀接口地址
     *
     * @param seckillId 秒杀商品id
     * @return exposer 对象，如果秒杀开启，那么 exposer 中存储的是秒杀接口地址 如果秒杀未开启，则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId 秒杀商品id
     * @param userId    用户id
     * @param md5       md5 值，用于验证秒杀请求是否为真
     * @return 秒杀执行结果：成功的信息或关于失败的异常
     * @throws SeckillException      the seckill exception
     * @throws RepeatKillException   重复秒杀异常
     * @throws SeckillCloseException 秒杀结束异常
     */
    SeckillExecution executionSeckill(long seckillId,long userId,String md5)
    throws SeckillException,RepeatKillException,SeckillCloseException;
}
