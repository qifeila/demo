<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>提单号查询拖车作业</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
           基于提单号查询拖车历史作业
        </h3>
        <form role="form" action="/getTruckBLNO" method="post">

            <div class="form-group">
                <label for="exampleInputPassword1">提单号</label><input type="text" class="form-control" id="BLNO" name="BLNO" />
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

                    <tr> <td>柜号.</td>  <td>${truck.cntrNo!}</td></tr>

                    <tr> <td>进闸日期.</td>  <td>${truck.gpassDate!}</td></tr>
                    <tr> <td>出闸日期.</td>  <td>${truck.outDate!}</td></tr>

                    <tr> <td>作业号</td>   <td>${truck.sicNo!}</td></tr>

                </table>
            </#list>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>