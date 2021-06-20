<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>基本代码查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                危险品代码查询
            </h3>
            <form role="form" action="/getDangerCode" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">危险品代码或类别</label><input type="text" class="form-control" id="unno" name="unno" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (codeList??) && (codeList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <#list codeList  as code>
            <h4  class="text-info">
                 输入：${unno!}
             </h4>
                <table class="table table-striped">
                    <tr> <td>CLASS</td>  <td>${code.dangerClass!}</td></tr>
                    <tr> <td>UNNO</td>  <td>${code.unno!}</td></tr>
                    <tr> <td>正确运输名称</td>  <td>${code.properShipName!}</td></tr>
                    <tr> <td>存储方式</td>  <td>${code.stowageSeg!}</td></tr>
                    <tr> <td>注意事项</td>  <td>${code.propertyObservation!}</td></tr>

                </table>
            </#list>
        <#else>
            <#if unno??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>无查询结果！！！</h4><br>
                    搜索内容为<strong>${unno!}</strong>,请检查后重新输入！<br>
                </div>
            <#else>
            </#if>

        </#if>
        </div>
    </div>
</div>
</body>
</html>