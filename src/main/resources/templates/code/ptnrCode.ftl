<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>

    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>基本代码查询</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                箱属代码查询
            </h3>
            <form role="form" action="/getPtnrCode" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">箱属中文名</label><input type="text" class="form-control" id="engSnm" name="engSnm" />
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提  交</button>
            </form>

        <#if (codeList??) && (codeList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <table class="table table-striped">
                <thead>
                <tr><th>#</th>
                    <th>港区 </th>
                    <th>箱属</th>
                    <th>中文名</th>
                </tr>
                </thead>
                <tbody>
                    <#list codeList  as code>
                    <tr>
                        <td>${code?index+1}</td>
                        <td>${code.terminal!}</td>
                        <td>${code.ptnrCode!}</td>
                        <td>${code.engSnm!}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>

        <#else>
        <#if engSnm??>
        <br>
            <div class="alert alert-dismissable alert-success">
                <h4>无查询结果！！！</h4><br>
                搜索内容为<strong>${engSnm!}</strong>,请检查后重新输入！<br>
            </div>
        <#else>
        </#if>

        </#if>
        </div>
    </div>
</div>
</body>
</html>