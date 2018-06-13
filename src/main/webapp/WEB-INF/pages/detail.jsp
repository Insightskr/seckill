<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<%@include file="common/tag.jsp" %>
<head>
    <title>秒杀商品详情页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<%-- 登录模态框 --%>
<div id="killPhoneModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center">
                    秒杀电话
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" class="form-control" name="killPhone" id="KillPhoneKey" placeholder="请输入手机号">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button id="killPhoneButton" type="button" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    登陆
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="container">

    <div class="panel panel-primary text-center">
        <div class="panel-heading"><h2>${seckill.name}</h2></div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- 引入 jQuery 的 cookie 插件--%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<%-- 引入 jQuery 的 countdown 插件--%>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<%-- 引入交互逻辑 --%>
<script src="${APP_PATH}/resources/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime: ${seckill.startTime.time},//加上time,转换为毫秒
            endTime:${seckill.endTime.time}
        });
    })
</script>
</html>