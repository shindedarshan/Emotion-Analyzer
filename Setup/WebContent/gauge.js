var txt = document.getElementById("txt");
var sen = txt.value;
console.log(sen);
console.log(document.getElementById("result2").value);
var data = document.getElementById("result2").value;
var data1 = data.split(";");
var gauge1 = new JustGage({
	id : "gauge1",
	title : "Anger",
	value : data1[0] * 100,
	min : 0,
	max : 100,
	humanFriendly : false,
	decimals : 0,
	counter : true,
	levelColors : [ "red" ],
	startAnimationTime : 2000,
	startAnimationType : ">",
	refreshAnimationTime : 1000,
	refreshAnimationType : "bonce",
	label : "Anger"
});
var gauge2 = new JustGage({
	id : "gauge2",
	value : data1[1] * 100,
	min : 0,
	max : 100,
	humanFriendly : false,
	decimals : 0,
	counter : true,
	levelColors : [ "green" ],
	title : "Joy",
	startAnimationTime : 2000,
	startAnimationType : ">",
	refreshAnimationTime : 1000,
	refreshAnimationType : "linear",
	label : "Joy"
});

var gauge3 = new JustGage({
	id : "gauge3",
	value : data1[2] * 100,
	min : 0,
	max : 100,
	humanFriendly : false,
	decimals : 0,
	counter : true,
	levelColors : [ "#007FFF" ],
	title : "Sad",
	startAnimationTime : 2000,
	startAnimationType : ">",
	refreshAnimationTime : 1000,
	refreshAnimationType : "linear",
	label : "Sad"
});

var gauge4 = new JustGage({
	id : "gauge4",
	value : data1[3] * 100,
	min : 0,
	max : 100,
	humanFriendly : false,
	decimals : 0,
	counter : true,
	levelColors : [ "black" ],
	title : "Fear",
	startAnimationTime : 2000,
	startAnimationType : ">",
	refreshAnimationTime : 1000,
	refreshAnimationType : "linear",
	label : "fear"
});