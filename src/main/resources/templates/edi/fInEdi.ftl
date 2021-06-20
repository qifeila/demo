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
                重进EDI查询
            </h3>
            <form role="form" action="/getFInEdi" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">SO NO</label><input type="text" class="form-control" id="soNo" name="soNo" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (ediList??) && (ediList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <#list ediList  as edi>
                <h4  class="text-info">
               <#-- ${truck.truckId}. 作业号：<td>${truck.tagNo}</td>-->
                </h4>
                <table class="table table-striped">
                    <tr> <td>VVD</td>  <td>${edi.vvd!}</td></tr>
                    <tr> <td>callSign</td>  <td>${edi.callSign!}</td></tr>
                    <tr> <td>SO NO</td>  <td>${edi.soNo!}</td></tr>

                </table>
            </#list>
        <#else>
           <#-- <div class="col-md-12 column">

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