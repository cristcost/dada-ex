<?php 
	header("Content-Type: text/csv");
	header('Content-Disposition: attachment; filename="ipaddr.csv"');

	require("../daily_report.php");

	reportFromFile("test_requests.log");
?>