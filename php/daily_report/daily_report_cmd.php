<?php 
	require("daily_report.php");

	if(count($argv) < 2) {
    	echo "Usage:   dailyreport.sh inputfile [outputfile]\n";
    	echo "Example: dailyreport.sh  /logfiles/requests.log /reports/ipaddr.csv\n";
		exit;
	}

	$inputFileName = $argv[1];
	$outputFileName = $argv[2];

	if($inputFileName && file_exists($inputFileName)) {
		if(!reportFromFile($inputFileName,$outputFileName)) {
			echo "Error opening file!";
		}
	} else {
		echo "file $inputFileName does not exist\n";
	}
?>