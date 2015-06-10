<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<center>
<?php include("menu.php"); ?>
<br>
<?php
include("connect.php");

$jira = trim(mysql_real_escape_string($_POST["jira"]));
		$application = trim(mysql_real_escape_string($_POST["application"]));
		$date = trim(mysql_real_escape_string($_POST["date"]));
		$automation = trim(mysql_real_escape_string($_POST["automation"]));
		$duration = trim(mysql_real_escape_string($_POST["duration"]));
		$dedication = trim(mysql_real_escape_string($_POST["dedication"]));
		$status = trim(mysql_real_escape_string($_POST["status"]));
		
$results = mysql_query("INSERT INTO score (id, jira, application, date, automation, duration, dedication, status)
	VALUES ('', '$jira', '$application', '$date', '$automation', '$duration', '$dedication', '$status')");

if($results) { echo "Successfully Added"; } else { die('Invalid query: '.mysql_error()); }

?>

<?php 
// REDIRECT
header( "refresh:3;url=index.php" );
?>
</center>
</body>
</html>
