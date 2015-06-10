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
<b>Requests - live data</b>
<br>
<br>
<table>
  <tr>
    <th>
        <form action="jira/sync_requests.php">
        <input type="submit" value="SYNC NOW">
        </form>
    </th>
    <th>
        <form action="export_requests.php" method="post">
        <input type="submit" value="DOWNLOAD">
        </form>
    </th>
    <th>
        <form action="?logout=1" method="post">
        <input type="submit" value="LOGOUT">
        </form>
    </th>
  </tr>
</table>
<?php
include("connect.php");

$result = mysql_query("SELECT * FROM request order by date DESC");
$num = mysql_num_rows ($result);

if ($num > 0 ) {
$i=0;
while ($i < $num) {

		$type = stripslashes(mysql_result($result,$i,"type"));
		$jira = stripslashes(mysql_result($result,$i,"jira"));
		$application = stripslashes(mysql_result($result,$i,"application"));
		$date = stripslashes(mysql_result($result,$i,"date"));
		$id = stripslashes(mysql_result($result,$i,"id"));
	
	$row .= '<tr>
	<td><a target="_blank" href="http://jira.accelus.com/browse/'.$jira.'">'.$jira.'</a></td>
	<td>'.$application.'</a></td>
	<td>'.$type.'</a></td>
	<td>'.$date.'</a></td>
	<td><a href="update.php?id='.$id.'"><img src="images/update.png" height="20px" width="20px" alt="update"></a></td>
	<td><a href="delete.php?id='.$id.'"><img src="images/delete.png" height="17px" width="17px" alt="Delete"></a></td>
	</tr>';
	
++$i; }} else { $row = '<tr><td colspan="2" align="center">Nothing found</td></tr>'; }

mysql_close();
?>
<br>

    <div class="datagrid"><table>
        <thead><tr>
          <th>JIRA</th>
          <th>APPLICATION</th>
          <th>TYPE</th>
          <th>DATE</th>
          <th></th>
          <th></th>
        </tr></thead>
	<?php echo $row ?>
	</table>
	</div>
<br>
<br>
<br>
</center>
</body>
</html>
