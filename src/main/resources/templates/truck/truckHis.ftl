<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>拖车历史作业记录</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
            基于月份查询拖车历史作业
        </h3>
        <form role="form" action="/getTruckHis" method="post">

            <div class="form-group">
                <label for="exampleInputPassword1">年月（六个数字）</label><input type="text" class="form-control" id="time" name="time" />
            </div>
           <#-- <div class="form-group">
                <label for="exampleInputPassword1">月份（两个数字）</label><input type="text" class="form-control" id="month" name="month" />
            </div>-->
            <div class="form-group">
                <label for="exampleInputPassword1">车牌号</label><input type="text" class="form-control" id="headNo" name="headNo" />
            </div>
            <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
        </form>

            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>

        <#if truckList??>
            <#list truckList  as truck>
                <h4  class="text-info">
                ${truck.truckId}. <td>${truck.headNo}</td>
                </h4>
                <table class="table table-striped">
                    <tr> <td>作业类型.</td>  <td>${truck.gjobType!}</td></tr>
                    <tr> <td>柜号.</td>  <td>${truck.cntrNo!}</td></tr>
                    <tr> <td>进闸日期.</td>  <td>${truck.gpassDate!}</td></tr>
                    <tr> <td>出闸日期</td>   <td>${truck.outDate!}</td></tr>


                </table>
            </#list>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>