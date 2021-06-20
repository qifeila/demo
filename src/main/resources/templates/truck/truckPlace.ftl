<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>当前拖车作业查询</title>
</head>
<body>
<br><br>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <div class="alert alert-dismissable alert-success">


                <h4>
                    各位司机，辛苦了，欢迎来到南沙海港码头。 进入码头后，请注意以下几点：</h4>
                <strong>第一，</strong>完成还柜后，提柜指令才会开始分派龙门吊进行作业。<br>
                <strong>第二，</strong>请把车速控制在30km/h以内，并且南沙港区为非吸烟区，如有违规，发现后将会重罚！<br>
                <strong>第三，</strong>请勿乱扔垃圾，垃圾请随身带走;不得在码头内丢弃封条，否则您也可能成为扎胎的受害者！<br>
            </div>
        </div>

    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                当前拖车作业查询
            </h3>
            <form role="form" action="/getTruckPlace" method="post">

                <div class="form-group">
                    <label for="exampleInputPassword1">车牌号</label><input type="text" value="${headNo!}"
                                                                         class="form-control" id="headNo"
                                                                         name="headNo"/>
                </div>
                <button type="submit" class="btn btn-default " style="width: 120px">提 交</button>
            </form>
        <#if truckList?? && (truckList?size > 0)>
            <h3 class="text-info" style="text-align: center">
                查询结果
            </h3>
            <h4 class="text-info">
                车牌：${headNo}当前作业信息
            </h4>
            <#list truckList  as truck>
                <#if truck.unNo??>
                    <!-- 按钮触发模态框 -->
                    <button class="btn btn-warning" style="float:right" data-toggle="modal"
                            data-target="#myModal_${truck_index}">
                        危险品信息 <span><img height="25px" src="images/danger2.png"></span>
                    </button>
                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="myModal_${truck_index}" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        危险品详细信息
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">类别</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" readonly
                                                       value="${truck.category!}"/>
                                            </div>
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">UNNO</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" readonly
                                                       value="${truck.unNo!}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">危险品名</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" readonly
                                                       value="${truck.dangerName!}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">重量(t)</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" readonly
                                                       value="${truck.dangerWeight!}"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary"
                                            data-dismiss="modal">关闭
                                    </button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->

                </#if>
                <table class="table table-striped">
                    <tr>
                        <td>集装箱号</td>
                        <td>${truck.cntrNo!}</td>
                    </tr>
                    <tr>
                        <td>箱型</td>
                        <td>${truck.priCode!}</td>
                    </tr>
                    <tr>
                        <td>作业类型</td>
                        <td>${truck.gjobType!}</td>
                    </tr>
                    <tr>
                        <td>场位位置</td>
                        <td>${truck.position!}</td>
                    </tr>
                    <tr>
                        <td>进闸日期</td>
                        <td>${truck.gpassDate!}</td>
                    </tr>
                    <tr>
                        <td>完成时间</td>
                        <td>${truck.yardTime!}</td>
                    </tr>
                    <tr>
                        <td>订舱号</td>
                        <td>${truck.bookingNo!}</td>
                    </tr>
                    <tr>
                        <td>提单号</td>
                        <td>${truck.blNo!}</td>
                    </tr>
                    <tr>
                        <td>作业龙号</td>
                        <td>${truck.yequNo!}</td>
                    </tr>
                    <tr>
                        <td>待作业指令数</td>
                        <td></td>
                    </tr>

                <#--  <tr> <td>场位信息</td>   <td>${truck.gisPosition}</td></tr>-->

                </table>
                <br>
            </#list>
        <#elseif headNo??>
            <h3>无查询结果</h3>
        </#if>
        </div>
    </div>
</div>
</body>
</html>