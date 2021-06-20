<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>拖车备案查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                拖车备案查询
            </h3>
            <form role="form" action="/getTruckData" method="post">

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
                拖车备案信息
                </h4>
                <table class="table table-striped">

                    <tr> <td>公司</td>  <td>${truck.companyName!}</td></tr>
                    <tr> <td>车牌</td>  <td>${truck.headNo!}</td></tr>
                    <tr> <td>车挂</td>  <td>${truck.truckBody!}</td></tr>
                    <tr> <td>车头类型</td>  <td>${truck.headType!}</td></tr>
                    <tr> <td>重量（kg）</td>  <td>${truck.weight!}</td></tr>

                    <tr> <td>有效日期</td>  <td>${truck.expiryDate!}</td></tr>
                    <tr> <td>禁止进闸原因</td>  <td>${truck.reason!}</td></tr>


                </table>
            </#list>
        *如对备案信息有疑问，请致电34661861。
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>