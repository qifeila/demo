<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>NCT拖车计件查询</title>
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
                NCT拖车计件查询
            </h3>
            <form role="form" id = "form1" action="nctTruckQty" method="post">
                <div class="form-group">
                    <label for="exampleInputPassword1">年月</label><input type="text" class="form-control" placeholder="${month!}" value="${month!}" id="month" name="month" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">工号</label><input type="text" class="form-control" placeholder="请输入工号" value="${workId!}" id="workId" name="workId" />
                </div>
                <button  class="btn btn-default " id = "submitButton" style="width: 120px">提  交</button>

            </form>
        <#if (driverList??)&&(driverList?size>0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4 class="text-info" >数据仅供参考，请以统计组提供数据为准，谢谢！ </h4>
                <h4 class="text-info" > 工号：${workId} &nbsp;&nbsp;总计：${sumQty!}</h4>
                <table class="table table-striped">
                    <thead>
                    <tr><th>日期</th>
                        <th>20'空</th>
                        <th>20'重</th>
                        <th>40'空</th>
                        <th>40'重</th>
                        <th>总计</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list driverList  as driver>
                        <tr>
                            <td>${driver.day}</td>
                            <td>${driver.cntr20!}</td>
                            <td>${driver.cntr20F!}</td>
                            <td>${driver.cntr40!}</td>
                            <td>${driver.cntr40F!}</td>
                            <td>${driver.qtyNct!}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
        <#elseif workId??>
            <br>
            <div class="alert alert-dismissable alert-success">
                <h4>无查询结果！！！</h4>
            </div>
        </#if>


        </div>
    </div>
</div>
</body>
<script>
    $('#submitButton').click(function(){
        if($("#workId").val() ==null ||$("#workId").val() ==""){
            alert("请输入有效的工号！")
            return false;
        }
        $("#loading").show();
        $("#form1").submit();
    })

</script>
</html>