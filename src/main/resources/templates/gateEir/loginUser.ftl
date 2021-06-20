<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/gobal.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="login/css/login.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>外修箱用户登录</title>
    <style>
        @media screen and (max-width: 901px) {
            .login {
                width: 60%;
                position: absolute;
                margin-left: 20%;
                top:20px;
            }
        }
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
<div class="login_box">
    <div id="loading" class="submit_loading" style="display:none">
        <div class="loading_show">
            <img src="images/loading.gif">
            <p class="loading_context">正在提交，请稍候。。。</p>
        </div>
    </div>
    <div class="login_l_img"><img src="login/img/login-img.png"/></div>
    <div class="login">
        <div class="login_logo"><a href="#"><img src="login/img/login_logo.png"/></a></div>
        <div class="login_name">
            <p>外修箱用户登录</p>
        </div>
        <form id="form1" method="post">
            <label for="username">账号：</label>
            <input type="text" name="account" id="account"/>
            <label for="password">密码：</label>
            <input type="password" id="password" name="password"/>
            <br/>
            <button type="button" onclick="ajaxSubmit()" id="submitButton" class="btn btn-primary btn-lg"
                    style="width: 120px">登录
            </button>
            <span id="message"></span>
        </form>
    </div>
    <div class="copyright">广州港南沙港务、南沙海港有限公司 &nbsp;&nbsp;&nbsp;&nbsp;技术支持电话：020-34661901</div>
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

    });

    function ajaxSubmit() {
        if (form1.account.value == "" || form1.password.value == "") {
            alert("请输入正确的账号密码！")
            return false;
        }
        $.ajax({
                    url: "/repairerLogin",
                    data: {
                        "account": form1.account.value,
                        "password": form1.password.value,
                    },
                    type: "post",
                    dataTye: "json",
                    success: function (data) {
                        if (data.code != 1) {
                            fail_prompt(data.msg, 2000);
                        } else {
                            success_prompt("登录成功", 500, data.data.remark);
                        }
                    }
                }
        )
    }

    // 成功提示
    var success_prompt = function (message, time, phone) {
        time = (time === undefined) ? 1200 : time;
        $('<div>')
                .appendTo('body')
                .addClass('alert alert-success')
                .html(message)
                .show()
                .delay(time)
                .fadeOut(function () {
                    toQrPay(phone);
                    //$("#form1").submit();
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

    function toQrPay(phone) {
        var parames = new Array();
        parames.push({name: "phone", value: phone});
        parames.push({name:"webFlag",value: "1"});
        FormPost("/gateEirQuery", parames);
        return false;
    }
</script>