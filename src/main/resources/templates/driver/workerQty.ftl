<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>中控计件查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                中控计件查询
            </h3>
            <form role="form" action="/workerQty?weixinid=${weixinId}" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">年月</label><input type="text" class="form-control"
                                                                        value="${month!}" placeholder="${month!}" id="month" name="month" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (driverList1??) && (driverList1?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4 class="text-info" >数据仅供参考，请以统计组提供数据为准，谢谢！ </h4>
            <h4 class="text-info" >姓名：${driverName}  工号：${catosId}</h4>
            <h4 class="text-info" >合计：${sum!?c}</h4>
            <table class="table table-striped">
                <thead>
                <tr><th>日期</th>
                    <th>总计</th>
                </tr>
                </thead>
                <tbody>
                    <#list driverList1  as driver>
                    <tr>
                        <td>${driver.day}</td>
                        <td>${driver.qty!}</td>

                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <#if month??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>无查询结果！！！</h4><br>
                    搜索内容为<strong>${month!}</strong>,请检查后重新输入！<br>
                </div>
            <#else>
            </#if>
            <#if tip??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>ERROR</h4><br>
                    <strong>${tip!}!!!</strong><br>
                </div>
            <#else>
            </#if>

        </#if>


        </div>
    </div>
</div>
</body>
</html>