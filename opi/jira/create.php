<?php
 require_once("utils.php");

$new_issue = array(
	'fields' => array(
		'project' => array('key' => 'TIS'),
		'summary' => 'Test via REST',
		'description' => 'Description of issue goes here.',
		'priority' => array('name' => 'Blocker'),
		'issuetype' => array('name' => 'Task'),
		'labels' => array('a','b')
	)
);

function create_issue($issue) {
	return post_to('issue', $issue);
}

$result = create_issue($new_issue);
if (property_exists($result, 'errors')) {
	echo "Error(s) creating issue:\n";
	var_dump($result);
} else {
	echo "New issue created at " . JIRA_URL ."/browse/{$result->key}\n";
}
?>