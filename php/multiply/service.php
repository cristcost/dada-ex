<?php 
	require("multiply.php");

	header("Content-Type: application/json");



  	if (is_null($_REQUEST["op1"]) || !validateString($_REQUEST["op1"])) {
      $data["error"][] = "Invalid operand 1";
    }
    if (is_null($_REQUEST["op2"]) || !validateString($_REQUEST["op2"])) {
      $data["error"][] = "Invalid operand 2";
    }

    if(!$data["error"]) {
		$firstOperand = stringToArray($_REQUEST["op1"]);
		$secondOperand = stringToArray($_REQUEST["op2"]);

		$data["result"] = arrayToString(multiply($firstOperand, $secondOperand));	
	}

	echo json_encode($data);
	
?>