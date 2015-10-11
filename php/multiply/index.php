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
		</div><!-- /.navbar-collapse -->
	</nav>

	<div class="container">
		<div class="page-header">
			<h1>Multiply <small>Multiply big numbers</small></h1>
		</div>

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
								<button type="submit" id="compute" class="col-sm-offset-2 btn btn-primary">Compute result</button>
							</div>
						</div>
						<div class="form-group">
							<legend>Result:</legend>
							<div class="row">
								<input type="number" id="result" class="col-sm-offset-2 col-sm-9" readonly>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>