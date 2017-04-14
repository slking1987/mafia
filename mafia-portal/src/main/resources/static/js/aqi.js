var aqiChart = echarts.init(document.getElementById('aqiGraphDiv'));
aqiChart.showLoading();
$.getJSON(
    '/aqi/getGraphData',
    function(data) {
        aqiChart.hideLoading();

        var fullList = data.fullList;
        var topList = data.topList;

        var colorGood = "#CCFF99";
        var colorModerate = "#3399CC";
        var colorLightlyPolluted = "#FFCC00";
        var colorModeratePolluted = "#FF6600";
        var colorHevilyPolluted = "#FF0000";
        var colorSeverelyPolluted = "#EE82EE";
        var colorTop5 = "#F5F5F5";

        option = {
            backgroundColor: '#404a59',
            title: {
                text: '全国空气质量报告',
                subtext: '数据来自<和风天气>  ' + data.updateTime,
                sublink: 'https://www.heweather.com',
                left: 'center',
                top: '10%',
                textStyle: {
                    color: '#fff'
                }
            },
            tooltip : {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                bottom: '5%',
                right: '5%',
                data:['优','良','轻度','中度','重度','严重'],
                textStyle: {
                    color: '#fff'
                }
            },
            geo: {
                map: data.mapId,
                label: {
                    emphasis: {
                        show: false
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#323c48',
                        borderColor: '#111'
                    },
                    emphasis: {
                        areaColor: '#2a333d'
                    }
                }
            },
            series : [
                {
                    name: '优',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.goodList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorGood
                        }
                    }
                },
                {
                    name: '良',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.moderateList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorModerate
                        }
                    }
                },
                {
                    name: '轻度',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.lightlyPollutedList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorLightlyPolluted
                        }
                    }
                },
                {
                    name: '中度',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.moderatePollutedList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorModeratePolluted
                        }
                    }
                },
                {
                    name: '重度',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.heavilyPollutedList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorHevilyPolluted
                        }
                    }
                },
                {
                    name: '严重',
                    type: 'scatter',
                    coordinateSystem: 'geo',
                    data: data.severelyPollutedList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorSeverelyPolluted
                        }
                    }
                },
                {
                    name: 'Top 5',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: topList,
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: colorTop5,
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]
        };

        aqiChart.setOption(option);

        aqiChart.on('click', function (params) {
            var city = params.name;
            alert(city);
        });
    }
);