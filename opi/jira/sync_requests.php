<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<center>
<?php include("../menu.php"); ?>
<br>
<b>Syncing with JIRA...</b>
<br>
<br>
<?php

//pull in login credentials and CURL access function
include("../connect.php");
require_once("utils.php");
require_once("credentials.php");
//create a payload that we can then pass to JIRA with JSON

$request = array('SR', 'IM');
function search_issue($issue) {
	return get_from('search', $issue);
}


foreach ($request as $type)
{
	$start = '(project = "Risk Service OPS") AND (labels =';
	$end = ') AND (Status = Closed OR Status = Resolved) AND (createdDate >= startOfYear()) ORDER BY createdDate DESC, cf[11660] ASC';
	$string = $start.$type.$end ;

	$jql = array(
		'jql' => $string
	);

	//call JIRA.
	$result = search_issue($jql);

	//check for errors
	if (property_exists($result, 'errors')) {
		echo "Error(s) searching for issues:\n";
		var_dump($result);
	} else {
		// GET ISSUE - APPLICATION - DATE
		foreach ($result->issues as &$issue) {
    		$date = preg_replace('/T.*/', '', $issue->fields->created) ;
		$application = ($issue->fields->customfield_11660->value) ; 
		$ticket = ($issue->key);
       
			$query = "select * from request where jira = '$ticket' limit 1";
			$result = mysql_query($query);
			if(mysql_num_rows($result) == 0)
			{
				$insert = "insert into request (type, jira, application, date) values ('$type', '$ticket', '$application', '$date')";
				$result2 = mysql_query($insert);
				echo "$ticket added<br>\n";
			} else {
				// update the database with the values
				$update = "UPDATE request SET type = '$type', application = '$application',  date = '$date' WHERE jira = '$ticket' ";
				$result2 = mysql_query($update);
				echo "$ticket updated<br>\n";
			} 

		}		
	}
}	

?>
<?php
// REDIRECT
header( "refresh:3;url=http://10.12.192.143/opi/feed_requests.php" );
?>
</center>
</body>
</html>
