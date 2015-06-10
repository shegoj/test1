<?php
include("connect.php");

/// TOTAL
$result = mysql_query("select application,ROUND(FORMAT(AVG(automation + duration + dedication + status),2),0) as 'AVERAGE EFFORT',FORMAT(((MAX(automation + duration + dedication + status) - MIN(automation + duration + dedication + status)) / count(*)),2) as 'EFFORT VARIATION' from score where (date BETWEEN CAST('2015-04-01' as DATE) AND CAST('2015-08-31' as DATE)) group by application order by application ASC;");
$num = mysql_num_rows ($result);

if ($num > 0 ) {
$i=0;
while ($i < $num) {

		$application = stripslashes(mysql_result($result,$i,"application"));
		$average_effort = stripslashes(mysql_result($result,$i,"AVERAGE EFFORT"));
		$effort_variation = stripslashes(mysql_result($result,$i,"EFFORT VARIATION"));
		switch ($effort_variation) {
			case ($effort_variation >= 0.2):
				$dispersal = "2";
				break;
			case ($effort_variation >= 0.1):
				$dispersal = "1";
				break;
			case ($effort_variation <= 0):
				$dispersal = "0";
				break;
		}

		$opiscore = $average_effort + $dispersal;
		switch ($opiscore) {
			case ($opiscore >= 9 ):
				$pain = "HUGE";
				break;
			case ($opiscore >= 6 ):
				$pain = "HIGH";
				break;
			case ($opiscore >= 3 ):
				$pain = "MEDIUM";
				break;
			case ($opiscore < 3 ):
				$pain = "LOW";
				break;
		}
		
	
	$q2 .= '<tr>
	<td>'.$application.'</a></td>
	<td>'.$average_effort.'</a></td>
	<td>'.$effort_variation.'</a></td>
	<td>'.$dispersal.'</a></td>
	<td>'.$opiscore.'</a></td>
	<td>'.$pain.'</a></td>
	</tr>';
	
++$i; }} else { $q2 = '<tr><td colspan="2" align="center">-- No data yet</td></tr>'; }

mysql_close();
?>
