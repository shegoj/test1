<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<center>
<?php include("menu.php"); ?>
<b>Service OPS - 2015 DASHBOARD</b>
<br>
<?php
include("connect.php");

$result = mysql_query("select application as 'APPLICATION',sum(if(request.type = 'SR',1,0)) as 'SR',sum(if(request.type = 'IM',1,0)) as 'IM', coalesce(DEPLOYMENT,0) as 'DEPLOYMENT', coalesce(EFFORT,0) as 'EFFORT' from request left join (select application as app1,count(*) as 'DEPLOYMENT' from score group by application) as b on request.application = b.app1 left join (select application as app2,ROUND(FORMAT(AVG(score.automation + score.duration + score.dedication + score.status),2),0) as 'EFFORT' from score group by application) as c on request.application = c.app2 group by application");
$num = mysql_num_rows ($result);

if ($num > 0 ) {
$i=0;
while ($i < $num) {

		$application = stripslashes(mysql_result($result,$i,"APPLICATION"));
		$sr = stripslashes(mysql_result($result,$i,"SR"));
		$im = stripslashes(mysql_result($result,$i,"IM"));
		$deployment = stripslashes(mysql_result($result,$i,"DEPLOYMENT"));
		$effort = stripslashes(mysql_result($result,$i,"EFFORT"));
	
	$row .= '<tr>
	<td>'.$application.'</a></td>
	<td>'.$sr.'</a></td>
	<td>'.$im.'</a></td>
	<td>'.$deployment.'</a></td>
	<td>'.$effort.'</a></td>
	</tr>';
	
++$i; }} else { $row = '<tr><td colspan="2" align="center">Nothing found</td></tr>'; }

mysql_close();
?>
<br>

    <div class="datagrid3"><table>
        <thead><tr>
          <th>APPLICATION</th>
          <th>SR</th>
          <th>IM</th>
          <th>DEPLOYMENT</th>
          <th>EFFORT</th>
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
