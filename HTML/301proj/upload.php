<?php
	
	session_save_path('/student/aksoyert/www/csc301Proj/sessions/');	
	session_start();
	
	$username = $_SESSION['username'];
	$target_dir = "documents/";
	$target_file = $target_dir . basename($_FILES["img"]["name"]);
	$fileName = $_FILES['img']['name'];
	$uploadOk = 1;
	$fileType = pathinfo($fileName,PATHINFO_EXTENSION);
	
	// file uploading part
	if($fileType != "txt" && $fileType != "html" && $fileType != "pdf") 
	{
    	echo "Sorry, only txt, html & pdf files are allowed.";
    	$uploadOk = 0;
	}
	if ($uploadOk == 0)
	{
    	echo "Sorry, your file was not uploaded.";
		
	}
	if ($uploadOk == 1 && move_uploaded_file($_FILES["img"]["tmp_name"], $target_file)) 
	{
        echo "The file ". basename( $_FILES["img"]["name"]). " has been uploaded.";
    } 
	else 
	{
        echo "Sorry, there was an error uploading your file.";
    }
	chmod($target_file, 0777);

	$dbconn = pg_connect("host=localhost dbname=aksoyert user=aksoyert password=4648000");
	if (!$dbconn){
		
		$_SESSION['error'] = 'Cant connect to the database!';
		header('Location: errorpage.php');
		exit;
	}

	// file uploaded, so now we can upload to the database
	
	// get next document id primary key
	$find_pk_value = "select max(documentid) as max_documentid from tab_document";
	$pk_query = pg_query($find_pk_value) or die('Query failed:' . pg_last_error());

	$pk_line = pg_fetch_row($pk_query);

	$twhen = date("Y-m-d H:i:s");

	// do the actual insert
	$adding_query = "insert into tab_document (documentid, documentName, documentUser, documentFile, twhen) values (" . ($pk_line[0] + 1) . ", '" . ($target_file) . "', '" . ($username) . "', '" . ($target_file) . "', '" . ($twhen) . "');";
	$adding_result = pg_query($adding_query) or die('Query failed:' . pg_last_error());

	header('mainpage.html');
?>
