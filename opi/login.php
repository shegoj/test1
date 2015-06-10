<?php include("/var/www/html/data/opi/password.php"); ?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<center>
<?php include("menu.php"); ?>
<?php
echo date("D d/m/Y H:i:s");
?>
<p>User <b>serviceops</b> logged in</p><br><br>
<form action="?logout=1" method="post">
<input type="submit" value="LOGOUT">
</form>
</center>
</body>
</html>
