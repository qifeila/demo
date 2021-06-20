<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="mui/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
<#--<link rel="stylesheet" href="mui/css/mui.min.css">-->
<#--为解决mui的样式冲突提取的css-->
    <link rel="stylesheet" href="mui/css/custom.css">
<#-- <script type="text/javascript" src="../static/js/check.js"></script>-->
    <link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<#--<style>
    td {text-align:center}
</style>-->
    <meta charset="UTF-8">
    <title>外拖超长指令</title>
</head>
<body>
<div class="container">
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <h3 class="text-info" style="text-align: center">
                    前30条外拖超长作业指令
                </h3>
            <#if (truckList??)&&(truckList ?size >0)>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>拖车号</th>
                        <th>指令类型</th>
                        <th>重吉</th>
                        <th>场位</th>
                        <th>进闸时间</th>
                        <th>时长(分)</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list truckList  as truck>
                        <tr>
                            <td>${truck.headNo!}</td>
                            <td>${truck.gjobType!}</td>
                            <td>${truck.fe!}</td>
                            <td>${truck.position!}</td>
                            <td>${truck.gpassDate!}</td>
                            <td>${truck.difftime!}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <div class="alert alert-success">
                    <h4>提示！！！</h4>
                    暂无记录！
                </div>
            </#if>
            <#--   </tbody>-->
            </div>
        </div>
    </div>
</div>
</body>
<script>
    mui.init({
        pullRefresh: {
            container: "#pullrefresh",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
            down: {
                auto: false,
                style: 'circle',//必选，下拉刷新样式，目前支持原生5+ ‘circle’ 样式
                callback: pulldownRefresh //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
            }
        }
    });

    function pulldownRefresh() {
        window.location.reload();
    }

</script>
</html>