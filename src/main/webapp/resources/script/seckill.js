//存放秒杀详情页的主要交互逻辑 js 代码

// js 模块化
var seckill = {
    //封装秒杀相关的 ajax 请求中的 url
    URL: {
        now: "/seckill/time/now",
        exposer: function (seckillId) {
            return "/seckill/"+seckillId+"/exposer";
        },
        execution: function (seckillId,md5) {
            return "/seckill/"+seckillId+"/"+md5+"/execution"
        }
    },
    //秒杀执行模块
    handleSeckill: function (seckillId, node) {
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),function (result) {
            if (result && result['success']){
                var exposer = result['data'];
                //如果允许暴露，将暴露出来的值绑定秒杀按钮
                if (exposer['exposed']){
                    var killUrl = seckill.URL.execution(seckillId,exposer['md5']);
                    console.log(killUrl);
                    $('#killBtn').one('click',function () {
                        //1.禁用按钮
                        $(this).addClass('disable');
                        //2.发送秒杀请求
                        $.post(killUrl,function (result) {
                            if (result && result['success']) {
                                node.html('<span class="label label-success">秒杀成功</span>');
                            }else {
                                var killResult = result['data'];
                                var statuInfo = killResult.statusInfo;
                                node.html('<span class="label label-danger">'+ statuInfo+'</span>');
                            }
                        })
                    });
                    node.show();
                }else {
                    //如果还未允许暴露，则进入倒计时
                    var nowTime = exposer['now'].time;
                    var startTime = exposer['startTime'].time;
                    var endTime = exposer['endTime'].time;
                    seckill.countdown(seckillId,nowTime,startTime,endTime);
                }
            } else {
                console.log(result);
            }
        })
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length === 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //倒计时
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var box = $('#seckill-box');
        if (nowTime > endTime) {
            //秒杀已结束
            box.html("秒杀已结束")
        } else if (nowTime < startTime) {
            //秒杀未开启
            //加 1 秒是为了校准时间
            var killTime = new Date(startTime + 1000);
            box.countdown(killTime, function (event) {
                var format = event.strftime('离开始还剩 %D 天 %H 时 %M 分 %S 秒');
                box.html(format);
            }).on('finish.countdown', function () {
                //倒计时结束后调用该函数
                seckill.handleSeckill(seckillId, box);
            })
        } else {
            //秒杀正在进行
            seckill.handleSeckill(seckillId, box);
        }
    },
    //详情页的秒杀逻辑
    detail: {
        //详情页的初始化
        init: function (params) {
            //手机验证和登录，计时交互
            // cookie 中获取手机号
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $('#killPhoneButton').click(function () {
                    var inputPhone = $('#KillPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //写入 cookie ，持久时间设置为2天，作用路径设置为该模块下面
                        $.cookie('killPhone', inputPhone, {expires: 2, path: '/seckill'});
                        // 刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html("<label class='label label-danger'>手机号错误</label>").show(150);
                    }
                });
            }
            //如果已经登陆，开始计时交互
            $.get(seckill.URL.now, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result: ' + result);
                }
            })
        }
    }
}