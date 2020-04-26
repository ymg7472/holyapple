<!DOCTYPE html>

<html>

	<head>
		<script src="https://d3js.org/d3.v3.min.js"></script>
		<script src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js" type="text/JavaScript"></script>	
	</head>
	
	<body>
		<script>

			var skillsToDraw = [
			
			<#list wordList as wl>
			{text: '${wl.text}', size: ${wl.size}} <#sep>,
			</#list>
			
			
			];
			
			
			
			var width = 960;
			var height = 500;
			var fill = d3.scale.category20();
			
			var svg = d3.select("body").append("svg")
			
			  .attr("width", width)
			  .attr("height", height);
			  
			var svg = d3.select("svg")
			
			  .append("g")
			  .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")")
			  
			var keywords = ["JavaScript", "Actionscript", "coffeescript"]
			
			function showCloud(words) {
			
			  d3.layout.cloud()
			    .size([width, height])
			    .words(words)
			    .rotate(function() {
			      return ~~(Math.random() * 2) * 90;
			    })
			    .font("Impact")
			    .fontSize(function(d) {
			      return d.size;
			    })
			    .on("end", drawSkillCloud)
			    .start();
			}
			showCloud(skillsToDraw);
			
			// Finally implement `drawSkillCloud`, which performs the D3 drawing:
			
			// apply D3.js drawing API
			function drawSkillCloud(words) {
			
			  var cloud = svg.selectAll("text").data(words)
			  //Entering words
			  cloud.enter()
			    .append("text")
			    .style("font-family", "overwatch")
			    .style("fill", function(d) {
			      return (keywords.indexOf(d.text) > -1 ? "#fbc280" : "#405275");
			    })
			    .style("fill-opacity", .5)
			    .attr("text-anchor", "middle")
			    .attr('font-size', 1)
			    .text(function(d) {
			      return d.text;
			    });
			  cloud
			    .transition()
			    .duration(600)
			    .style("font-size", function(d) {
			      return d.size + "px";
			    })
			    .attr("transform", function(d) {
			      return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			    })
			    .style("fill-opacity", 1);
			}
			
			// set the viewbox to content bounding box (zooming in on the content, effectively trimming whitespace)
			
			setInterval(function() {
			  showCloud(skillsToDraw);
			}, 2000)
			
		</script>
		
		
</body>


</html>
