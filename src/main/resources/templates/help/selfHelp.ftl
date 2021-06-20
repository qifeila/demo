<!DOCTYPE html>
<html lang="en">
<head>
    <link href="" rel="stylesheet">

    <script src="js/jquery-3.4.1.min.js"></script>

    <script src="bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="js/bootstrap3-typeahead.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>帮助信息</title>
    <style>


    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                帮助信息查询
            </h3>
            <form role="form" id="form1">
                <div class="form-group">
                    <br><br><input type="text" class="form-control" data-provide="typeahead" placeholder="请输入关键字" autocomplete="off" id="input">
                </div>
                <button class="btn btn-default " type="button" id="submitButton" onclick="ajaxSubmit()"
                        style="width: 120px">提 交
                </button>
            </form>
        </div>
    </div>
</div>
<div id="result" style="display: none">
    <div class="alert alert-dismissable alert-warning">
        <h4>无查询结果！！！</h4><br>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="accordion" id="accordion-485044">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-485044" href="#accordion-element-902714" id="tittle"></a>
                    </div>
                    <div id="accordion-element-902714" class="accordion-body collapse in">
                        <div class="accordion-inner" id="text">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        var objects = {};
        $("#input").typeahead(
                {
                    minLength: 2,//最少输入字符串
                    delay:500,//延迟0.5sec后查询
                    source: function (query, process) { //query是输入框输入的文本内容, process是一个回调函数
                        $.post("/getHelpByKey", {keyWord: query}, function (data) {
                            if (data == undefined || data.length == 0) {
                                //$("#result").show();
                            }
                            ;
                            var results = [];
                            for (var i = 0; i < data.length; i++) {
                                objects[data[i].question] = data[i].solution;
                                results.push(data[i].question);
                            }
                            process(results);
                        });
                    },
                    afterSelect: function (item) {       //选择项之后的事件，item是当前选中的选项
                        //$("#result").hide();
                        $("#tittle").html(item);//标题
                        $("#text").html(objects[item]); //内容
                    },
                });
    });

    function ajaxSubmit(){
        $.post("/getHelpByKey", {keyWord: query}, function (data) {
            if (data == undefined || data.length == 0) {
                $("#result").show();
                return;
            };
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str +="<div class=\"accordion-group\">\n" +
                        "                    <div class=\"accordion-heading\">\n" +
                        "                        <a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion-485044\" href=\"#accordion-element-902714\" id=\"tittle\">" +
                        data[i].question +
                        "</a>\n" +
                        "                    </div>\n" +
                        "                    <div id=\"accordion-element-902714\" class=\"accordion-body collapse in\">\n" +
                        "                        <div class=\"accordion-inner\" id=\"text\">\n" +
                        data[i].solution+
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>";
                objects[data[i].question] = data[i].solution;
                results.push(data[i].question);
            }
            var str = "";
            $("#accordion-485044").html("")
        });
    }
</script>
</html>