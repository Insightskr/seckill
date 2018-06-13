package com.ins.seckill.dao;

import com.ins.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * The interface Seckill dao.
 * @author blue
 */
public interface SeckillDao {
    /**
     * 减少商品库存
     *
     * @param seckillId 秒杀商品id
     * @param killTime  秒杀时间
     * @return 操作成功的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 通过id查询秒杀对象
     *
     * @param seckillId the 秒杀商品id
     * @return 秒杀商品记录
     */
    Seckill queryById(@Param("seckillId") long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset 偏移量
     * @param limit  限制值
     * @return 秒杀商品列表
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
