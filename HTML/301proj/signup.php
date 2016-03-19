<?php
	
	
	session_save_path('/student/aksoyert/www/csc301Proj/sessions/');	
	session_start();
	
	$username = $_POST['user'];
	$password = $_POST['pass'];
	$firstName = $_POST['firstName'];
	$lastName = $_POST['lastName'];
	$email = $_POST['email'];
	$status = $_POST['status'];
	
	$dbconn = pg_connect("host=localhost dbname=aksoyert user=aksoyert password=4648000");
	if (!$dbconn){
		
		$_SESSION['error'] = 'Cant connect to the database!';
		header('Location: errorpage.php');
		exit;
	}

	// check if the username already exists
	$query = "select count(*) from (select * from tab_account where username='" . ($username) . "') as foo;";
	$result = pg_query($query) or die('Query failed:' . pg_last_error());
	
	$line = pg_fetch_row($result);
	
	if ($line[0] == 1)
	{
		$_SESSION['error'] = 'Username already exists!';
		header('Location: errorpage.php');
		exit;
	}

	// if everything is good, then add the user to our profile table

	// first need to find the next primary key value to put for the user
	$find_pk_value = "select max(accountid) as max_accountid from tab_account";
	$pk_query = pg_query($find_pk_value) or die('Query failed:' . pg_last_error());

	$pk_line = pg_fetch_row($pk_query);

	
	// then we can add it (the user) to the database
	$adding_query = "insert into tab_account (accountid, firstName, lastName, email, username, passwd, status) values (" . ($pk_line[0] + 1) . ", '" . ($firstName) . "', '" . ($lastName) . "', '" . ($email) . "', '" .  ($username) . "', '" . (sha1($password)) . "', '" . ($status) . "');";
	$adding_result = pg_query($adding_query) or die('Query failed:' . pg_last_error());


	$_SESSION['username'] = $username;
	$_SESSION['password'] = $password;

	header('Location: login.html');

	
?>

