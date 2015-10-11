<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PHP Sandbox | Multiply</title>
	<!-- Latest compiled and minified CSS & JS -->
	<link rel="stylesheet" media="screen" href="//netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="//code.jquery.com/jquery.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="..">PHP Sandbox</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li><a href="../dailyreport/">Daily Report</a></li>
				<li class="active"><a href="#">Multiply</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a id="googol" href="#100000000000000000000000000000000000000000000000000">#</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</nav>

	<div class="container">
		<div class="page-header">
			<h1>Multiply <small>Multiply big numbers</small></h1>
		</div>
		<div id="errors"></div>
		<div>
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<legend>Input Operands:</legend>
						</div>
						<div class="form-group">
							<div class="row">
								<label for="firstOperand" class="col-sm-2 control-label">1st operand:</label>
								<input type="number" id="firstOperand" class="col-sm-9">
							</div>

							<div class="row">
								<span class="col-sm-2 control-label"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></span>
							</div>

							<div class="row">
								<label for="secondOperand" class="col-sm-2 control-label">2st operand:</label>
								<input type="number" id="secondOperand" class="col-sm-9">
							</div>
							<div class="row">
								<button type="button" id="compute" class="col-sm-offset-2 col-sm-2 btn btn-primary">Compute result</button>
							</div>
						</div>
						<div class="form-group">
							<legend>Result:</legend>
							<div class="row">
								<input type="text" id="result" class="col-sm-offset-2 col-sm-9" readonly>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 100000000000000000000000000000000000000000000000000 -->

	<script type="text/javascript">

	$('#googol').click(function(event) {
		$('#firstOperand').val("100000000000000000000000000000000000000000000000000");
		$('#secondOperand').val("100000000000000000000000000000000000000000000000000");
	});

	var appendError = function(msg) {
		var errorDiv = $('<div class="alert alert-danger">')
			.append($('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'))
			.append($('<strong>Error: </strong>'))
			.append($('<test>'+msg+'</test>'));
		$("#errors").append(errorDiv);
	}

	$('#compute').click(function(event) {
		var op1 = $('#firstOperand').val();
		var op2 = $('#secondOperand').val();
		$.ajax({
				url: 'service.php',
				dataType: 'json',
				method: 'POST',
				data: {
					"op1":op1,
					"op2":op2
				},
				success: function(response) {
					if (response.error) {
						for (var i = response.error.length - 1; i >= 0; i--) {
							appendError(response.error[i]);
						};
					} else {
						if(response.result == '10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000') {
							var outputElement = $('#result');
							outputElement.popover({
								content:'This is 1 googol!',
								trigger:'manual',
								placement:'top',
								title:'WoW!'
							});
							outputElement.popover('show');
							setTimeout(function(){ outputElement.popover('hide');; }, 3000);
							
						} 
						$('#result').val(response.result);
						
					}
				},
				error: function() {
					appendError("Service request error");
				}
			});
	});
	</script>
</body>
</html>