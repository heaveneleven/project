<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>WhatsNearby, a useful plugin</title>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script src="js/es5-shim.min.js" type="text/javascript"></script>
<script src="js/es5-sham.min.js" type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script src="js/WhatsNearby.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#wn1").whatsnearby({
			zoom : 15,
			width : "100%",
			address : "32 Saint-Charles Ouest, Longueuil, Qc, Canada"
		});
		$("#wn1-1").whatsnearby({
			zoom : 15,
			width : "100%"
		});
		$("#wn2").whatsnearby({
			zoom : 15,
			width : "100%",
			address : "Montreal",
			placesRadius : 500,
			placesTypes : [ 'restaurant', 'cafe', 'gym' ]
		});
		$("#wn3").whatsnearby({
			zoom : 15,
			width : "100%",
			address : "Montreal",
			placesRadius : 500,
			placesTypes : [ 'restaurant', 'cafe', 'gym' ],
			excludePlacesTypes : [ 'bar' ]
		});
		$("#wn4").whatsnearby({
			zoom : 15,
			width : "100%",
			address : "Montreal",
			placesRadius : 500,
			placesTypes : [ 'restaurant', 'cafe', 'gym' ],
			excludePlacesTypes : [ 'bar' ]
		});
	});
</script>
</head>
<body>
	<div class="container">
		<h1>WhatsNearby ?</h1>
		<h2>Simple map</h2>
		<div class="well">
			<div id="wn1"></div>
		</div>
		<h2>Simple map using data-address attribute</h2>
		<div class="well">
			<div id="wn1-1" data-address="Longueuil, Qc"></div>
		</div>
		<h2>Showing restaurants, cafes and gyms, 500 meters around the
			center.</h2>
		<div class="well">
			<div id="wn2"></div>
		</div>
		<h2>Showing restaurants, cafes and gyms, 500 meters around the
			center and excluding bars.</h2>
		<div class="well">
			<div id="wn3"></div>
		</div>
		<h2>Custom markup (inside the initial div)</h2>
		<div class="well">
			<div id="wn4">
				<div class='infowindow-markup'>{{name}}</div>
			</div>
		</div>
	</div>
</body>
</html>

