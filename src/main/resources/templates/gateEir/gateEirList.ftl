<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/gobal.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>外修箱查询</title>
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

        .alert {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            min-width: 300px;
            max-width: 600px;
            transform: translate(-50%, -50%);
            z-index: 99999;
            text-align: center;
            padding: 15px;
            border-radius: 3px;
        }

        .alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        .alert-info {
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        .alert-warning {
            color: #8a6d3b;
            background-color: #fcf8e3;
            border-color: #faebcc;
        }

        .alert-danger {
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
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
                外修箱查询
            </h3>
            <div style="float:right">
                当前用户:${user.name!},${user.account!}
            </div>
            <br>
            <form role="form" id="form1" action="/gateEirQuery" method="post">
            <#if (webFlag?? && webFlag == "1")>
                <div style="float:right;padding-bottom:20px">
                    <input type="hidden" name="webFlag" value="1">
                    <button type="button" class="btn btn-default">
                        <a href="/loginUser"><span class="glyphicon glyphicon-home"></span>首页</a>
                    </button>
                </div>
            </#if>
                <input type="hidden" value="${phone}" name="phone"/>
                <div class="form-group">
                    <label for="cntrNo">箱号</label><input type="text" class="form-control"
                                                         value="${cntrNo!}" placeholder="请输入箱号" id="cntrNo"
                                                         name="cntrNo"/>
                </div>
                <button type="submit" id="submitButton" class="btn btn-default " style="width: 120px">查询</button>
            </form>

        <#if (gateEir??)>
            <form class="form-horizontal" style="padding-top:20px" role="form">
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="ds_host">箱号:</label>
                    <div class="col-sm-5">
                        <input class="form-control" value="${gateEir.cntr_no}" readonly type="text"/>
                    </div>
                    <label class="col-sm-1 control-label" for="ds_name">箱属:</label>
                    <div class="col-sm-5">
                        <input class="form-control" value="${gateEir.ptnr_code!}" readonly type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="ds_host">验柜状态:</label>
                    <div class="col-sm-5">
                        <input class="form-control"
                               value="<#if (gateEir.cntr_state=="Y")>好柜<#elseif (gateEir.cntr_state=="N")>烂柜</#if>"
                               readonly type="text"/>
                    </div>
                    <label class="col-sm-1 control-label" for="ds_name">箱型:</label>
                    <div class="col-sm-5">
                        <input class="form-control" value="${gateEir.sztp!}"
                               readonly type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="ds_name">出闸时间:</label>
                    <div class="col-sm-5">
                        <input class="form-control" value="${(gateEir.update_time?string("yyyy/MM/dd HH:mm"))!}"
                               readonly type="text"/>
                    </div>
                    <label class="col-sm-1 control-label" for="ds_name">验箱工号:</label>
                    <div class="col-sm-5">
                        <input class="form-control" value="${(gateEir.gateCheckList[0].account)!}"
                               readonly type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="ds_host">备注:</label>
                    <div class="col-sm-11">
                        <textarea class="form-control" readonly>${gateEir.remark!}</textarea>
                    </div>
                </div>
            </form>
        </#if>
        <#if tips??>
            <br>
            <h3>
                <strong class="text-danger">${tips!}</strong>
            </h3>
        </#if>
        <#if (coparn??)>
            <form class="panel panel-primary" id="form2">
                <div class="panel-heading">
                    <h3 class="panel-title">查验状态</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="repairState"><span class="text-danger">*</span>箱是否完好：&nbsp;&nbsp;
                            <label class="radio-inline">
                                <input type="radio" name="repairState"
                                       id="repairState1" <#if (coparn.repair_state??&&coparn.repair_state == 'Y')> checked </#if> value="Y"> 是
                            </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="radio-inline">
                                <input type="radio" name="repairState"
                                       id="repairState2" <#if (coparn.repair_state??&&coparn.repair_state == 'N')> checked </#if> value="N"> 否
                            </label>
                    </div>
                    <div class="form-group">
                        <label for="repairRemark">备注：</label><textarea rows="2" value="${coparn.repair_remark!}"
                                                                       class="form-control" id="repairRemark"
                                                                       name="repairRemark">${coparn.repair_remark!}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="repairer">更新人:&nbsp; ${coparn.repairer!"--"}
                    </div>
                    <div class="form-group">
                        <label for="repair_time">更新时间:&nbsp; ${(coparn.repair_time?string("yyyy/MM/dd HH:mm"))!"--"}
                    </div>
                    <div style="text-align:center">
                        <button type="button" class="btn btn-primary btn-lg" onclick="ajaxSubmit()"
                                style="width: 120px">提交
                        </button>
                    </div>
                </div>
            </form>
        </#if>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(function () {
        //为ajax绑定loading_bottom  
        $(document).ajaxStart(function () {
            $("#loading").show();//在ajax请求开始的时候启用loading  
        }).ajaxStop(function () {
            $('#loading').hide(0);//在ajax请求结束后隐藏loading  
        });

        $('#submitButton').click(function () {
            if(!checkCntr($("#cntrNo").val())) {
                alert("箱编号不正确！")
                return false;
            }
            $("#loading").show();
            $("#form1").submit();
        })
    });

    function ajaxSubmit() {
        if (form2.repairState.value == "") {
            alert("请选择箱状态！")
            return false;
        }
        if(!checkCntr("${cntrNo!}")){
            alert("箱编号不正确！")
            return false;
        }
        $.ajax({
                    url: "/coparnUpdate",
                    data: {
                        "phone": "${phone!}",
                        "cntrNo": "${cntrNo!}",
                        "repairState": form2.repairState.value,
                        "repairRemark": $("#repairRemark").val()
                    },
                    type: "post",
                    dataTye: "json",
                    success: function (data) {
                        if (data.code == "1") {
                            success_prompt("提交成功", 1000);
                        } else {
                            fail_prompt(data.msg, 2000);
                        }
                    }
                }
        )
    }

    // 成功提示
    var success_prompt = function (message, time) {
        time = (time === undefined) ? 1200 : time;
        $('<div>')
                .appendTo('body')
                .addClass('alert alert-success')
                .html(message)
                .show()
                .delay(time)
                .fadeOut(function () {
                    $("#form1").submit();
                });
    };

    // 失败提示
    var fail_prompt = function (message, time) {
        time = (time === undefined) ? 1200 : time;
        $('<div>')
                .appendTo('body')
                .addClass('alert alert-danger')
                .html(message)
                .show()
                .delay(time)
                .fadeOut();
    };



</script>