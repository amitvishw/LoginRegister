<?php
	
	$servername = 'localhost';
	$username 	= 'root';
	$password 	= '';
	$dbname		= 'testlogin';
	$conn 		= new mysqli($servername, $username, $password,$dbname);
	if ($conn->connect_error) 
	{
    	die("Connection failed: " . $conn->connect_error);
	}

	$username	=$_POST['username'];
	$password	=$_POST['password'];

	$stmt 		= $conn->prepare("SELECT * FROM userDetails WHERE username=? AND password=?");
	$stmt		->bind_param("ss", $username,$password);
	$stmt		->execute();
	$stmt		->store_result();
	$stmt		->bind_result($name,$age,$username,$password); 
	$user 		= array();
	while ($stmt->fetch())
	{
		$user['name']	 =$name;
		$user['age']	 =$age;
		$user['username']=$username;
		$user['password']=$password;
	}
	echo json_encode($user);
	

	$stmt		->close();
	$conn 		->close();
?>
