<?php 
	header("Content-Type: text/csv");
	header('Content-Disposition: attachment; filename="ipaddr.csv"');

	require("../daily_report.php");

	if(!reportFromFile("test_requests.log")) {
		echo "Error opening file!";
	}
?>