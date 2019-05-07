
console.log(document.getElementById("result2").value);
var data = document.getElementById("result2").value;
var data1 = data.split(";");
if(data1[0]==="0.0" && data1[1]==="0.0" && data1[2]==="0.0" && data1[3]==="0.0"){
	alert("No Emotions.");
}
else{
	var svg = dimple.newSvg("div", 450, 350);
	var chartPara = [ {
		"  " : "Anger",
		" " : parseFloat(data1[0]) * 100.0
	}, {
		"  " : "Joy",
		" " : parseFloat(data1[1]) * 100.0
	}, {
		"  " : "Sad",
		" " : parseFloat(data1[2]) * 100.0
	}, {
		"  " : "Fear",
		" " : parseFloat(data1[3]) * 100.0
	}];
	var chart = new dimple.chart(svg, chartPara);
	var axis = chart.addCategoryAxis("x", "  ");
	axis.fontSize = 14;
	chart.addMeasureAxis("y", " ");
	chart.addSeries(null, dimple.plot.bar);
	chart.addSeries("  ", dimple.plot.bar);
	chart.assignColor("Anger", "red", "black", 1);
	chart.assignColor("Joy", "green", "black", 1);
	chart.assignColor("Sad", "	#0D98BA", "black", 1);
	chart.assignColor("Fear", "black", "black", 1);
	chart.ease = "sine";
	chart.staggerDraw = true;
	chart.draw(2000);	
}