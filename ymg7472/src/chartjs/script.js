// Code goes here

google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ["준식", 12],
          ["준식", 12],
          ["준식", 12],
        ]);

        var options = {
          title: 'test',
          is3D: false
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }