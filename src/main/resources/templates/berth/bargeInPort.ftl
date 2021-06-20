<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>当前在港驳船</title>

    <style type="text/css">
        .res
        {
            color: Red;
        }
        .result{
            background: yellow;
        }
    </style>

    <script type="text/javascript">
        var oldKey = "";
        var index = -1;
        var pos = new Array();//用于记录每个关键词的位置，以方便跳转
        var oldCount = 0;//记录搜索到的所有关键词总数

        function previous(){
            index--;
            index = index < 0 ? oldCount - 1 : index;
            search();
        }
        function next(){
            index++;
            //index = index == oldCount ? 0 : index;
            if(index==oldCount){
                index = 0 ;
            }
            search();
        }

        function search() {
            $(".result").removeClass("res");//去除原本的res样式
            var key = $("#key").val(); //取key值
            if (!key) {
                console.log("key为空则退出");
                $(".result").each(function () {//恢复原始数据
                    $(this).replaceWith($(this).html());
                });
                oldKey = "";
                return; //key为空则退出
            }
            if (oldKey != key) {
                console.log("进入重置方法");
                //重置
                index = 0;
                $(".result").each(function () {
                    $(this).replaceWith($(this).html());
                });
                pos = new Array();
                var regExp = new RegExp(key+'(?!([^<]+)?>)', 'ig');//正则表达式匹配
                $("body").html($("body").html().replace(regExp, "<span id='result" + index + "' class='result'>" + key + "</span>")); // 高亮操作
                $("#key").val(key);
                oldKey = key;
                $(".result").each(function () {
                    pos.push($(this).offset().top);
                });
                oldCount = $(".result").length;
                console.log("oldCount值：",oldCount);
            }

            $(".result:eq(" + index + ")").addClass("res");//当前位置关键词改为红色字体

            $("body").scrollTop(pos[index]);//跳转到指定位置
        }
    </script>
    <script type="text/javascript">
        $("#getGoct").onclick(){
            $.ajax({
                type: 'post',
                url: "getBerthInPort",
                cache: false,
                //data: {"berthList":berthList},
                dataType: 'json',
                success: function(data){
                    $("#getBerthList").value= data.berthList;

                    });
                },
                error: function(){
                    return;
                }
            });

        }


    </script>


</head>
<body>
<i  class="fa fa-arrow-down" aria-hidden="true" ></i>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <h3  class="text-info" style="text-align: center">
                在港驳船船期查询
            </h3>
        <#if status == "1">
            <ul class="nav nav-tabs">
                <li class= "active" >
                    <a id="getGoct" href="getBargeInPort">GOCT</a>

                <li >
                    <a href="getBargeInPort2" >NCT</a>
                </li>
            </ul>
        <#else>
            <ul class="nav nav-tabs">
                <li >
                    <a href="getBargeInPort">GOCT</a>

                <li   class= "active">
                    <a href="getBargeInPort2" >NCT</a>
                </li>
            </ul>
        </#if>
            <input id="getBerthList" style="visibility:hidden;" />
        <#if berthList??>
            <#list berthList  as berth>
                <h4  class="text-info">
                ${berth_index+1}.${berth.vslName}(${berth.oldVslName})
                </h4>
                <table class="table table-striped">
                    <tr> <td>状态</td>  <td>${berth.status!}</td></tr>

                    <tr> <td>船代码</td>  <td>${berth.vslCd!}</td></tr>
                 <#--   <tr> <td>船名</td>  <td>${berth.vslName}</td></tr>
                    <tr> <td>中文船名</td>  <td>${berth.oldVslName}</td></tr>
                    <tr> <td>船属</td>  <td>${berth.ptnrCode}</td></tr>
                    <tr> <td>进口航线</td>  <td>${berth.inLane}</td></tr>
                    <tr> <td>出口航线</td>  <td>${berth.outLane}</td></tr>
                    <tr> <td>进口航次</td>  <td>${berth.inVoy}</td></tr>
                    <tr> <td>出口航次</td>  <td>${berth.outVoy}</td></tr>
                    <tr> <td>泊位</td>  <td>${berth.berthNo}</td></tr>
                    <tr> <td>开始桩位</td>  <td>${berth.fromBitt}</td></tr>
                    <tr> <td>结束桩位</td>  <td>${berth.fromBitt}</td></tr>
-->

                  <#--  <tr> <td>ETD</td>  <td>${berth.etd}</td></tr>
                    <tr> <td>ATC</td>  <td>${berth.atc}</td></tr>
                    <tr> <td>ATW</td>  <td>${berth.atw}</td></tr>-->
                    <tr> <td>实际靠泊时间</td>  <td>${berth.ata}</td></tr>
                    <#--<tr> <td>总量</td>  <td>${berth.total}</td></tr>
                    <tr> <td>剩余数量</td>  <td>${berth.remainQty}</td></tr>
                    <tr> <td>装柜数量</td>  <td>${berth.loQty}</td></tr>
                    <tr> <td>卸柜数量</td>  <td>${berth.dsQty}</td></tr>
                    <tr> <td>完成率</td>  <td>${berth.ratio}</td></tr>
                    <tr> <td>桥时</td>  <td>${berth.vqvh}</td></tr>-->


                </table>
            </#list>
        <#else>
        </#if>
        </div>

        <div style="position: fixed; right: 15px; bottom:0px;">
            <input  class="input-medium search-query" id="key" type="text" style="width: 160px;"/>
            <button class="btn btn-info btn-sm glyphicon glyphicon-menu-up" type="button" onclick="previous()" >

            </button>
            <button class="btn btn-info btn-sm  glyphicon glyphicon-menu-down"  type="button"  onclick="next()">

            </button>



        </div>
    </div>
</div>
</body>
</html>