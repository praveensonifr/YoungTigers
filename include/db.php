<?php
// db connection 
    try{
    	
    $db = new pdo('mysql:host = localhost; dbname=api','root','',array(
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    ));
    }catch(PDOException $pe){
        echo $pe->getMessage();
    }  


?>