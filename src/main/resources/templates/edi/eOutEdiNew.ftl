<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>提吉Edi查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                提吉EDI查询
            </h3>
            <form role="form" action="/getEoutEdiNew" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">订舱号</label><input type="text" class="form-control" id="soNo"
                                                                         name="soNo" value="${soNo!}"/>
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提 交</button>
            </form>

        <#if objEdi??>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4 class="text-info">
                订舱号：${soNo!}
            </h4>
            <table class="table table-striped">

                <tr>
                    <td>箱型</td>
                    <td>${objEdi.iso_code!}</td>
                </tr>
                <tr>
                    <td>箱属</td>
                    <td>${objEdi.opr!}</td>
                </tr>

            </table>
        <#elseif soNo??>
            <br>
            <div class="">
                <h4>无查询结果！！！</h4><br>
            </div>
        </#if>
        </div>
    </div>
</div>
</body>
</html>