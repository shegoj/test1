<?php include("/var/www/html/data/opi/password.php"); ?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<?php include("menu.php"); ?>

<center>
<br>
<form action="jira/sync.php">
    <input type="submit" value="SYNC NOW">
</form>

<br /><a href="http://10.12.192.143/opi/sync.php?logout=1">Logout</a>
</center>
</body>
</html>
