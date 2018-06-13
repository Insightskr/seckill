package com.ins.seckill.dao;

import com.ins.seckill.entity.SucceedKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SucceedKilledTest {

    @Autowired
    private SucceedKilledDao succeedKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1001L;
        long userId = 15251102137L;
        int i = succeedKilledDao.insertSuccessKilled(id, userId);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1001L;
        long userId = 15251102137L;
        SucceedKilled i = succeedKilledDao.queryByIdWithSeckill(id, userId);
        System.out.println(i);
        System.out.println(i.getSeckill());
    }
}