<%@page import="Worker.WordNet"%>
<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Controller.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Emotion Analyzer</title>
<style type="text/css">
input.search {
	height: 50px;
	width: 50px;
}

textarea {
	font-size: 18px;
	font-style: oblique;
}

div {
	background: #EBE9F9; /* fallback for old browsers */
}

.gauge {
	width: 220px;
	height: 220px;
	display: inline-block;
	margin: 5px;
}

div.gauge img {
	position: relative;
	right: 10px;
	left: -1px;
	bottom: 220px;
}

/* #gauge1 {
	 background: url(anger1.jpg) no-repeat;
	position: absolute;
	right: 0;
	bottom: 0;
}

#gauge2 {
	background: url(joy1.jpg) no-repeat;
	position: absolute;
	right: 0;
	bottom: 0;
}

#gauge3 {
	background: url(sad1.jpg) no-repeat;
	position: absolute;
	right: 0;
	bottom: 0;
}

#gauge4 {
	background: url(fear1.png) no-repeat;
	position: absolute;
	right: 0;
	bottom: 0;
} */
#chart {
	background: rgba(235, 233, 249, 1);
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	text-align: center;
	top: 75px;
	border: 3px solid #000000;
	position: absolute;
	right: 530px;
	width: 500px;
	padding: 10pxi
}
</style>
<script src="raphael-2.1.4.min.js"></script>
<script src="justgage.js"></script>
</head>
<body background="/EmotionAnalyzer/emotions.png">
	<div id="chart">
		<!-- style="text-align: center; top: 200px; border: 3px solid #000000; position: absolute; right: 500px; width: 350px; padding: 10px;"> -->

		<h1>Emotion Analyzer</h1>

		<form action="#">
			<textarea rows="2" cols="38" name="text" id="txt" autofocus></textarea>
			<br> <br> <input type="Submit" value="Analyze" id="search"
				style="background-color: orange; font-size: 20px"> <br>
			<br> <span> <%
 	String result = null;
			String userInput = request.getParameter("text");
			try{
 	if ((request.getParameter("text")) != null) {
 		result = new Controller().showCategory(request
 				.getParameter("text"));
 	}}
			catch(Exception e){}
 	/* if (result != null) {
 		String rSplit[] = result.split(";");
 		out.println("<h2>Feeling : " + rSplit[0] + "<br>Polarity : "
 				+ rSplit[1] + "</h2>");
 		out.println("<h3>" + rSplit[2] + "<br><br>" + rSplit[3]
 				+ "</h3>");
 	} */
 	if (result != null) {
 		String sp[] = result.split(";");
 		if (sp[0].equals("0.0") && sp[1].equals("0.0")
 				&& sp[2].equals("0.0") && sp[3].equals("0.0")) {
 			try{
 			new WordNet().addWord(userInput);}
 			catch(Exception e){}
 			String d = "<img src=\"noemo2.png\">";
 			String tag = "<figure>"
 					+ d
 					+ "<figcaption><h2>No Emotions..</figcaption></h2></figure>";
 			out.print(tag);
 			String st = "<script> document.getElementById('txt').innerHTML="
 					+ "\""
 					+ request.getParameter("text")
 					+ "\""
 					+ "</script>";
 			out.println(st);
 		} else {
 			String s1 = "<div  id=" + "\"gauge1" + "\" class="
 					+ "\"gauge" + "\"><img src=\"anger1.jpg\"></div>";
 			String s2 = "<div id=" + "\"gauge2" + "\" class="
 					+ "\"gauge" + "\"><img src=\"joy1.jpg\"></div>";
 			String s3 = "<div id=" + "\"gauge3" + "\" class="
 					+ "\"gauge" + "\"><img src=\"sad1.jpg\"></div>";
 			String s4 = "<div id=" + "\"gauge4" + "\" class="
 					+ "\"gauge" + "\"><img src=\"fear1.png\"></div>";
 			out.println(s1 + s2 + s3 + s4);
 			String tag = "<input type=" + "\"hidden" + "\" value="
 					+ result + " id=" + "\"result2\">";
 			out.println(tag);
 			String t = "\"text/javascript\"";
 			String f = "\"gauge.js\"";
 			String s = "<script type=" + t + " src=" + f + "></script>";
 			out.println(s);
 			String st = "<script> document.getElementById('txt').innerHTML="
 					+ "\""
 					+ request.getParameter("text")
 					+ "\""
 					+ "</script>";
 			out.println(st);
 		}
 	}
 %>
			</span>
		</form>

		<%-- <% out.println(new Tester2().showCategory()); %> --%>
		<br>
	</div>
</body>
</html>