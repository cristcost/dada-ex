<?php 

function reportFromFile($fileName) {
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
		
		uasort($report, $callback);
		echo "IP Address;Requests;Total Requests %;Bytes;Total Bytes %\n";
		foreach ($report as $ip => $data) {
			echo $ip.";"
				.$data["requests"].";".($data["requests"] * 100.0 / $total_requests).";"
				.$data["bytes"].";".($data["bytes"] * 100.0 / $total_bytes)."\n";
		}
	} else {
	    echo "Error opening file!";
	} 
}
?>