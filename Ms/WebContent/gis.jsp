<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--The viewport meta tag is used to improve the presentation and behavior of the samples 
      on iOS devices-->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no">
    <title>Simple Image Service</title>
    <link rel="stylesheet" href="http://js.arcgis.com/3.13/esri/css/esri.css" />

    <style>
      html, body, #map { height: 100%; width: 100%; margin: 0; padding: 0; }
    </style>

    <script src="http://js.arcgis.com/3.13/"></script>
    <script>
      var map;
      require([
        "esri/map", "esri/layers/ArcGISImageServiceLayer", 
        "esri/layers/ImageServiceParameters", "dojo/parser", "dojo/domReady!"
      ], function(
        Map, ArcGISImageServiceLayer, 
        ImageServiceParameters, parser
      ) {
        parser.parse();

        map = new Map("map", {
          basemap: "topo",
          center: [-79.40, 43.64],
          zoom: 12
        });

        var params = new ImageServiceParameters();
        params.noData = 0;
        var imageServiceLayer = new ArcGISImageServiceLayer("http://sampleserver6.arcgisonline.com/arcgis/rest/services/Toronto/ImageServer", {
          imageServiceParameters: params,
          opacity: 0.75
        });
        map.addLayer(imageServiceLayer);
      });
    </script>
  </head>
  
  <body>
    <div id="map"> </div>
  </body>

</html>
 