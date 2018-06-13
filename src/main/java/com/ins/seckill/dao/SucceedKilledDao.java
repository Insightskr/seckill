package com.ins.seckill.dao;

import com.ins.seckill.entity.SucceedKilled;
import org.apache.ibatis.annotations.Param;

/**
 * The interface Success killed.
 * @author blue
 */
public interface SucceedKilledDao {
    /**
     * 插入秒杀成功明细
     * 由于使用了联合主键，可以过滤重复秒杀
     *
     * @param seckillId 秒杀商品 id
     * @param userId    用户 id
     * @return 修改行树
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userId") long userId);

    /**
     * 根据 id 查询 SucceedKilledDao 并携带返回 Seckill 实体
     *
     * @param seckillId 秒杀商品 id
     * @return 秒杀成功明细
     */
    SucceedKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userId") long userId);
}
