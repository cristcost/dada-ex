<?php 
	require("multiply.php");


	if(count($argv) < 3) {
		echo("Usage:   multiply operand1 operand2\n");
    	echo("Example: multiply 123 321\n");
		exit;
	}
  	if (!validateString($argv[1])) {
      echo "Invalid operand 1\n";
      exit;
    }
    if (!validateString($argv[2])) {
      echo "Invalid operand 2\n";
      exit;
    }


	$firstOperand = stringToArray($argv[1]);
	$secondOperand = stringToArray($argv[2]);


	echo arrayToString(multiply($firstOperand, $secondOperand))."\n";	
	
?>