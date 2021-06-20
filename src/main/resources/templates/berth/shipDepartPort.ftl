<!DOCTYPE html>
<html lang="en">
<head>

    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<#-- <script type="text/javascript" src="../static/js/check.js"></script>-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>当天离港班轮</title>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                离港班轮查询
            </h3>
        <#if status == "1">
            <ul class="nav nav-tabs">
                <li class= "active" >
                    <a href="getShipDepartPort">GOCT</a>

                <li >
                    <a href="getShipDepartPort2" >NCT</a>
                </li>
            </ul>
        <#else>
            <ul class="nav nav-tabs">
                <li >
                    <a href="getShipDepartPort">GOCT</a>

                <li   class= "active">
                    <a href="getShipDepartPort2" >NCT</a>
                </li>
            </ul>
        </#if>
        <#if berthList??>
            <#list berthList  as berth>
                <h4 class="text-info">
                ${berth_index+1}.${berth.vslName}(${berth.oldVslName})
                </h4>
                <table class="table table-striped">

                    <tr> <td>船代码</td>  <td>${berth.vslCd!}</td></tr>
                   <#-- <tr> <td>船名</td>  <td>${berth.vslName}</td></tr>
                    <tr> <td>中文船名</td>  <td>${berth.oldVslName}</td></tr>
                    <tr> <td>进口航线</td>  <td>${berth.inLane}</td></tr>
                    <tr> <td>出口航线</td>  <td>${berth.outLane}</td></tr>-->
                    <tr> <td>进/出口航次</td>  <td>${berth.inVoy!}/${berth.outVoy!}</td></tr>

                    <tr> <td>ATB</td>  <td>${berth.atb!}</td></tr>

                    <tr> <td>ATW</td>  <td>${berth.atw!}</td></tr>
                    <tr> <td>ATD</td>  <td>${berth.atd}</td></tr>
                </table>
            </#list>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>