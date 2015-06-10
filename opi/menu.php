<nav id="menu-wrap">    
	<ul id="menu">
		<li><a href="index.php">Home</a></li>

		<li>
			<a href="dashboard.php">Dashboard</a>
		</li>
		<li>
			<a href="">Feed</a>
			<ul>

				<li>
					<a href="feed_opi.php">OPI</a>
				</li>
				<li>
					<a href="feed_requests.php">Requests</a>
				</li>
			</ul>		
		</li>
		<li><a href="login.php">Login</a></li>
		<li><img style="margin-top:-2px; float:right" src="images/tr_logo.png" height="40px" width="136px"></li>
	</ul>
</nav>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript">
    $(function() {
		if ($.browser.msie && $.browser.version.substr(0,1)<7)
		{
		$('li').has('ul').mouseover(function(){
			$(this).children('ul').css('visibility','visible');
			}).mouseout(function(){
			$(this).children('ul').css('visibility','hidden');
			})
		}

		/* Mobile */
		$('#menu-wrap').prepend('<div id="menu-trigger">Menu</div>');		
		$("#menu-trigger").on("click", function(){
			$("#menu").slideToggle();
		});

		// iPad
		var isiPad = navigator.userAgent.match(/iPad/i) != null;
		if (isiPad) $('#menu ul').addClass('no-transition');      
    });          
</script>
