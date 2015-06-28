<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head runat="server">  
<title>导出</title>
<script type="text/javascript" src="./scripts/jquery.min.js"></script> 

<script src="./scripts/highcharts/highcharts.js"></script>
<script src="./scripts/highcharts/modules/exporting.js"></script>
<script src="./scripts/export-csv.js"></script> 
<script type="text/javascript">  
    $(function() {  
        Highcharts.wrap(Highcharts.Chart.prototype, 'getSVG', function (proceed) {  
            return proceed.call(this)  
                .replace(  
                    /(fill|stroke)="rgba([0−9]+,[0−9]+,[0−9]+),([0−9\.]+)"/g,   
                    '$1="rgb($2)" $1-opacity="$3"'  
                );  
        });  
        $('#container').highcharts({  
            title : {  
                text : 'Monthly Average Temperature',  
                x : -20  
            //center  
            },  
            subtitle : {  
                text : 'Source: WorldClimate.com',  
                x : -20  
            },  
            xAxis : {  
                categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ]  
            },  
            yAxis : {  
                title : {  
                    text : 'Temperature (°C)'  
                },  
                plotLines : [ {  
                    value : 0,  
                    width : 1,  
                    color : '#808080'  
                } ]  
            },  
            tooltip : {  
                valueSuffix : '°C'  
            },  
            legend : {  
                layout : 'vertical',  
                align : 'right',  
                verticalAlign : 'middle',  
                borderWidth : 0  
            },  
            series : [ {  
                name : 'Tokyo',  
                data : [ 7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6 ]  
            }, {  
                name : 'New York',  
                data : [ -0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5 ]  
            }, {  
                name : 'Berlin',  
                data : [ -0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0 ]  
            }, {  
                name : 'London',  
                data : [ 3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8 ]  
            } ]  
        });  
  
        $('#container_pie').highcharts({  
            chart : {  
                plotBackgroundColor : null,  
                plotBorderWidth : null,  
                plotShadow : false  
            },  
            title : {  
                text : 'Browser market shares at a specific website, 2010'  
            },  
            tooltip : {  
                pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'  
            },  
            plotOptions : {  
                pie : {  
                    allowPointSelect : true,  
                    cursor : 'pointer',  
                    dataLabels : {  
                        enabled : true,  
                        color : '#000000',  
                        connectorColor : '#000000',  
                        format : '<b>{point.name}</b>: {point.percentage:.1f} %'  
                    }  
                }  
            },  
            series : [ {  
                type : 'pie',  
                name : 'Browser share',  
                data : [ [ 'Firefox', 45.0 ], [ 'IE', 26.8 ], {  
                    name : 'Chrome',  
                    y : 12.8,  
                    sliced : true,  
                    selected : true  
                }, [ 'Safari', 8.5 ], [ 'Opera', 6.2 ], [ 'Others', 0.7 ] ]  
            } ]  
        });  
  
    });  
</script>  
<script type="text/javascript">  
    function exportHighcharts(type){  
        var chart_line = $("#container").highcharts();  
        var chart_pie = $("#container_pie").highcharts();  
        var svg_line = chart_line.getSVG();  
        var svg_pie = chart_pie.getSVG();  
        var svg = svg_line+"_"+svg_pie;  
        $("#svg").val(svg);
        $("#form1").prop("action", "exportHighChart.servlet?type="+type+"").submit();  
    }  
</script>  
</head>  
<body>  
    <form id="form1" action="exportHighChart.servlet" method="post">  
        <div>  
            <input type="hidden" name="svg" id="svg" />   
            <input id="btn_word" type="button" value="导出word" onclick="exportHighcharts('word');"/>   
            <input id="btn_pdf" type="button" value="导出pdf" onclick="exportHighcharts('pdf');"/>  
        </div>  
    </form>  
  
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>  
    <div id="container_pie" style="min-width: 310px; height: 400px; margin: 0 auto"></div>  
</body>  
</html> 