<?php

	session_save_path('/student/aksoyert/www/csc301Proj/sessions');
	session_start();	
	$dbconn = pg_connect("host=localhost dbname=aksoyert user=aksoyert password=4648000");
	if (!$dbconn){
		header('Location: errorpage.html');
		exit;
	}
	
	$query = "select * from tab_account where username='" . ($_POST['user']) . "' and passwd='" . (sha1($_POST['pass'])) . "';";
        $result = pg_query($query) or die ('Query failed:' . pg_last_error());
	
	$line = pg_fetch_row($result);

	if ($line == null)
	{	
		$_SESSION['error'] = 'Invalid username or password!';
		header('Location: errorpage.php');
		exit;
	}
	else if ($line != null)
	{
		$_SESSION['username'] = $_POST['user'];
		$_SESSION['password'] = $_POST['pass'];
		header('Location: mainpage.html');
	}

?>
