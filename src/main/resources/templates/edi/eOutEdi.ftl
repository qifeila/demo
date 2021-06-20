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
                吉出重回EDI查询
            </h3>
            <form role="form" action="/getEOutEdi" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">订舱号</label><input type="text" class="form-control" id="soNo" name="soNo" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (ediList??) && (ediList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <#list ediList  as edi>
                <h4  class="text-info">

                订舱号：${soNo}
                </h4>
                <table class="table table-striped">

                    <tr> <td>箱型</td>  <td>${edi.sztp!}</td></tr>
                    <tr> <td>箱属</td>  <td>${edi.ptnrCode!}</td></tr>
                    <tr> <td>数量</td>  <td>${edi.bookQty!}</td></tr>
                    <tr> <td>卸货港</td>  <td>${edi.pod!}</td></tr>
                    <tr> <td>最终目的港</td>  <td>${edi.fpod!}</td></tr>
                    <tr> <td>船名</td>  <td>${edi.oldVslName!}</td></tr>
                    <tr> <td>出口航次</td>  <td>${edi.outVoy!}</td></tr>




                </table>
            </#list>
        <#else>
            <#--<div class="col-md-12 column">

                <div class="alert alert-dismissable alert-success">
                    <h4>无查询结果！！！</h4><br>
                    <strong>提示：</strong>搜索内容为${soNo!}<br>
                </div>
            </div>-->
        </#if>
        </div>
    </div>
</div>
</body>
</html>