<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<#-- <script type="text/javascript" src="../static/js/check.js"></script>-->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>当前在港班轮</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
              <h3  class="text-info" style="text-align: center">
                在港班轮船期查询
            </h3>
            <#if status == "1">
                          <ul class="nav nav-tabs">
                                        <li class= "active" >
                                            <a href="getShipInPort">GOCT</a>

                                        <li >
                                            <a href="getShipInPort2" >NCT</a>
                                        </li>
                                    </ul>
                         <#else>
                         <ul class="nav nav-tabs">
                                        <li >
                                            <a href="getShipInPort">GOCT</a>

                                        <li   class= "active">
                                            <a href="getShipInPort2" >NCT</a>
                                        </li>
                                    </ul>
                         </#if>

             <#if berthList??>
                <#list berthList  as berth>
                <h4  class="text-info">
                ${berth_index+1}.${berth.vslName}(${berth.oldVslName})
                </h4>
                <table class="table table-striped">
                                    <tr> <td>状态.</td>  <td>${berth.status!}</td></tr>
                                    <tr> <td>船代码</td>  <td>${berth.vslCd!}</td></tr>
                                    <tr> <td>进/出口航次</td>  <td>${berth.inVoy!}/${berth.outVoy!}</td></tr>
                                    <tr> <td>ETD</td>  <td>${berth.etd!}</td></tr>
                                    <tr> <td>ATB</td>  <td>${berth.atb!}</td></tr>
                                </table>
                </#list>
        <#else>
        </#if>


                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>