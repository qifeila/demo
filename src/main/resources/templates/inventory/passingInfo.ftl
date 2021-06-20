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
    <title>过机信息查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                过机信息查询
            </h3>
            <form role="form" action="/getPassingInfo" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">柜号（多个柜号以“.”间隔）\提单号\预约号</label><input type="text" class="form-control" id="container" name="container" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

            <h3  class="text-info" style="text-align: center">
                查询结果
            </h3>
<#if container??>
    <h4  >
        您搜索信息为：${container}
    </h4>
<#else>
</#if>
        <#if inventoryList??>
            <#list inventoryList  as inventory>
                <h4  class="text-info">
                ${inventory.inventoryId}.${inventory.cntrNo}
                </h4>
                <table class="table table-striped">
                    <tr> <td>空重</td>  <td>${inventory.fe!}</td></tr>
                    <tr> <td>提单号</td>  <td>${inventory.blNo!}</td></tr>
                    <tr> <td>预约号</td>  <td>${inventory.bookingNo!}</td></tr>
                    <tr> <td>过机时间</td>  <td>${inventory.passDate!}</td></tr>
                </table>
            </#list>
        <#else>
        </#if>

       <#-- <#if container??>
            <h4  class="text-info">
                您搜索信息为：${container}
            </h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>码头上锁</th>
                <th>箱型</th>
                <th>介绍</th>
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
        </table>-->


        <#--   </tbody>-->

        </div>
    </div>
</div>
</body>
</html>