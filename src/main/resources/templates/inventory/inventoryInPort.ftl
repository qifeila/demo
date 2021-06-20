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
    <title>在场信息查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                在场信息查询
            </h3>
            <form role="form" action="/getInventoryInPort" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">柜号\提单号\订舱号</label><input type="text" class="form-control"
                                                                                id="container" name="container"/>
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提 交</button>
            </form>

            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
        <#if (inventoryList??)&&(inventoryList ?size >0)>
            <#list inventoryList  as inventory>
                <h4 class="text-info">
                ${inventory.inventoryId}.${inventory.cntrNo}
                </h4>
                <table class="table table-striped">
                    <tr>
                        <td>堆放港区</td>
                        <td>${inventory.terminal!}</td>
                    </tr>
                    <tr>
                        <td>进入码头时间</td>
                        <td>${inventory.inTime!}</td>
                    </tr>
                    <tr>
                        <td>船名航次</td>
                        <td>${inventory.vslNm!}</td>
                    </tr>

                    <tr>
                        <td>空重</td>
                        <td>${inventory.fe!}</td>
                    </tr>
                    <tr>
                        <td>箱型</td>
                        <td>${inventory.sztp!}</td>
                    </tr>
                    <tr>
                        <td>卸货港</td>
                        <td>${inventory.pod!}</td>
                    </tr>
                    <tr>
                        <td>装货港</td>
                        <td>${inventory.pol!}</td>
                    </tr>
                    <tr>
                        <td>进出口状态</td>
                        <td>${inventory.ixCd!}</td>
                    </tr>
                <#-- <tr> <td>tier</td>  <td>${inventory.tier}</td></tr>-->
                    <tr>
                        <td>箱属</td>
                        <td>${inventory.ptnrCode!}</td>
                    </tr>
                    <tr>
                        <td>重量</td>
                        <td>${inventory.wgt!}</td>
                    </tr>
                <#-- <tr> <td>提单号</td>  <td>${inventory.blNo}</td></tr>
                 <tr> <td>订单号</td>  <td>${inventory.bookingNo}</td></tr>-->
                    <tr>
                        <td>海关放行标识</td>
                        <td>${inventory.flag!}</td>
                    </tr>
                    <tr>
                        <td>VGM标识</td>
                        <td>${inventory.vgmChk!}</td>
                    </tr>
                    <tr>
                        <td>国检放行标识</td>
                        <td>${inventory.ciqChk!}</td>
                    </tr>


                </table>
            </#list>
        <#else>
            <#if container??>
                <div class="alert alert-success">
                    <h4>提示！！！</h4>
                    柜号：${container!}不在场！或者您输入的柜号不存在！
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