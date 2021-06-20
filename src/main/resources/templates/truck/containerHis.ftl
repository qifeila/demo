<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta charset="UTF-8">
    <title>集装箱进出闸记录</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
        <h3 class="text-info" style="text-align: center">
            三个月内集装箱进出闸记录查询
        </h3>
        <form role="form" action="/getContainerHis" method="post">

            <div class="form-group">
                <label for="exampleInputPassword1">柜号</label><input type="text" class="form-control" id="container" name="container" />
            </div>
            <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
        </form>

             <h4 class="text-info"   style="text-align: center">
              查询结果
            </h4>

            <#if container??>
                        <h4  class="text-info">
                            柜号：${container}
                        </h4>
                        <table class="table table-striped">
                                                                <thead>
                                                                	<tr>
                                                                	    <th>#</th>
                                                                		<th>车牌</th>
                                                                        <th>进闸日期</th>
                                                                		<th>出闸日期</th>
                                                                	</tr>
                                                                </thead>
            <#else>
            </#if>



                   <#if truckList??>
                        <#list truckList  as truck>



                                <tr>
                                   <td>${truck.truckId!}</td>
                                   <td>${truck.headNo!}</td>

                                  <td>${truck.gpassDate!}</td>
                                  <td>${truck.outDate!}</td>
                                 </tr>




                        </#list>

                    <#else>
                    </#if>
                    </table>
        </div>
    </div>
</div>
</body>
</html>