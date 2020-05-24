<!DOCTYPE html>
<html>

  <head>
	<style>
.ChartDiv
{
  width: 1200px; 
  height: 700px;
}
</style>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script>
      // Code goes here

google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          <#list wordList as wl>
			['${wl.text}', ${wl.size}] <#sep>,
		  </#list>
        ]);

        var options = {
          title: '${date} 자 뉴스의 단어 비율',
          is3D: false
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <div id="piechart" class="ChartDiv"></div>
  </body>

</html>