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
textarea {
	font-size: 14px;
	font-style: oblique;
}

div {
	background: rgba(235,233,249,1); /* fallback for old browsers */
}
div {
	background: rgba(235,233,249,1);
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	text-align: center; top: 150px; border: 3px solid #000000; position: absolute; right: 500px; width: 450px; padding: 10pxi
}
</style>
<script src="d3.v3.4.8.min.js"></script>
<script src="dimple.v2.1.6.min.js"></script>
</head>
<body background="/EmotionAnalyzer/emotions.png">
	<div id="#chart">
		<!-- style="text-align: center; top: 200px; border: 3px solid #000000; position: absolute; right: 500px; width: 350px; padding: 10px;"> -->
		<h1>
			<h2>Emotion Analyzer</h2>
		</h1>
		<form action="#">
			<textarea rows="2" cols="30" name="text" autofocus></textarea>
			<br> <br> <input type="Submit" value="Analyze"
				style="background-color: orange; font-size: 20px"> <br>
			<span> <%
 	String result = null;
 	if ((request.getParameter("text")) != null) {
 		result = new Controller()
 				.showCategory(request.getParameter("text"));
 	}
 	/* if (result != null) {
 		String rSplit[] = result.split(";");
 		out.println("<h2>Feeling : " + rSplit[0] + "<br>Polarity : "
 				+ rSplit[1] + "</h2>");
 		out.println("<h3>" + rSplit[2] + "<br><br>" + rSplit[3]
 				+ "</h3>");
 	} */
 	if (result != null) {
 		String tag = "<input type=" + "\"hidden" + "\" value=" + result
 				+ " id=" + "\"result2\">";
 		out.println(tag);
 		String t = "\"text/javascript\"";
 		String f = "\"bar.js\"";
 		String s = "<script type=" + t + " src=" + f + "></script>";
 		out.println(s);
 	}
 %>
			</span>
		</form>

		<%-- <% out.println(new Tester1().showCategory()); %> --%>
		<br>
	</div>
</body>
</html>