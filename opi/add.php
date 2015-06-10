<?php include("/var/www/html/data/opi/password.php"); ?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<?php include("menu.php"); ?>

<center>
<br>
<p>Add an entry (manually)</p>
<br>
<br>
<form id="FormName" action="added.php" method="post" name="FormName">
<table width="448" border="0" cellspacing="2" cellpadding="0">

<tr>
<td width="150" align="right"><label for="jira">jira</label></td>
<td><input name="jira" maxlength="" type="text" value="<?php echo stripslashes($jira) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="application">application</label></td>
<td><input name="application" maxlength="" type="text" value="<?php echo stripslashes($application) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="date">date</label></td>
<td><input name="date" maxlength="" type="text" value="<?php echo stripslashes($date) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="automation">automation</label></td>
<td><input name="automation" maxlength="" type="text" value="<?php echo stripslashes($automation) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="duration">duration</label></td>
<td><input name="duration" maxlength="" type="text" value="<?php echo stripslashes($duration) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="dedication">dedication</label></td>
<td><input name="dedication" maxlength="" type="text" value="<?php echo stripslashes($dedication) ?>"></td>
</tr>

<tr>
<td width="150" align="right"><label for="status">status</label></td>
<td><input name="status" maxlength="" type="text" value="<?php echo stripslashes($status) ?>"></td>
</tr>

<tr>
<td colspan="2" align="center"><input name="" type="submit" value="Add"></td>
</tr>

</table>
</form>

<a href="http://10.12.192.143/opi/add.php?logout=1">Logout</a>
</center>
</body>
</html>
