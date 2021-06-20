<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

    <script src="js/echarts.min.js"></script>
    <script src="js/swiper.min.js"></script>
<#-- <link rel="stylesheet" href="css/swiper.css">-->
    <link rel="stylesheet" href="css/swiper.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    <meta charset="UTF-8">
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

    <title>昨日闸口操作数据</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
        <#--<#if status == "1">
            <ul class="nav nav-tabs">
                <li class= "active" >
                    <a id="getGoct" href="getBargeInPort">GOCT</a>

                <li >
                    <a href="getBargeInPort2" >NCT</a>
                </li>
            </ul>
        <#else >
            <ul class="nav nav-tabs">
                <li >
                    <a href="#" onclick="getGoct()">GOCT</a>

                <li   class= "active">
                    <a href = "#" onclick ="getNct()" >NCT</a>
                </li>
            </ul>

        </#if>-->

            <h4 class="text-info">昨日闸口操作数据&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-primary  btn-sm" href="/getCountTable">每日报表</a></h4>

            <table class="table table-striped">
            <#if (gateList??)&&(gateList?size > 0)>
                <#list gateList  as gate>
                    <tr>
                        <td>${gate.kpi!}</td>
                        <td>${gate.value!}</td>
                    </tr>
                </#list>
            <#else >
                <div class="alert alert-dismissable alert-success">
                    <h4>暂无昨日闸口KPI数据！！！</h4>
                </div>
            </#if >
            </table>
            <div class="swiper-container">
                <div class=" swiper-wrapper btn-group">
                    <button class="swiper-slide btn btn-info" id="buttonDayKpi" type="button">每日统计</button>
                    <button class="swiper-slide btn  btn-info" id="buttonMonthKpi" type="button">月度统计</button>
                    <button class="swiper-slide btn btn-info" id="buttonFEInOut" type="button">业务统计</button>
                    <button class="swiper-slide btn  btn-info" id="buttonFEByEMonth" type="button">月度业务统计</button>
                </div>
            </div>
            <!-- Add Pagination -->
            <br>
            <div id="dayKpi" style="width:335px;height: 500px"></div>
            <div id="feInOut" style="width:345px;height:500px;display:none"></div>
            <div id="monthKpi" style="width: 345px;height: 500px;display:none"></div>
            <div id="feInOutByMonth" style="width: 345px;height: 500px;display:none"></div>
            <br>


        </div>
    </div>
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('dayKpi'));
    var data1 = ${data!};
    var option = {


        tooltip: {
            trigger: 'axis',

        },
        grid: {
            left: '2%',
            //right: '4%',
            bottom: '3%',
            containLabel: true
        },
        legend: {
            x: 'left',
            data: ['操作量', '车次', '回流时间']


        },
        xAxis: {
            data: data1.map(function (item) {
                return item[0];
            })
        },
        yAxis: [{
            splitLine: {
                show: false
            }
        },
            {
                splitLine: {
                    show: false
                }
            }

        ],

        dataZoom: [{
            startValue: '${lastMonth}'
        }, {
            type: 'inside'
        }],

        series: [
            {
                name: '车次',
                // type: 'bar',
                color: '#425066',
                data: data1.map(function (item) {
                    return item[2];
                }),
                type: 'line',
                areaStyle: {},
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '操作量',
                //type: 'bar',
                color: '#808080',
                data: data1.map(function (item) {
                    return item[3];
                }),
                type: 'line',
                areaStyle: {},
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '回流时间',
                type: 'line',
                yAxisIndex: 1,
                //  color: '#44cef6',
                data: data1.map(function (item) {
                    return item[1];
                }),
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]


    };
    myChart.setOption(option);
</script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('feInOut'));
    var data1 = ${dataFE!};
    var option = {
        tooltip: {
            trigger: 'axis',

        },
        grid: {
            left: '2%',
            //right: '4%',
            bottom: '3%',
            containLabel: true
        },
        legend: {
            x: 'left',
            data: ['重进', '重出', '吉进', '吉出']


        },
        xAxis: {
            data: data1.map(function (item) {
                return item[0];
            })
        },
        yAxis: [{
            splitLine: {
                show: false
            }
        },
            {
                splitLine: {
                    show: false
                }
            }

        ],

        dataZoom: [{
            startValue: '${lastMonth}'
        }, {
            type: 'inside'
        }],

        series: [

            {
                name: '重进',
                // type: 'bar',
                color: '#1685a9',
                stack: '操作量',
                data: data1.map(function (item) {
                    return item[1];
                }),
                type: 'bar',
                areaStyle: {}
            },
            {
                name: '重出',
                //type: 'bar',
                stack: '操作量',
                color: '#3eede7',
                data: data1.map(function (item) {
                    return item[2];
                }),
                type: 'bar',
                areaStyle: {}
            },
            {
                name: '吉进',
                type: 'bar',
                stack: '操作量',

                color: '#ffb61e',
                data: data1.map(function (item) {
                    return item[3];
                })

            },
            {
                name: '吉出',
                type: 'bar',
                stack: '操作量',
                //color: '#7fecad',

                color: '#ff8936',
                data: data1.map(function (item) {
                    return item[4];
                })

            }
        ]


    };
    myChart.setOption(option);
</script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('monthKpi'));
    var month = "";
    var time = "";
    var num = "";
    var throughput = "";

    console.log(month, throughput, time, num);


    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        grid: {
            left: '2%',
            //right: '4%',
            bottom: '3%',
            containLabel: true
        },
        /*toolbox: {
            feature: {

                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true}

            }
        },*/
        legend: {
            x: 'left',
            data: ['操作量', '车次', '回流时间']
        },
        xAxis: [
            {
                type: 'category',
                data: [<#list gateList1 as gate>
                    '${gate.month}月',</#list>],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '数量'


                /*axisLabel: {
                    formatter: '{value} ml'
                }*/
            },
            {
                type: 'value',
                name: '回流时间'
                /*min: 0,
                max: 25,
                interval: 5,*/
                /*axisLabel: {
                    formatter: '{value} °C'
                }*/
            }
        ],
        series: [
            {
                name: '操作量',
                type: 'bar',

                color: '#ff8936',
                data: [ <#list gateList1 as gate>
                ${gate.sumThroughput},</#list>]
            },
            {
                name: '车次',
                type: 'bar',
                color: '#1685a9',
                data: [ <#list gateList1 as gate>
                ${gate.sumNum},</#list>]
            },
            {
                name: '回流时间',
                type: 'line',
                //color: '#1685a9',
                color: '#8d4bbb',
                yAxisIndex: 1,
                data: [<#list gateList1 as gate>${gate.avgTime},</#list>],
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    myChart.setOption(option);
</script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('feInOutByMonth'));
    var month = "";
    var fin = "";
    var fout = "";
    var ein = "";
    var eout = "";


    <#list feListByMonth as gate>
    month.valueOf(month+${gate.month}+
    "月" + ","
    )
    ;
    fin = fin+${gate.fin}+
    ",";
    fout = fout+${gate.fout}+
    ",";
    ein = ein+${gate.ein}+
    ",";
    eout = eout+${gate.eout}+
    ",";
    </#list>
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        grid: {
            left: '2%',
            //right: '4%',
            bottom: '3%',
            containLabel: true
        },
        /*toolbox: {
            feature: {

                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true}

            }
        },*/
        legend: {
            x: 'left',
            data: ['重进', '重出', '吉进', '吉出']
        },
        xAxis: [
            {
                type: 'category',
                data: [<#list feListByMonth as gate>'${gate.month}月',</#list>],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '数量',
            }
        ],
        series: [

            {
                name: '重进',
                // type: 'bar',
                color: '#1685a9',
                barWidth: '50%',
                stack: '操作量',
                data: [ <#list feListByMonth as gate>${gate.fin},</#list>],
                type: 'bar',

                areaStyle: {}
            },
            {
                name: '重出',
                //type: 'bar',
                stack: '操作量',
                color: '#3eede7',
                data: [<#list feListByMonth as gate>${gate.fout},</#list>],
                type: 'bar',
                areaStyle: {}
            },
            {
                name: '吉进',
                type: 'bar',
                stack: '操作量',
                color: '#ffb61e',
                data: [<#list feListByMonth as gate>${gate.ein},</#list>]
            },
            {
                name: '吉出',
                type: 'bar',
                stack: '操作量',
                color: '#ff8936',
                data: [<#list feListByMonth as gate>${gate.eout},</#list>]
            }
        ]
    };
    myChart.setOption(option);
</script>
<script>
    $(document).ready(function () {
        $("#buttonDayKpi").click(function () {
            $("#dayKpi").show();
            $("#feInOut").hide();
            $("#monthKpi").hide();
            $("#feInOutByMonth").hide();

        });
        $("#buttonFEInOut").click(function () {
            $("#dayKpi").hide();
            $("#feInOut").show();
            $("#monthKpi").hide();
            $("#feInOutByMonth").hide();
        });
        $("#buttonMonthKpi").click(function () {
            $("#dayKpi").hide();
            $("#feInOut").hide();
            $("#monthKpi").show();
            $("#feInOutByMonth").hide();
        });
        $("#buttonFEByEMonth").click(function () {
            $("#dayKpi").hide();
            $("#feInOut").hide();
            $("#monthKpi").hide();
            $("#feInOutByMonth").show();
        });


    });
</script>
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
</body>
</html>