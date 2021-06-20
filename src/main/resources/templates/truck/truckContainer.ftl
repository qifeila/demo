<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>预约信息查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
            预约信息查询
        </h3>
        <form role="form" action="/getTruckContainer" method="post">

            <div class="form-group">
                <label for="exampleInputPassword1">柜号/作业号</label><input type="text" class="form-control" id="container" name="container" />
            </div>
            <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
        </form>

            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>

        <#if truckList??>
            <#list truckList  as truck>
                <h4  class="text-info">
                ${truck.truckId}. 作业号：<td>${truck.tagNo}</td>
                </h4>
                <table class="table table-striped">

                    <tr> <td>柜号</td>  <td>${truck.cntrNo!}</td></tr>
                    <tr> <td>箱属</td>  <td>${truck.ptnrCode!}</td></tr>

                    <tr> <td>箱型</td>  <td>${truck.sztp2!}</td></tr>
                    <tr> <td>预约号</td>  <td>${truck.bookingNo!}</td></tr>


                </table>
            </#list>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>