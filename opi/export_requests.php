<?php
require("connect.php");
// Fetch Record from Database

$output			= "";
$table 			= "request";
$sql 			= mysql_query("select jira as 'JIRA',application as 'APPLICATION',type as 'TYPE',date as 'DATE' from request order by date DESC;");
$columns_total 	= mysql_num_fields($sql);

// Get The Field Name

for ($i = 0; $i < $columns_total; $i++) {
	$heading	=	mysql_field_name($sql, $i);
	$output		.= '"'.$heading.'",';
}
$output .="\n";

// Get Records from the table

while ($row = mysql_fetch_array($sql)) {
for ($i = 0; $i < $columns_total; $i++) {
$output .='"'.$row["$i"].'",';
}
$output .="\n";
}

// Download the file
$date = date('Y-m-d_H:m');
$filename =  "Request_data_$date.csv";
header('Content-type: application/csv');
header('Content-Disposition: attachment; filename='.$filename);

echo $output;
exit;
?>
