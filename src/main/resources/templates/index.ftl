<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta charset="UTF-8">
    <title>公共查询</title>
</head>
<body>
<div class="container">
    <div class="" row
    ">
    <div class="span4">
        <h3 class="text-center text-info">
            1. 船期查询
        </h3>
        <a class="btn btn-block btn-primary  btn-lg" href="/getBerthPlan">班轮集港计划</a>
        <br>
        <a class="btn  btn-block btn-primary  btn-lg" href="/getShipInPort">当前在港班轮</a>
        <br>
        <a class="btn  btn-block btn-primary  btn-lg" href="/getBargeInPort">当前在港驳船</a>
        <br>
        <a class="btn btn-block btn-primary  btn-lg" href="/getShipDepartPort">当天离港班轮</a>
        <br>
    </div>


    <div class="span4">
        <h3 class="text-center text-info">
            2. 外拖查询
        </h3>
        <a class="btn btn-block btn-info  btn-lg" href="/getTruckPlace1">当前作业查询</a>
        <br>
        <a class="btn  btn-block btn-info btn-lg" href="/getTruckTime1">周转时间查询</a>
        <br>
        <a class="btn  btn-block btn-info btn-lg" href="/getTruckContainer1">预约信息查询</a>
        <br>
        <a class="btn  btn-block btn-info btn-lg" href="/getTruckBLNO1">提单号查询拖车历史作业</a>
        <br>
        <a class="btn  btn-block btn-info btn-lg" href="/getContainerHis1">集装箱进出闸记录</a>
        <br>
        <a class="btn  btn-block btn-info btn-lg" href="/getTruckHis1">拖车历史作业记录</a>
        <br>
        <#--<a class="btn  btn-block btn-info" href="/getTruckData1">备案资料查询</a>-->
        <#--<br>-->


    </div>

    <div class="span4">
        <h3 class="text-center text-info">
            3. 集装箱查询
        </h3>
        <a class="btn  btn-block btn-warning btn-lg" href="/getInventoryInPort1">在场信息查询</a>
        <br>
        <a class="btn  btn-block btn-warning btn-lg" href="/getInventoryNumE1">可提吉柜数量查询</a>
        <br>
        <a class="btn  btn-block btn-warning btn-lg" href="/getCheckInfo1">查验信息查询</a>
        <br>
        <a class="btn  btn-block btn-warning btn-lg" href="/getPassingInfo1">过机信息查询</a>
        <br>

    </div>

    <div class="span4">
        <h3 class="text-center text-info">
            4. EDI查询
        </h3>
        <a class="btn  btn-block btn-danger btn-lg" href="/getFInEdi1">重进EDI查询</a>
        <br>
        <#--<a class="btn  btn-block btn-danger btn-lg" href="/getEOutEdi1">吉出重回EDI查询</a>-->
        <a class="btn  btn-block btn-danger btn-lg" href="/getEoutEdiNew">提吉EDI查询</a>
        <br>
    </div>
    <div class="span4">
        <h3 class="text-center text-info">
            5. 基本代码查询
        </h3>
        <a class="btn  btn-block btn-success btn-lg" href="/getPtnrCode">箱属代码查询</a>
        <br>
        <a class="btn  btn-block btn-success btn-lg" href="/getCntrRule">用箱规则查询</a>
        <br>
        <a class="btn  btn-block btn-success btn-lg" href="/getPortCode">港口代码查询</a>
        <br>
        <a class="btn  btn-block btn-success btn-lg" href="/getDangerCode">危险品代码查询</a>
        <br>
    </div>
   <#-- <div class="span4">
        <h3 class="text-center text-info">
            5. 计件查询
        </h3>
        <a class="btn  btn-block btn-info" href="/driver?weixinid=1">计件查询</a>

        <br>
    </div>-->
</div>
</div>

</body>
</html>