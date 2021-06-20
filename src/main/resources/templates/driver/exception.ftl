<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>计件查询</title>
    <style>
        .submit_loading {
            position:fixed;
            width:100%;
            height:100%;
            top:0;
            left:0;
            background-color:#000;
            text-align:center;
            opacity:0.3;
        }
        .loading_show {
            margin-top:15%;
        }
        .loading_context {
            color:#fff;
        }

    </style>
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <h3 class="text-info" style="text-align: center">
                出错啦！
            </h3>

            <#if tips??>
                <br>
                <div class="alert alert-dismissable alert-success">
                    <h4>ERROR！！！</h4><br>
                    <strong>${tips!}</strong><br>
                </div>
            <#else>
            </#if>



        </div>
    </div>
</div>
</body>

</html>