$(function(){
	$("#myid").click(function(){
		alert($("#myid").html());
	});
});

function load() {   
if (GBrowserIsCompatible()) {   
var map = new GMap2(document.getElementById("map"));   
map.setCenter(new GLatLng(37.4419, -122.1419), 13);   
}   
}