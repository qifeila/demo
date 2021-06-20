<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<#-- <script type="text/javascript" src="../static/js/check.js"></script>-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<#--<style>
    td {text-align:center}
</style>-->
    <meta charset="UTF-8">
    <title>危险品查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                危险品查询
            </h3>
            <form role="form" action="/getDangerousInventory" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">柜号/堆场</label><input type="text" class="form-control" id="container" name="container" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>


        <#if (inventoryList??)&&(inventoryList ?size >0)>
            <h3  class="text-info" style="text-align: center">
                查询结果
            </h3>
            <#list inventoryList  as inventory>
                <h4  class="text-info">
                ${inventory_index+1}.${inventory.cntrNo}
                </h4>
                <table class="table table-striped">
                    <tr> <td>重量</td>  <td>${inventory.wgt!}</td></tr>
                    <tr> <td>箱型</td>  <td>${inventory.sztp!}</td></tr>
                    <tr> <td>危险品类</td>  <td>${inventory.imdg!}</td></tr>
                    <tr> <td>UNNO</td>  <td>${inventory.unno!}</td></tr>
                    <tr> <td>危险品名称</td>  <td>${inventory.dangerName!}</td></tr>
                    <tr> <td>进入堆场时间</td>  <td>${inventory.inYardTime!}</td></tr>
                    <tr> <td>所在堆场</td>  <td>${inventory.pos!}</td></tr>


                </table>
            </#list>
        <#else>
            <#if container??>
                <div class="alert alert-success">
                    <h4>无返回结果！！！</h4>
                   <strong>提示：</strong> 请检查柜号${container!}是否输入错误。
                </div>
            <#else>

            </#if>

        </#if>


        <#--   </tbody>-->

        </div>
    </div>
</div>
</body>
</html>