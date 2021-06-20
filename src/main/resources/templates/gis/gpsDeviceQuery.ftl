<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>GPS在线设备查询</title>
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
                GPS在线设备查询
            </h3>
            <form role="form" id="form1">
                <div class="form-group">
                    <label for="exampleInputPassword1">设备名称</label>
                    <input type="text" class="form-control" id="deviceName" name="deviceName"/>
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">设备IP</label>
                    <input type="text" class="form-control" id="deviceIp" name="deviceIp"/>
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
        var deviceName = $("#deviceName").val().toUpperCase();
        var deviceIp = $("#deviceIp").val();
        if (deviceName == "" && deviceIp == "") {
            alert("请填写设备IP或名称查询")
            return false;
        }

        var str = "";
        $.ajax({
                    url: "/getGpsDeviceData",
                    data: {
                        "deviceIp": deviceIp,
                        "deviceName": deviceName
                    },
                    type: "post",
                    dataTye: "json",
                    success: function (data) {
                        if (data.code != 1) {
                            alert(data.msg);
                        } else {
                            var result = data.data;
                            if (result == null || result.length == 0) {
                                $("#result").html("<br>\n" +
                                        "                    <div class=\"alert alert-dismissable alert-success\">\n" +
                                        "                        <h3>无查询结果！！！</h3><br>\n" +
                                        "                    </div>");
                                return false;

                            } else {
                                str += "<h3 class=\"text-info\" style=\"text-align: center\">\n" +
                                        "                    GPS在线设备信息\n" +
                                        "                </h3>\n" +
                                        "<table class=\"table table-striped\">\n" +
                                        "                    <thead>\n" +
                                        "                    <tr>\n" +
                                        //"                    <th>设备类型</th>\n" +
                                        "                    <th>设备IP</th>\n" +
                                        "                    <th>设备名称</th>\n" +
                                        //"                    <th>经度</th>\n" +
                                        //"                    <th>纬度</th>\n" +
                                        //"                    <th>SPEED</th>\n" +
                                        "                    <th>在线时间</th>\n" +
                                        "                    <th>工号</th>\n" +
                                        "                    </tr>\n" +
                                        "                    </thead>\n" +
                                        "                    <tbody>\n";
                                for (var i = 0; i < result.length; i++) {
                                    str += "<tr >\n" +
                                            //       "                <td>" + result[i].deviceType + "</td>\n" +
                                            "                <td>" + result[i].deviceId + "</td>\n" +
                                            "                <td>" + result[i].deviceNam + "</td>\n" +
                                            //"                <td>" + result[i].longitude + "</td>\n" +
                                            //"                <td>" + result[i].latitude + "</td>\n" +
                                            //       "                <td>" + result[i].speed + "</td>\n" +
                                            "                <td>" + result[i].lstDateTim + "</td>\n" +
                                            "                <td>" + (result[i].trkDir==undefined?'':result[i].trkDir) + "</td>\n" +
                                            "                </tr>\n";
                                }
                                str += "            </tbody>\n" +
                                        "            </table>";
                            }
                            $("#result").html(str);
                        }
                    }
                }
        )
    }
</script>
</html>