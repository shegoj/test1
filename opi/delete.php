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

$id = $_GET['id'];

mysql_query("DELETE FROM score WHERE id = '$id' ");
mysql_close();

echo "Entry deleted";
?>

<?php
// REDIRECT
header( "refresh:3;url=index.php" );
?>
</center>
</body>
</html>
