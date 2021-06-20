<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <script type="text/javascript" src="pdf.js"></script>


    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta charset="UTF-8">
    <title>pdf测试</title>

    <style>
        .parent {
            display: flex;
            width: 100%;
            min-height: 100vh;
            align-items: center;
            justify-content: center;
        }
        .select {
            width: 200px;
            height: 2rem;
            border: 1px solid rgba(48, 43, 43, 0.3);
            /*内容居中显示*/
            text-align: center;
            text-align-last: center;
            /* 去掉select自带的样式 */
            appearance: none;
            /* IE */
            -webkit-appearance: none;
            /* google */
            -moz-appearance: none;
            /* firefox */
            background: url("img/arrow_down.png") no-repeat scroll right center transparent;
            background-size: 10% 30%;
            /* 手势样式 */
            cursor: pointer;

        }
        /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
        select::-ms-expand {
            display: none;
        }
    </style>




</head>

<body>



<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-info" style="text-align: center">
                PDF目录
            </h3>
<#if (deptSet??) && (deptSet?size > 0)>
            <form class="form-search" action="/deptPdf" role="form"  method="post">
                <div class="form-group">
                    <label >类别:&nbsp;&nbsp;</label>
                    <select id="selector" name = "dept" class="select" >
    <#list deptSet  as dept>
                        <option value="${dept}">${dept}</option>
    </#list>

                    </select>
                </div>

                <button type="submit" class="btn btn-info">查找</button>
            </form>
<#else >
</#if>
        <#if (pdfList??) && (pdfList?size > 0)>

            <table class="table table-striped">

                <thead>
                <tr>
                    <th>#</th>
                    <th  style="width: 90px;text-align: center"" >类别</th>
                    <th >文件</th>

                </tr>

                </thead>
                <tbody>
                    <#list pdfList  as pdf>
                    <tr>
                        <td>${pdf?index+1}</td>
                        <td style="width: 90px;text-align: center">${pdf.pdfDept!}</td>
                        <td  style="overflow: hidden; " class="btn-link"   onclick=" window.location.href = '/web/viewer.html?file='+encodeURIComponent('/downloadPdf?fileName=${pdf.pdfUrl}')">${pdf.pdfName!}</td>


                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <br>
            <div class="alert alert-dismissable alert-success">
                <h4>无结果！！！</h4><br>
                所选目录暂时无pdf文件！<br>
            </div>

        </#if>






            <script>
                $(document).ready(function(){
            <#if dept??>
                console.log("${dept!}11111");
                $("#selector").val("${dept!}");
            <#else>
                $("#selector").val("业务类");
            </#if>

                });
            </script>

</body>

</html>