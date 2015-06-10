<?php
 require_once("utils.php");

$key = 'TIS-71';
$fields = array(
	'fields' => array(
		'summary' => "Update to issue 71"
	)
);

function edit_issue($key, $fields) {
	return put_to('issue/'.$key, $fields);
}

$result = edit_issue($key, $fields);
if ($result != NULL) {
	echo "Error(s) creating issue:\n";
	var_dump($result);
} else {
	echo "Edits complete. Issue can be viewed at " . JIRA_URL ."/browse/{$key}\n";
}
?>