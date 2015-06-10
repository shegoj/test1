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
<br>
<?php

//pull in login credentials and CURL access function
include("../connect.php");
require_once("utils.php");
require_once("credentials.php");
//create a payload that we can then pass to JIRA with JSON
$jql = array(
	'jql' => '(project = "Risk Service OPS") AND (labels = DPL AND labels != MST) AND (status = Closed) AND (Resolution = Fixed) AND createdDate >= startOfYear() ORDER BY createdDate DESC, cf[11660] ASC'
);

/*define a function that calls the right REST API
We convert the array to JSON inside of the function. */
function search_issue($issue) {
	return get_from('search', $issue);
}

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
       
	// GET COMMENTS & OPI 
	$ch = curl_init();
        curl_setopt_array($ch, array(
                CURLOPT_URL => JIRA_URL . '/rest/api/2/issue/' . $ticket . '/comment',
                //CURLOPT_URL => 'http://jira.accelus.com/rest/api/2/issue/' . $ticket . '/comment',
                CURLOPT_USERPWD => USERNAME . ':' . PASSWORD,
                CURLOPT_HTTPHEADER => array('Content-type: application/json'),
                CURLOPT_RETURNTRANSFER => true
        ));
        $comment = curl_exec($ch);
        curl_close($ch);
		$query = "select * from score where jira = '$ticket' limit 1";
		$result = mysql_query($query);
		if (preg_match('/"#OPI/', $comment)){
	        	$start = strrpos($comment,"\"body\":\"#OPI");
	        	$start += 9;
        		$end =  strrpos($comment,"\"updateAuthor\"", $start);
		        $newComment = substr($comment,$start,$end -($start + 2));
	        	$score = preg_replace('/OPI|\W+r|\W+n/', ' ', $newComment);
			$score = str_replace("  "," ",$score);
			$a=explode(" ", trim($score));
			$automation_1=explode(":", trim($a[0]));
			$automation=$automation_1[1];
			$duration_1=explode(":", trim($a[1]));
			$duration=$duration_1[1];
			$dedication_1=explode(":", trim($a[2]));
			$dedication=$dedication_1[1];
			$status_1=explode(":", trim($a[3]));
			$status=$status_1[1];

			// check if the ticket has been already synched with the MySQ database
			//include("../connect.php");
			//$query = "select * from score where jira = '$ticket' limit 1";
			//$result = mysql_query($query);
			if(mysql_num_rows($result) == 0)
			{
				$insert = "insert into score (jira, application, date, automation, duration, dedication, status) values ('$ticket', '$application', '$date', '$automation', '$duration', '$dedication', '$status')";
				$result2 = mysql_query($insert);
				echo "$ticket added<br>\n";
			} else {
			// update the database with the values
				
				$update = "UPDATE score SET application = '$application',  date = '$date',  automation = '$automation',  duration = '$duration',  dedication = '$dedication',  status = '$status' WHERE jira = '$ticket' ";
				$result2 = mysql_query($update);
				echo "$ticket updated<br>\n";
			} 
			// debug	
			// echo($issue->key . "  " . $application . " " . $date . " " . $automation . " " . $duration . " " . $dedication . " " . $status . "<br>\n");

		} else {

			if(mysql_num_rows($result) == 0)
                        {
                                $insert = "insert into score (jira, application, date, automation, duration, dedication, status) values ('$ticket', '$application', '$date', '', '', '', '')";
                                $result2 = mysql_query($insert);
                                echo "$ticket - <b>MISSING OPI</b><br>\n";
				require("../mail.php");
				// debug
				// echo($issue->key . "  " . $application . " " . $date . "<br>\n");
                        } else {
                                echo "$ticket - <b>MISSING OPI</b><br>\n";
				require("../mail.php");
                        } 
		}
	}
}

?>
<?php 
// REDIRECT
header( "refresh:3;url=http://10.12.192.143/opi/feed_opi.php" );
?>
</center>
</body>
</html>
