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


	$name		=$_POST['name'];
	$age		=$_POST['age'];
	$username	=$_POST['username'];
	$password	=$_POST['password'];

	$stmt 		= $conn->prepare("INSERT INTO userDetails (name,age,username,password) VALUES (?, ?, ?,?)");
	$stmt		->bind_param("siss", $name,$age,$username,$password);
	$stmt		->execute();
	
	$stmt		->close();
	$conn 		->close();
?>
