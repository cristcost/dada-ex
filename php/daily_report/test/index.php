<?php 
	header("Content-Type: text/plain");
	require("../daily_report.php");

	reportFromFile("test_requests.log");
?>