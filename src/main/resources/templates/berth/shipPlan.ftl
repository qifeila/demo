<!DOCTYPE html>
<html lang="en">
<head>
 <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
     <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <meta charset="UTF-8">
    <title>班轮集港计划</title>
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


</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">


            <h3  class="text-info" style="text-align: center">
                未来十天船期查询
            </h3>

            <#if status == "1">
              <ul class="nav nav-tabs">
                            <li class= "active" >
                                <a href="getBerthPlan">GOCT</a>

                            <li >
                                <a href="getBerthPlan2" >NCT</a>
                            </li>
                        </ul>
             <#else>
             <ul class="nav nav-tabs">
                            <li >
                                <a href="getBerthPlan">GOCT</a>

                            <li   class= "active">
                                <a href="getBerthPlan2" >NCT</a>
                            </li>
                        </ul>
             </#if>




        <#if berthList??>
            <#list berthList  as berth>
                <h4  class="text-info">
                ${berth_index+1}.${berth.vslName}(${berth.oldVslName!})
                </h4>
                <table class="table table-striped">

                    <tr> <td>船代码</td>  <td>${berth.vslCd!}</td></tr>
                    <tr> <td>进/出口航次</td>  <td>${berth.inVoy!}/${berth.outVoy!}</td></tr>
                    <tr> <td>ETA</td>  <td>${berth.eta!}</td></tr>
                    <tr> <td>ETB</td>  <td>${berth.etb!}</td></tr>
                    <tr> <td>闸口截箱时间</td>  <td>${berth.yardClose!}</td></tr>
                    <tr> <td>IMO</td>  <td>${berth.inmarsat!}</td></tr>
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