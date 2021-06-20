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
    <title>查验信息查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                查验信息查询
            </h3>
            <form role="form" action="/getCheckInfo" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">柜号\提单号</label><input type="text" class="form-control" id="container" name="container" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

            <h3  class="text-info" style="text-align: center">
                查询结果
            </h3>

        <#if (container??) && container?length gt 11>
            <h4 >
                所查询单号为：${container}
            </h4>
            <#if inventoryList??>
                <h4  class="text-info">
                    查验信息：
                </h4>
             <#list inventoryList  as inventory>
                <h4  class="text-info">
                ${inventory.inventoryId}.柜号：${inventory.cntrNo}
                </h4>
                <table class="table table-striped">
                    <tr> <td>查验单位</td>  <td>${inventory.requester!}</td></tr>
                    <tr> <td>尺寸</td>  <td>${inventory.sztp!}</td></tr>
                   <#-- <tr> <td>提单号</td>  <td>${inventory.blNo!}</td></tr>-->
                    <tr> <td>箱属</td>  <td>${inventory.ptnrCode!}</td></tr>
                    <tr> <td>货名</td>  <td>${inventory.cargoType!}</td></tr>
                    <tr> <td>状态</td>  <td>${inventory.status!}</td></tr>
                </table>
            </#list>
          <#else>
        </#if>

        <#elseif  (container??) && container?length gt 0>
            <h4 >
                所查询柜号为：${container}
            </h4>
            <#if inventoryList??>
                <h4 class="text-info">
                    查验信息：
                </h4>
            <#list inventoryList  as inventory>
                <h4  >
                ${inventory.inventoryId}.${inventory.blNo}
                </h4>
                <table class="table table-striped">
                    <tr> <td>查验单位</td>  <td>${inventory.requester!}</td></tr>
                    <tr> <td>尺寸</td>  <td>${inventory.sztp!}</td></tr>
                    <tr> <td>提单号</td>  <td>${inventory.cntrNo!}</td></tr>
                    <tr> <td>箱属</td>  <td>${inventory.ptnrCode!}</td></tr>
                    <tr> <td>货名</td>  <td>${inventory.cargoType!}</td></tr>
                    <tr> <td>状态</td>  <td>${inventory.status!}</td></tr>
                </table>
            </#list>
            <#else>
            </#if>
            <#if inventoryList1??>
                <h4  class="text-info">
                    查验进度
                </h4>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>地点</th>
                        <th>进度</th>

                    </tr>
                    </thead>

            <#list inventoryList1  as inventory>
                <tr><th>${inventory.operationTime!}</th>
                    <th>${inventory.origin!}</th>
                    <th>${inventory.description!}</th>
                </tr>

            </#list>
                </table>
            <#else>
            </#if>




        <#else>

        </#if>


        <#--   </tbody>-->

        </div>
    </div>
</div>
</body>
</html>