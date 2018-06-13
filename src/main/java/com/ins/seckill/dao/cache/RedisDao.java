package com.ins.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ins.seckill.entity.Seckill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * The type Redis dao.
 *
 * @author blue
 */
@Component
public class RedisDao {
    /**
     * 设置 key 值的前缀
     */
    private static final String KEY_PREFIX = "seckill:";
    /**
     * log4j 对象
     */
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);


    /**
     * 获取一个秒杀对象
     *
     * @param seckillId 秒杀商品id
     * @return 秒杀对象 seckill
     */
    public Seckill getSeckill(long seckillId){
        try(Jedis jedis = jedisPool.getResource()){
            String key = setPrefix(seckillId);
            byte[] bytes = jedis.get(key.getBytes());
            //从缓存中重新获取到对象
            if (bytes != null){
                //通过 schema 创建空对象
                Seckill seckill = schema.newMessage();
                //通过这个方法将获取到的 byte[] 进行反序列化
                ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                return seckill;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 设置一个秒杀对象
     *
     * @param seckill 秒杀对象
     * @return redis操作提示 string
     */
    public String putSeckil(Seckill seckill){
        try(Jedis jedis = jedisPool.getResource()) {
            String key = setPrefix(seckill.getSeckillId());
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            // 设置缓存超时时间
            int timeout = 60*60;
            String result = jedis.setex(key.getBytes(),timeout,bytes);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    private String setPrefix(long key) {
        String result=KEY_PREFIX+"_"+key;
        return result;
    }
}
