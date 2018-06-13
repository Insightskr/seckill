package com.ins.seckill.service.Impl;

import com.ins.seckill.dto.Exposer;
import com.ins.seckill.dto.SeckillExecution;
import com.ins.seckill.entity.Seckill;
import com.ins.seckill.exception.RepeatKillException;
import com.ins.seckill.exception.SeckillCloseException;
import com.ins.seckill.service.SeckillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        value = {"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-redis.xml"})
public class SeckillServiceImplTest {
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.listSeckill();
        for (Seckill seckill : seckillList) {
            System.out.println(seckill);
        }

    }

    @Test
    public void getById() {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        System.out.println(exposer);
    }

    @Test
    public void executionSeckill() {
        long id = 1000L;
        long userId = 15251102138L;
        String md5 = "6e2c032073a72e4bcd47153c4a23a853";
        SeckillExecution seckillExecution = seckillService.executionSeckill(id, userId, md5);
        System.out.println(seckillExecution);
    }

    @Test
    public void testSeckillExecution(){
        long id = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("秒杀地址 = "+exposer.toString());
            long userId = 15251102139L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executionSeckill(id, userId, md5);
                logger.info("秒杀结果 = "+seckillExecution.toString());
            } catch (RepeatKillException|SeckillCloseException e) {
               logger.error(e.getMessage());
            }
        }else {
            logger.warn("秒杀未开启:",exposer);
        }
    }
}