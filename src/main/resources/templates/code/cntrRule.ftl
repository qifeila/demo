<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>基本代码查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                用箱规则查询
            </h3>
            <form role="form" action="/getCntrRule" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">英文代码箱属</label><input type="text" class="form-control" id="ptnrCode" name="ptnrCode" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (codeList??) && (codeList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4  class="text-info">

                箱属：${ptnrCode!}
            </h4>
        <table class="table table-striped">
            <thead>
            <tr><th>代码</th>
                <th>定义 </th>
                <th>备注</th>
                <th>港区</th>
            </tr>
            </thead>
        <tbody>
            <#list codeList  as code>
            <tr>
                <td>${code.goctCode!}</td>
                <td>${code.define!}</td>
                <td>${code.remark!}</td>
                <td>${code.terminal!}</td>
            </tr>
            </#list>
        </tbody>
        </table>
        <#else>
            <#if ptnrCode??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>无查询结果！！！</h4><br>
                    搜索内容为<strong>${ptnrCode!}</strong>,请检查后重新输入！<br>
                </div>
            <#else>
            </#if>

        </#if>
        </div>
    </div>
</div>
</body>
</html>