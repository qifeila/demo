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

    <title>统计数据</title>
</head>
<body>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <h4 class="text-info">二三期互提网上办单统计</h4>
            <br>
            <div id="dayKpi" style="width:325px;height: 500px"></div>
        </div>
    </div>

    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('dayKpi'));
        var data1 = ${cntrNumData!};
        var option = {
            tooltip: {
                trigger: 'axis',
            },
            grid: {
                 left: '2%',
                right: '2%',
                bottom: '3%',
                containLabel: true
            },
            legend: {
                x: 'left',
                data: ['提二还三(Unit)', '提三还二(Unit)']


            },
            xAxis: {
                data: data1.map(function (item) {
                    return item[0];
                })
            },
            yAxis: [

            {
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
                startValue:  ${lastWeek}
            }, {
                type: 'inside'
            }],

            series: [
                {
                    name: '提二还三(Unit)',
                    //type: 'bar',
                    color: '#808080',
                    data: data1.map(function (item) {
                        return item[1];
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
                    name: '提三还二(Unit)',
                    type: 'line',
                    yAxisIndex: 1,
                    // color: '#44cef6',
                    data: data1.map(function (item) {
                        return item[2];
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
</div>

</body>
</html>