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

function sum($a, $b) {
	$carry = false;
	$ret = array_fill(0, max(count($a),count($b))+1, 0);;
	for ($i = 0; $i < count($ret); $i++) {
		$partialSum =
		valueAtArrayIndexOrZero($a, $i) + valueAtArrayIndexOrZero($b, $i) + ($carry ? 1 : 0);
		if ($partialSum >= 10) {
			$carry = true;
			$partialSum -= 10;
		} else {
			$carry = false;
		}
		$ret[$i] = $partialSum;
	}
	return stripZeros($ret);
}

function stripZeros($array) {
	for ( $i = count($array) - 1; $i >= 0; $i--) {
		if ($array[$i] == 0) {
			unset($array[$i]);
		} else {
			return $array;
		}
	}
    return array(0); // if zero, return array with one zero
}

function valueAtArrayIndexOrZero($a, $i) {
	if ($i < count($a)) {
		return $a[$i];
	} else {
		return 0;
	}
}

function multiply($a, $b) {
	$ret = array(0);
    // multiply a foreach item in b
    for ($i = 0; $i < count($b); $i++) {
		for ($j = 0; $j < $b[$i]; $j++) {
			$ret = sum($ret, $a);
		}
		$a = shift($a);
	}
	return stripZeros($ret);
}
function shift($array) {
	$ret = array(0);
	for ($i = 0; $i < count($array); $i++) {
		$ret[$i+1] = $array[$i];
	}
	// print_r(array("in"=>$array,"out"=>$ret));
	return $ret;
}

?>