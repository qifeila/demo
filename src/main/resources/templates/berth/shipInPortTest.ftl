<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<#-- <script type="text/javascript" src="../static/js/check.js"></script>-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <meta charset="UTF-8">
    <title>班轮集港计划</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3  class="text-info" style="text-align: center">
                未来十天船期查询
            </h3>

        <#if berthList??>
            <#list berthList  as berth>
                <h4  class="text-info">
                ${berth.berthId}.${berth.vslName}(${berth.oldVslName})
                </h4>
                <table class="table table-striped">

                    <tr> <td>船代码</td>  <td>${berth.vslCd!}</td></tr>
                    <tr> <td>进/出口航次</td>  <td>${berth.inVoy!}/${berth.outVoy!}</td></tr>
                    <tr> <td>ETB</td>  <td>${berth.etb!}</td></tr>
                    <tr> <td>闸口截箱时间</td>  <td>${berth.yardClose!}</td></tr>
                    <tr> <td>IMO</td>  <td>${berth.inmarsat!}</td></tr>
                </table>
            </#list>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
</html>