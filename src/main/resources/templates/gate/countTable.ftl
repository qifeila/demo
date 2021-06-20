<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js" charset="utf-8"></script>

    <meta charset="UTF-8">

    <title>统计数据</title>
</head>
<body>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->

<div>

            <br><br>
            <h4 class="text-info">每日报表</h4>
            <br>
            <div >
                <table class="layui-table" lay-data="{ url:'/getCountTableData',size:'sm'}">
                    <thead>
                    <tr>
                        <th lay-data="{align:'center',field:'ymd',width:100}" rowspan="2">DAY</th>
                        <th lay-data="{align:'center',field:'headNum',width:80}" rowspan="2">车次</th>
                        <th lay-data="{align:'center'}" colspan="2">进闸</th>
                        <th lay-data="{align:'center'}" colspan="2">出闸</th>
                        <th lay-data="{align:'center',field:'teu',width:100}" rowspan="2">总计</th>
                    </tr>
                    <tr>
                        <th lay-data="{align:'center',field:'gfE',width:100}">E</th>
                        <th lay-data="{align:'center',field:'gfF',width:100}">F</th>
                        <th lay-data="{align:'center',field:'goE',width:100}">E</th>
                        <th lay-data="{align:'center',field:'goF',width:100}">F</th>
                    </tr>
                    </thead>
                </table>

            </div>
        </div>
    </div>

    <script>
        layui.use('table', function(){
            var table = layui.table;

        });
    </script>
</div>

</body>
</html>