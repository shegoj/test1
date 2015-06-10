<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServiceOPS - OPI</title>
    </head>
    <body>
<center>
<?php include("menu.php"); ?>
<b>Service OPS - 2015 OPI REPORT</b>
<br>
<?php include("report_total.php"); ?>
<br>

    <div class="datagrid"><table>
        <thead><tr>
          <th>APPLICATION</th>
          <th>AVERAGE EFFORT</th>
          <th>EFFORT VARIATION</th>
          <th>DISPERSAL</th>
          <th>OPI SCORE</th>
          <th>PAIN</th>
        </tr></thead>
	<?php echo $row ?>
	</table>
	</div>
<br>
<br>
<br>
<table>
    <tr>
    <th>Q1</th>
    <th>Q2</th>
    <th>Q3</th>
  </tr>
  <tr>
    <th align="center">
    <?php include("report_q1.php"); ?>
    <div class="datagrid2"><table>
        <thead><tr>
          <th>APPLICATION</th>
          <th>AVERAGE EFFORT</th>
          <th>EFFORT VARIATION</th>
          <th>DISPERSAL</th>
          <th>OPI SCORE</th>
          <th>PAIN</th>
        </tr></thead>
        <?php echo $q1 ?>
        </table>
        </div>
    </th>
    <th align="center">
    <?php include("report_q2.php"); ?>
    <div class="datagrid2"><table>
        <thead><tr>
          <th>APPLICATION</th>
          <th>AVERAGE EFFORT</th>
          <th>EFFORT VARIATION</th>
          <th>DISPERSAL</th>
          <th>OPI SCORE</th>
          <th>PAIN</th>
        </tr></thead>
        <?php echo $q2 ?>
        </table>
        </div>
    </th>
    <th align="center">
    <?php include("report_q3.php"); ?>
    <div class="datagrid2"><table>
        <thead><tr>
          <th>APPLICATION</th>
          <th>AVERAGE EFFORT</th>
          <th>EFFORT VARIATION</th>
          <th>DISPERSAL</th>
          <th>OPI SCORE</th>
          <th>PAIN</th>
        </tr></thead>
        <?php echo $q3 ?>
        </table>
        </div>
   </th>
  </tr>
</table>

</center>
</body>
</html>
