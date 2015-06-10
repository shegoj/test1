<?php
$hostname='localhost'; //// specify host, i.e. 'localhost'
$user='opi'; //// specify username
$pass='OPSp41n'; //// specify password
$dbase='opi'; //// specify database name
$connection = mysql_connect($hostname , $user , $pass) 
or die ("Can't connect to MySQL");
$db = mysql_select_db($dbase , $connection) or die ("Can't select database.");
?>
