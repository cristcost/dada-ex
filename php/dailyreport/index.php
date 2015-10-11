<?
	if($_FILES) {
		if(is_uploaded_file($_FILES['logfile']['tmp_name']))
		{
			$filename = $_FILES['logfile']['tmp_name'];
			$original = $_FILES['logfile']['name'];
			$file = file($filename);
			
			// browser uploads ".log" files with this Content-Type:
			if($_FILES['logfile']['type'] == "application/octet-stream") {

				require("dailyreport.php");
				header("Content-Type: text/csv");
				header('Content-Disposition: attachment; filename="ipaddr.csv"');
				reportFromFile($_FILES['logfile']['tmp_name']);
				exit;
			} else {
				$error = "Uploaed log file has not the proper mime type (application/octet-stream)";
			}
		} 
	} if($_SERVER["REQUEST_METHOD"] == "POST") {
		$error = "File upload failed. Please check server upload size limit (".ini_get("upload_max_filesize").")";
	}
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>PHP Sandbox | Daily Report</title>
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
				<li class="active"><a href="#">Daily Report</a></li>
				<li><a href="../multiply/">Multiply</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</nav>

	<div class="container">
		<div class="page-header">
			<h1>DailyReport <small>Generate and Donwload Requests Daily Report</small></h1>
		</div>

		<div>
			<? if($error): ?>
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<strong>Warning:</strong> <?= $error; ?>
			</div>
			<? endif; ?>
			<div class="panel panel-default">
				<div class="panel-body">
					<form method="POST" enctype="multipart/form-data" class="form-horizontal" role="form">
						<div class="form-group">
							<legend>Log file Upload</legend>
						</div>
						<div class="form-group">
							<input type="hidden" name="MAX_FILE_SIZE" value="2097152" /> 
							<label for="logInputFile" class="col-sm-1 control-label"><span class="glyphicon glyphicon-file" aria-hidden="true"></span>File:</label>
							<div class="col-sm-6"><input class="btn btn-default" type="file" id="logInputFile" name="logfile" style="height:80px;"></div>
							<p class="col-sm-offset-1 col-sm-11 help-block">Upload a Requests Log file in semicolon separated format.</p>
						</div>
						<button type="submit" class="col-sm-offset-1 btn btn-default">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>