<?php 

require_once("credentials.php");

function put_to($resource, $data) {
	$jdata = json_encode($data);
	$ch = curl_init();
	curl_setopt_array($ch, array(
		CURLOPT_CUSTOMREQUEST=>"PUT",
		CURLOPT_URL => JIRA_URL . '/rest/api/latest/' . $resource,
		CURLOPT_USERPWD => USERNAME . ':' . PASSWORD,
		CURLOPT_POSTFIELDS => $jdata,
		CURLOPT_HTTPHEADER => array(
			'Accept: application/json',
			'Content-Type: application/json'
			),
		CURLOPT_RETURNTRANSFER => true
	));
	$result = curl_exec($ch);
	curl_close($ch);
	return json_decode($result);
}


function post_to($resource, $data) {
	$jdata = json_encode($data);
	$ch = curl_init();
	curl_setopt_array($ch, array(
		CURLOPT_POST => 1,
		CURLOPT_URL => JIRA_URL . '/rest/api/latest/' . $resource,
		CURLOPT_USERPWD => USERNAME . ':' . PASSWORD,
		CURLOPT_POSTFIELDS => $jdata,
		CURLOPT_HTTPHEADER => array('Content-type: application/json'),
		CURLOPT_RETURNTRANSFER => true
	));
	$result = curl_exec($ch);
	curl_close($ch);
	return json_decode($result);
}

function get_from($resource, $data) {
	//convert array to JSON string
	$jdata = json_encode($data);
	$ch = curl_init();
	//configure CURL
	curl_setopt_array($ch, array(
		CURLOPT_URL => JIRA_URL . '/rest/api/latest/' . $resource,
		CURLOPT_USERPWD => USERNAME . ':' . PASSWORD,
		CURLOPT_POSTFIELDS => $jdata,
		CURLOPT_HTTPHEADER => array('Content-type: application/json'),
		CURLOPT_RETURNTRANSFER => true
	));
	$result = curl_exec($ch);
	curl_close($ch);
	// DEBUG
	// echo($result);
	//convert JSON data back to PHP array
	return json_decode($result);
}
?>
