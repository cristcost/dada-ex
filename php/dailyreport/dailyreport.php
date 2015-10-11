<?php 

function reportFromFile($fileName,$outputFileName = NULL) {
	$report = array();
	$total_bytes=0;
	$total_requests=0;

	$handle = @fopen($fileName, "r");
	if ($handle) {
	    while (($line = fgets($handle)) !== false) {
	        list($timestamp,$bytes,$status,$ip_addr) = explode(";", $line);
	        if($status != "200 OK") 
	        	continue;

	        $ip_addr = trim($ip_addr);
			
	        if($report[$ip_addr]) {
	        	$report[$ip_addr]["requests"]++;
	        	$report[$ip_addr]["bytes"]+=(int)$bytes;
	        } else {
				$report[$ip_addr] = array("requests" => 1, "bytes" => (int)$bytes);
	        }

	        $total_requests++;
			$total_bytes += (int)$bytes;
	    }

	    fclose($handle);
	
		$callback = function($a, $b) {
		    if ($a["requests"] == $b["requests"]) {
		        return 0;
		    }
		    return ($a["requests"] > $b["requests"]) ? -1 : 1;
		};
		
		$output = function($string) {
			echo $string;
		};
		if($outputFileName != null) {
			$f = fopen($outputFileName, "w");
			$output = function($string) use ($f) {
				fwrite($f, $string);
			};
		}		

		uasort($report, $callback);
		$output("IP Address;Requests;Total Requests %;Bytes;Total Bytes %\n");
		foreach ($report as $ip => $data) {
			$output($ip.";"
				.$data["requests"].";".number_format($data["requests"] * 100.0 / $total_requests,2)."%;"
				.$data["bytes"].";".number_format($data["bytes"] * 100.0 / $total_bytes,2)."%\n");
		}
		
		if($f) {
			fclose($f); 
		}

		return true;
	} else {
	    return false;
	} 
}
?>