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

$id = $_POST['id'];

$jira = trim(mysql_real_escape_string($_POST["jira"]));
		$application = trim(mysql_real_escape_string($_POST["application"]));
		$date = trim(mysql_real_escape_string($_POST["date"]));
		$automation = trim(mysql_real_escape_string($_POST["automation"]));
		$duration = trim(mysql_real_escape_string($_POST["duration"]));
		$dedication = trim(mysql_real_escape_string($_POST["dedication"]));
		$status = trim(mysql_real_escape_string($_POST["status"]));
		
$rsUpdate = mysql_query("UPDATE score
	SET  jira = '$jira',  application = '$application',  date = '$date',  automation = '$automation',  duration = '$duration',  dedication = '$dedication',  status = '$status'
	WHERE id = '$id' ");

if($rsUpdate) { echo "Successfully updated"; } else { die('Invalid query: '.mysql_error()); }
?>

<?php
// REDIRECT
header( "refresh:3;url=index.php" );
?>
</center>
</body>
</html>
