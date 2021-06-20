<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>NCT计件查询</title>
    <style>
        .submit_loading {
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: #000;
            text-align: center;
            opacity: 0.3;
        }

        .loading_show {
            margin-top: 15%;
        }

        .loading_context {
            color: #fff;
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
                NCT计件查询
            </h3>
            <form role="form" id="form1">
            <#if !workerCode??>
            <div class="form-group">
                <label for="workerCode">工号</label><input type="text" name="workerCode" id="workerCode" class="form-control"
                                                                    placeholder="请输入工号" value="${workerCode!}">
            </div>
            <#else>
                <input type="hidden" name="workerCode" id="workerCode" value="${workerCode!}">
            </#if>
                <div class="form-group">
                    <label for="month">年月</label><input type="text" class="form-control"
                                                                        placeholder="${month!}" value="${month!}"
                                                                        id="month" name="month"/>
                </div>
                <button class="btn btn-default" type="button" style="width: 120px" onclick="ajaxSubmit()">查询</button>
            </form>
            <div id="result">
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        //为ajax绑定loading_bottom  
        $(document).ajaxStart(function () {
            $("#loading").show();//在ajax请求开始的时候启用loading  
        }).ajaxStop(function () {
            $('#loading').hide();//在ajax请求结束后隐藏loading  
        });
    });

    function ajaxSubmit() {
        var workerCode = $("#workerCode").val();
        var month = $("#month").val();
        if (workerCode == "" || month == "") {
            alert("录入工号和日期不能为空")
            return false;
        }
        var str = "";
        $.ajax({
                    url: "/nctDriverQuery",
                    data: {
                        "workerCode": workerCode,
                        "month": month
                    },
                    type: "post",
                    dataTye: "json",
                    success: function (data) {
                        if (data.code != 1) {
                            alert(data.msg);
                        } else {
                            var result = data.data;
                            if (result.lstDriver != null && result.lstDriver.length > 0) {
                                str = "<h3 class=\"text-info\" style=\"text-align: center\">\n" +
                                        "                查询结果\n" +
                                        "            </h3>\n" +
                                        "            <h4 class=\"text-info\">数据仅供参考，请以统计组提供数据为准，谢谢！ </h4>\n" +
                                        "            <h4 class=\"text-info\"> 工号：" + result.workerCode + " &nbsp;&nbsp;总计：" + result.sumQty + "</h4>\n" +
                                        "            <table class=\"table table-striped\">\n" +
                                        "                <thead>\n" +
                                        "                <tr>\n" +
                                        "                    <th>日期</th>\n" +
                                        "                    <th>20'空</th>\n" +
                                        "                    <th>20'重</th>\n" +
                                        "                    <th>40'空</th>\n" +
                                        "                    <th>40'重</th>\n" +
                                        "                    <th>总计</th>\n" +
                                        "                </tr>\n" +
                                        "                </thead>\n" +
                                        "                <tbody>\n";
                                for (var i = 0; i<result.lstDriver.length; i++ ){
                                    var driver = result.lstDriver[i];
                                    str += "<tr>" +
                                            "<td>"+ driver.day +"</td>\n" +
                                            "<td>"+ driver.cntr20 +"</td>\n" +
                                            "<td>"+ driver.cntr20F +"</td>\n" +
                                            "<td>"+ driver.cntr40 +"</td>\n" +
                                            "<td>"+ driver.cntr40F +"</td>\n" +
                                            "<td>"+ driver.qtyNct +"</td>\n" +
                                            "</tr>";
                                }
                                str += "</tbody>\n" +
                                        "</table>";
                            }else {
                                str += "<br>\n" +
                                        "            <div class=\"alert alert-dismissable alert-success\">\n" +
                                        "                <h4>无查询结果！！！</h4>\n" +
                                        "            </div>";
                            }
                            $("#result").html(str);
                        }
                    }
                }
        )
    }
</script>
</html>