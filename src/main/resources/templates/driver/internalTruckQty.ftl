<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>拖车计件查询</title>
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
                拖车计件查询
            </h3>
            <form role="form" id="form1">
                <div class="form-group">
                    <label for="exampleInputPassword1">年月</label><input type="text" class="form-control"
                                                                        placeholder="日期格式如：${sMonth!}" value="${sMonth!}" id="month"
                                                                        name="month"/>
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">工号</label>
                    <input type="text" class="form-control" placeholder="请输入工号" id="workId" name="workId" />
                </div>
                <button class="btn btn-default " type="button" id="submitButton" onclick="ajaxSubmit()"
                        style="width: 120px">提 交
                </button>
            <#--<button type="submit" class="btn btn-default " id = "submitButton" style="width: 120px">提  交</button>-->
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
            $('#loading').hide(0);//在ajax请求结束后隐藏loading  
        });
    });

    function ajaxSubmit() {
        var workId = $("#workId").val();
        var month = $("#month").val();
        if (workId == "" || month == "") {
            alert("入工号和日期不能为空")
            return false;
        }

        var str = "";
        $.ajax({
                    url: "/getInternalTruckQty",
                    data: {
                        "workId": workId,
                        "month": month
                    },
                    type: "post",
                    dataTye: "json",
                    success: function (data) {
                        if (data.code != 1) {
                            alert(data.msg);
                        } else {
                            var result = data.data;
                            if (result.lstDriver == null && result.lstDriver1 == null) {
                                $("#result").html("<br>\n" +
                                        "                    <div class=\"alert alert-dismissable alert-success\">\n" +
                                        "                        <h4>无查询结果！！！</h4><br>\n" +
                                        "                    </div>");
                                return false;

                            }
                            if (result.lstDriver != null && result.lstDriver.length > 0) {
                                str += "<h3 class=\"text-info\" style=\"text-align: center\">\n" +
                                        "                    每日作业量\n" +
                                        "&nbsp;&nbsp;总计："+result.iSumQty+
                                        "                </h3>\n" +
                                        "<table class=\"table table-striped\">\n" +
                                        "                    <thead>\n" +
                                        "                    <tr>\n" +
                                        "                    <th>日期</th>\n" +
                                        "                    <th>20'</th>\n" +
                                        "            <th>40'</th>\n" +
                                        "            <th>总计</th>\n" +
                                        "            </tr>\n" +
                                        "            </thead>\n" +
                                        "            <tbody>\n";
                                var lstDriver = result.lstDriver;
                                for (var i = 0; i < lstDriver.length; i++) {
                                    str += "<tr >\n" +
                                            "                <td>" + lstDriver[i].day + "</td>\n" +
                                            "                <td>" + lstDriver[i].cntr20 + "</td>\n" +
                                            "                <td>" + lstDriver[i].cntr40 + "</td>\n" +
                                            "                <td>" + lstDriver[i].qty + "</td>\n" +
                                            "                </tr>\n";
                                }
                                str += "            </tbody>\n" +
                                        "            </table>";
                            }
                            if (result.lstDriver1 != null && result.lstDriver1.length > 0) {
                                str += "<h3 class=\"text-info\" style=\"text-align: center\">\n" +
                                        "                    卸船过机量\n" +
                                        "&nbsp;&nbsp;总计："+result.iSumQty1+
                                        "                </h3>\n" +
                                        "<table class=\"table table-striped\">\n" +
                                        "                    <thead>\n" +
                                        "                    <tr>\n" +
                                        "                    <th>日期</th>\n" +
                                        "                    <th>20'</th>\n" +
                                        "            <th>40'</th>\n" +
                                        "            <th>总计</th>\n" +
                                        "            </tr>\n" +
                                        "            </thead>\n" +
                                        "            <tbody>\n";
                                var lstDriver1 = result.lstDriver1;
                                for (var i = 0; i < lstDriver1.length; i++) {
                                    str +=
                                            "<tr >\n" +
                                            "                <td>" + lstDriver1[i].day + "</td>\n" +
                                            "                <td>" + lstDriver1[i].cntr20 + "</td>\n" +
                                            "                <td>" + lstDriver1[i].cntr40 + "</td>\n" +
                                            "                <td>" + lstDriver1[i].qty + "</td>\n" +
                                            "                </tr>\n";
                                }
                                str += "            </tbody>\n" +
                                        "            </table>";
                                ;
                            }
                            str += "<h4 class=\"text-info\">以上数据仅供参考，请以统计组提供数据为准，谢谢！ </h4>";
                            $("#result").html(str);
                        }
                    }
                }
        )
    }
</script>
</html>