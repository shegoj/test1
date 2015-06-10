<?php
$to = "Thomson-GRC-ServiceOps@thomsonreuters.com";
$subject = "Ticket $ticket incomplete";
$message = "Missing OPI score\nPlease fix it at: http://jira.accelus.com/browse/$ticket\n\nThank you!";
$from = "From: ServiceOps OPI <OPI@thomsonreuters.com>";
mail($to,$subject,$message,$from);
?>
