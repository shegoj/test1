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
<b>OPI - live data</b>
<br>
<br>
<table>
  <tr>
    <th>
	<form action="jira/sync_opi.php">
	<input type="submit" value="SYNC NOW">
	</form>
    </th>
    <th>
	<form action="export_opi.php" method="post">
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

$result = mysql_query("SELECT * FROM score order by date DESC");
$num = mysql_num_rows ($result);

if ($num > 0 ) {
$i=0;
while ($i < $num) {

		$jira = stripslashes(mysql_result($result,$i,"jira"));
		$application = stripslashes(mysql_result($result,$i,"application"));
		$date = stripslashes(mysql_result($result,$i,"date"));
		$automation = stripslashes(mysql_result($result,$i,"automation"));
		$duration = stripslashes(mysql_result($result,$i,"duration"));
		$dedication = stripslashes(mysql_result($result,$i,"dedication"));
		$status = stripslashes(mysql_result($result,$i,"status"));
		$id = stripslashes(mysql_result($result,$i,"id"));
		$effort = $status + $dedication + $duration + $automation ;
	
	$row .= '<tr>
	<td><a target="_blank" href="http://jira.accelus.com/browse/'.$jira.'">'.$jira.'</a></td>
	<td>'.$application.'</a></td>
	<td>'.$date.'</a></td>
	<td>'.$automation.'</a></td>
	<td>'.$duration.'</a></td>
	<td>'.$dedication.'</a></td>
	<td>'.$status.'</a></td>
	<td bgcolor="#FF4D4D"><b>'.$effort.'</b></a></td>
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
          <th>DATE</th>
          <th>AUTOMATION</th>
          <th>DURATION</th>
          <th>DEDICATION</th>
          <th>STATUS</th>
          <th>EFFORT</th>
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
