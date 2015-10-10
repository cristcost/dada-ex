<?php 
	function stringToArray($number) {
		$ret = array();
	 	$length = strlen($number);
	    for ($i = 0; $i < $length; $i++) {
	      $ret[$length - $i - 1] = (int)$number[$i];
	    }
		return $ret;
	}

	function arrayToString($array) {
		krsort($array);	// ensure most significant digit is first
	    return implode("",$array);
	}

	function validateArray($array) {
		for ($i = 0; $i < count($array); $i++) {
			if ($array[$i] < 0 || $array[$i] > 9) {
				return false;
			}
		}
		return true;
	}

	function validateString($string) {
		return preg_match("/^[0-9]*$/",$string) == 1;
	}

	function multiply($a,$b) {
		return $a;
	}
?>