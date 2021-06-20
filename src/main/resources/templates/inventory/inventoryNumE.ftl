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
    <title>可提吉柜数量查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                可提吉柜数量查询
            </h3>
            <form role="form" action="/getInventoryNumE" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">箱属</label><input type="text" class="form-control" id="ptnrCode" name="ptnrCode" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

            <h3  class="text-info" style="text-align: center">
                查询结果
            </h3>
        <#--<#if inventoryList??>
            <#list inventoryList  as inventory>
                <h4  class="text-info">
                ${inventory.inventoryId}.${inventory.priCode}.${inventory.storageCode}
                </h4>
                <table class="table table-striped">
                    <tr> <td>码头上锁标识</td>  <td>${inventory.tholdChk!}</td></tr>

                    <tr> <td>箱型</td>  <td>${inventory.priCode!}</td></tr>
                    <tr> <td>可提数量</td>  <td>${inventory.total!}</td></tr>

                    <tr> <td>箱属</td>  <td>${inventory.ptnrCode!}</td></tr>

                    <tr> <td>介绍</td>  <td>${inventory.storageCode!}</td></tr>


                </table>
            </#list>
        <#else>
        </#if>-->

        <#if ptnrCode??>
            <h4  class="text-info">
                箱属：${ptnrCode}
            </h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>码头上锁</th>
                <th>箱型</th>
                <th>用箱规则</th>
                <th>数量</th>
            </tr>
            </thead>
        <#else>
        </#if>



        <#if inventoryList??>
            <#list inventoryList  as inventory>
                <tr>
                    <td>${inventory.tholdChk!}</td>
                    <td>${inventory.priCode!}</td>

                    <td>${inventory.storageCode!}</td>
                    <td>${inventory.total!}</td>
                </tr>




            </#list>

        <#else>
        </#if>
        </table>


        <#--   </tbody>-->

        </div>
    </div>
</div>
</body>
</html>