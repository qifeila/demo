<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>计件查询</title>
    <style>
        .submit_loading {
            position:fixed;
            width:100%;
            height:100%;
            top:0;
            left:0;
            background-color:#000;
            text-align:center;
            opacity:0.3;
        }
        .loading_show {
            margin-top:15%;
        }
        .loading_context {
            color:#fff;
        }

    </style>
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="loading" class="submit_loading" style="display:none">
                <div class="loading_show">
                    <img src="images/loading.gif">
                    <p class="loading_context">正在提交，请稍候。。。</p>
                </div>
            </div>
            <h3 class="text-info" style="text-align: center">
                计件查询
            </h3>
            <form role="form" id = "form1" action="driverQty" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">年月</label><input type="text" class="form-control" placeholder="${sMonth!}" value="${sMonth!}" id="month" name="month" />
                </div>
                <input type="hidden" name="weixinid" value=${weixinId!}>
                <button  class="btn btn-default " id = "submitButton" style="width: 120px">提  交</button>
                <#--<button type="submit" class="btn btn-default " id = "submitButton" style="width: 120px">提  交</button>-->
            </form>
        <#if (driverList1??)||(driverList2??)||(driverList3??)||(driverList4??)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4 class="text-info" >该月份所有工号总计<u>${sumQty!}</u>。数据仅供参考，请以统计组提供数据为准，谢谢！ </h4>
        <#else >
            <#if month??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>无查询结果！！！</h4><br>
                    搜索内容为<strong>${month!}</strong>,请检查后重新输入！<br>
                </div>
            <#else>
            </#if>
        </#if>


        <#if (driverList1??) && (driverList1?size > 0)>

        <h4 class="text-info" >姓名：${driverName}  工号：${catosId} 总计：${catosIdSumQty!}</h4>
            <table class="table table-striped">
                <thead>
                <tr><th>日期</th>
                    <th>10'</th>
                    <th>20'</th>
                    <th>40'</th>
                    <th>45'</th>
                    <th>特殊</th>
                    <th>危品</th>
                    <th>总计</th>
                </tr>
                </thead>
                <tbody>
                    <#list driverList1  as driver>
                    <tr>
                        <td>${driver.day}</td>
                        <td>${driver.cntr10!}</td>
                        <td>${driver.cntr20!}</td>
                        <td>${driver.cntr40!}</td>
                        <td>${driver.cntr45!}</td>
                        <td>${driver.cntrSpe!}</td>
                        <td>${driver.dangerCntr!}</td>
                        <td>${driver.qty!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>

        </#if>



        <#if (driverList2??) && (driverList2?size > 0)>
            <h4 class="text-info" >姓名：${driverName}  工号：${catosId2} 总计：${catosId2SumQty!}</h4>
            <table class="table table-striped">
                <thead>
                <tr><th>日期</th>
                    <th>10'</th>
                    <th>20'</th>
                    <th>40'</th>
                    <th>45'</th>
                    <th>特殊</th>
                    <th>危品</th>
                    <th>总计</th>
                </tr>
                </thead>
                <tbody>
                    <#list driverList2  as driver>
                    <tr>
                        <td>${driver.day}</td>
                        <td>${driver.cntr10!}</td>
                        <td>${driver.cntr20!}</td>
                        <td>${driver.cntr40!}</td>
                        <td>${driver.cntr45!}</td>
                        <td>${driver.cntrSpe!}</td>
                        <td>${driver.dangerCntr!}</td>
                        <td>${driver.qty!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
        </#if>
        <#if (driverList3??) && (driverList3?size > 0)>
            <h4 class="text-info" >姓名：${driverName}  工号：${catosId3} 总计：${catosId3SumQty!}</h4>
            <table class="table table-striped">
                <thead>
                <tr><th>日期</th>
                    <th>10'</th>
                    <th>20'</th>
                    <th>40'</th>
                    <th>45'</th>
                    <th>特殊</th>
                    <th>危品</th>
                    <th>总计</th>
                </tr>
                </thead>
                <tbody>
                    <#list driverList3  as driver>
                    <tr>
                        <td>${driver.day}</td>
                        <td>${driver.cntr10!}</td>
                        <td>${driver.cntr20!}</td>
                        <td>${driver.cntr40!}</td>
                        <td>${driver.cntr45!}</td>
                        <td>${driver.cntrSpe!}</td>
                        <td>${driver.dangerCntr!}</td>
                        <td>${driver.qty!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
        </#if>
        <#if (driverList4??) && (driverList4?size > 0)>
            <h4 class="text-info" >姓名：${driverName}  工号：${catosId4} 总计：${catosId4SumQty!}</h4>
            <table class="table table-striped">
                <thead>
                <tr><th>日期</th>
                    <th>10'</th>
                    <th>20'</th>
                    <th>40'</th>
                    <th>45'</th>
                    <th>特殊</th>
                    <th>危品</th>
                    <th>总计</th>
                </tr>
                </thead>
                <tbody>
                    <#list driverList4 as driver>
                    <tr>
                        <td>${driver.day}</td>
                        <td>${driver.cntr10!}</td>
                        <td>${driver.cntr20!}</td>
                        <td>${driver.cntr40!}</td>
                        <td>${driver.cntr45!}</td>
                        <td>${driver.cntrSpe!}</td>
                        <td>${driver.dangerCntr!}</td>
                        <td>${driver.qty!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
        </#if>
        </div>
    </div>
</div>
</body>
<script>
    $('#submitButton').click(function(){
       // $('submitButton').attr("disabled","true");
      //  $('submitButton').css({'background':'#8E8E8E'});
        $("#loading").show();
        $("#form1").submit();
    })

</script>
</html>