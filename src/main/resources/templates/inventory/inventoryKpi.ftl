<!DOCTYPE html>
<html lang="en">
<head>


    <script src="js/jquery-3.4.1.min.js"></script>
    <link  rel="stylesheet" href="bootstrap/css/bootstrap.min.css" >
    <script  src="js/echarts.min.js"></script>
<#--<script src="https://unpkg.com/swiper/js/swiper.min.js"> </script>
<script src="https://unpkg.com/swiper/js/swiper.js"> </script>-->
<#--<script  src="js/swiper.js"></script>-->
    <script  src="js/swiper.min.js"></script>
<#-- <link rel="stylesheet" href="css/swiper.css">-->
    <link rel="stylesheet" href="css/swiper.min.css">


    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="referrer" content="no-referrer">


    <meta charset="UTF-8">
    <!-- Demo styles -->
    <style>

        .swiper-container {
            width: 100%;
            height: 100%;
        }
        .swiper-slide {
            text-align: center;


            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }

    </style>

    <title>集装箱KPI</title>
</head>
<body>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->

<div class="container">
<div class="row clearfix">
<div class="col-md-12 column">

<table class="table table-striped">
<thead>
<tr>

    <th>数据项目</th>
    <th>数量</th>
</tr>
</thead>
<tbody>
<#list inventoryCom  as inventory>
        <tr>

            <td>${inventory.item!}</td>
            <td>${inventory.qty!}</td>
        </tr>
</#list >
</tbody>
</table>


    <div class="swiper-container">
        <div class="swiper-wrapper btn-group">
            <button class="swiper-slide btn btn-info" id="buttonCntrKind"  type="button">箱型分类</button>
            <button class="swiper-slide btn btn-info" id="buttonInOut"  type="button">进出口</button>
            <button class="swiper-slide btn  btn-info" id="buttonFCntr" type="button">重柜货物类型</button>
            <button class="swiper-slide btn btn-info"  id="buttonBCntr" type="button">烂柜情况</button>
            <button class="swiper-slide btn btn-info" id="buttonPntrCode"  type="button">船公司柜数量</button>
        </div>

    </div>
    <script>
        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 4,
            spaceBetween: 0,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        });
    </script>
    <div id="cntrCntrKind" style="width:335px;height:400px;"></div>
    <div id="cntrInOut" style="width:335px;height:400px;display:none;"></div>
    <div id="cntrFCntr" style="width:345px;height:400px; display:none;"></div>
    <div id="cntrBCntr" style="width:345px;height:400px; display:none;"></div>
    <div id="cntrPntrCode" style="width:345px;height:500px; display:none;"></div>
</div>
</div>
</div>


<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例翻倒量
var myChart = echarts.init(document.getElementById('cntrCntrKind'));
// 指定图表的配置项和数据
var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)",
    },
    grid: {
        x: 10,
        y: 10,
        x2: 5,
        y2: 2,
        borderWidth: 1
    },

    legend: {
        orient: 'vertical',
        x: 'left',
        data: [ <#list inventoryKind as inventory> '${inventory.item}',</#list>]
    },

    series: [
        {
            name: '箱型分类',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data: [
                <#list inventoryKind as inventory>
                {value: ${inventory.qty!}, name: '${inventory.item}'},
                </#list>
            ]
        },
    ],itemStyle: {
        emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
    }
};

myChart.setOption(option);
</script>

<#if inventoryInOut?size != 0>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例翻倒量
    var myChart = echarts.init(document.getElementById('cntrInOut'));
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)",
        },
        grid: {
            x: 10,
            y: 10,
            x2: 5,
            y2: 2,
            borderWidth: 1
        },

        legend: {
            orient: 'vertical',
            x: 'left',
            data: [
                <#list inventoryInOut as inventory> '${inventory.item}',</#list>
            ]
        },

        series: [
            {
                name: '进出口',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: [
                    <#list inventoryInOut as inventory>
                        {value: ${inventory.qty!}, name: '${inventory.item}'},
                    </#list>
                ]
            },
        ],itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    };

    myChart.setOption(option);
</script>
<#else >
</#if >

<#if fInventoryKind?size != 0>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例翻倒量
    var myChart = echarts.init(document.getElementById('cntrFCntr'));
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)",
        },
        grid: {
            x: 10,
            y: 10,
            x2: 5,
            y2: 2,
            borderWidth: 1
        },

        legend: {
            orient: 'vertical',
            x: 'left',
            data: [
                <#list fInventoryKind as inventory> '${inventory.item}',</#list>
            ]
        },

        series: [
            {
                name: '重柜货物类型',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: [
                    <#list fInventoryKind as inventory>
                        {value: ${inventory.qty!}, name: '${inventory.item}'},
                    </#list>
                ]
            },
        ],itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    };

    myChart.setOption(option);
</script>
<#else >
</#if >

<#if bInventoryInfo?size != 0>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('cntrBCntr'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '烂柜组成（单位:U）'

        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var tar = params[1];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data : [ <#list bInventoryInfo as inventory> '${inventory.item}',</#list>]
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: '辅助',
                type: 'bar',
                stack:  '总量',
                itemStyle: {
                    normal: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    },
                    emphasis: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    }
                },
                data: [<#list bInventoryInfo as inventory> '${inventory.qtyE!?c}',</#list>]
            },
            {
                name: '柜量',
                type: 'bar',
                stack: '总量',
                barWidth: '50%',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data:[<#list bInventoryInfo as inventory> '${inventory.qty!}',</#list>]
            }
        ]
    };

    myChart.setOption(option);
</script>
<#else >
<#--<div class="alert alert-info">
    <h4>
        提示!
    </h4> <strong>注意：</strong> 该船暂无桥时信息.
</div>-->
</#if >
<#if inventoryPtnrCode?size != 0>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('cntrPntrCode'));
    // 指定图表的配置项和数据
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: [ '吉柜', '重柜']
        },
        grid: {
            left: '2%',
            right: '6%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: [<#list inventoryPtnrCode as inventory> '${inventory.item}',</#list>]
        },
        series: [
            {
                name: '吉柜',
                type: 'bar',
                stack: '总量',
               /* label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },*/
                data: [<#list inventoryPtnrCode as inventory> '${inventory.qtyE!?c}',</#list>]
            },
            {
                name: '重柜',
                type: 'bar',
                stack: '总量',
               /* label: {
                    normal: {
                        show: true,
                        position: 'right'
                    }
                },*/
                data: [<#list inventoryPtnrCode as inventory> '${inventory.qtyF!?c}',</#list>]
            }

        ]
    };

    myChart.setOption(option);
</script>
<#else >

</#if >

<script>
    $(document).ready(function () {
        $("#buttonCntrKind").click(function () {
            $("#cntrCntrKind").show();
            $("#cntrBCntr").hide();
            $("#cntrFCntr").hide();
            $("#cntrInOut").hide();
            $("#cntrPntrCode").hide();

        });
        $("#buttonInOut").click(function () {
            $("#cntrCntrKind").hide();
            $("#cntrBCntr").hide();
            $("#cntrFCntr").hide();
            $("#cntrInOut").show();
            $("#cntrPntrCode").hide();
        });
        $("#buttonBCntr").click(function () {
            $("#cntrCntrKind").hide();
            $("#cntrBCntr").show();
            $("#cntrFCntr").hide();
            $("#cntrInOut").hide();
            $("#cntrPntrCode").hide();
        });
        $("#buttonFCntr").click(function () {
            $("#cntrCntrKind").hide();
            $("#cntrBCntr").hide();
            $("#cntrFCntr").show();
            $("#cntrInOut").hide();
            $("#cntrPntrCode").hide();
        });
        $("#buttonPntrCode").click(function () {
            $("#cntrCntrKind").hide();
            $("#cntrBCntr").hide();
            $("#cntrFCntr").hide();
            $("#cntrInOut").hide();
            $("#cntrPntrCode").show();
        });


    });
</script>

<#--<script>

    function exception() {
        event.stopPropagation();
        swal({
            title:"异常记录",

            text: "<#if (exceptionList??) && (exceptionList?size > 0)>\n"+
            "\n"+
                    "            <#list exceptionList  as exception>\n"+
                "\n"+
                "                <table class=\"table table-striped\">\n"+
                "\n"+
                "                    <tr> <td width=\"90px\">创建人</td>  <td>${exception.userName!}</td></tr>\n"+
                "                    <tr> <td>创建时间</td>  <td>${exception.createTime!}</td></tr>\n"+
                "                    <tr> <td>设备号</td>  <td>${exception.quNo!}</td></tr>\n"+
                "                    <tr> <td>原因</td>  <td>${exception.reason!}</td></tr>\n"+
                "                    <tr> <td>停止时间</td>  <td>${exception.stopTime!}</td></tr>\n"+
                "\n"+
                "                    <tr> <td>开始时间</td>  <td>${exception.startTime!}</td></tr>\n"+
                "\n"+
                "\n"+
                "\n"+
                "                </table>\n"+
                        "            </#list>\n"+
                    "        <#else>\n"+
            "            <div class=\"alert alert-info\">\n"+
            "                <h4>\n"+
            "                    提示!\n"+
            "                </h4> 暂无异常记录！！！&lt;#&ndash;是否需要<strong><a href=\"addException\">添加</a></strong>?&ndash;&gt;\n"+
            "            </div>\n"+
                    "        </#if>",

            html: true
        });



    };
</script>-->

</body>
</html>