package com.ins.seckill.web;

import com.ins.seckill.dto.Exposer;
import com.ins.seckill.dto.SeckillExecution;
import com.ins.seckill.dto.SeckillResult;
import com.ins.seckill.entity.Seckill;
import com.ins.seckill.enums.SeckillStatusEnum;
import com.ins.seckill.exception.RepeatKillException;
import com.ins.seckill.exception.SeckillCloseException;
import com.ins.seckill.exception.SeckillException;
import com.ins.seckill.service.SeckillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The type Seckill controller.
 *
 * @author blue
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    /**
     * The Logger.
     */
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    /**
     * 跳转秒杀商品列表
     *
     * @param model the model
     * @return the list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        List<Seckill> list = seckillService.listSeckill();
        model.addAttribute("list", list);
        return "list";
    }

    /**
     * 跳转秒杀商品详情页
     *
     * @param seckillId the seckill id
     * @param model     the model
     * @return the string
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 暴露秒杀商品 URL
     *
     * @param seckillId the seckill id
     * @return the seckill result
     */
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new SeckillResult<>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀操作
     *
     * @param seckillId the seckill id
     * @param MD5       the md 5
     * @param userId    the user id
     * @return the seckill result
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    public SeckillResult<SeckillExecution> excute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String MD5,
                                                  @CookieValue(value = "killPhone", required = false) Long userId) {
        try {
            SeckillExecution execution = seckillService.executionSeckill(seckillId, userId, MD5);
            return new SeckillResult<>(true, execution);
        } catch (RepeatKillException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatusEnum.REPEAT_KILL);
            return new SeckillResult<>(false, execution);
        } catch (SeckillCloseException e) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatusEnum.END);
            return new SeckillResult<>(false, execution);
        } catch (SeckillException e) {
            logger.error(e.getMessage());
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatusEnum.INNER_ERROR);
            return new SeckillResult<>(false, execution);
        }
    }

    /**
     * Time seckill result.
     *
     * @return the seckill result
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<>(true, now.getTime());
    }
}
