<!DOCTYPE html>
<html>
<head>
	<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
  	<title>tree</title>
  	<script type="text/javascript" src="js/deps.js"></script>
	<script type="text/javascript" src="js/sea.js"></script>
	<script type="text/javascript" src="js/d3.js"></script>
<style type="text/css">
#chart {
  border-top: 1px dashed #F00;
  border-bottom: 1px dashed #F00;
}
.textArea {
  border: 2px solid black;
  color: black;
  font-family: monospace;
  height: 3in;
  overflow: auto;
  padding: 0.5em;
  width: 750px;
}
</style>
</head>

<body>
  <div id="chart"></div>
  <%String dataFileName = "csvdata/" + request.getParameter("domain") + ".csv";%>
  <script>
    seajs.config({
      alias: {
        'DataV': 'datav.js',
        'Tree': 'tree.js'
      }
    });
    seajs.use(["Tree", "DataV"], function (Tree, DataV) {
      var tree = new Tree("chart", {width:1280});
      DataV.csv('<%=dataFileName %>', function(source){
        tree.setSource(source);
        tree.render();
      });
    });
  </script>
</body>
</html>