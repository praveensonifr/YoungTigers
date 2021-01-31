<?php require_once("include/db.php"); ?>
<?php require_once("include/function.php"); ?>
<?php require_once("include/session.php"); ?>
<?php

if(isset($_SESSION["id"])){


        $response["success"] = 0;
        $response["message"] = "You are already login";
        echo json_encode($response);
        exit();

  }

if(isset($_POST["Username"],$_POST["Password"])){
     $email = $_POST["Username"];
     $Password = $_POST["Password"];
  if (empty($email)||empty($Password)) {

        $response["success"] = 0;
        $response["message"] = "All fields must be filled out";

        echo json_encode($response);
  }else {
      
     $founduser=login_atempt($email,$Password);
      if($founduser){

      $_SESSION["id"] = $founduser["user_id"];
      $_SESSION["FullName"]=$founduser["FullName"];
      $_SESSION["Email"]=$founduser["Email"];

        $response["success"] = 1;
        $response["message"] = "Welecome ".$_SESSION["FullName"];

        echo json_encode($response);

      }
      else{

        $response["success"] = 0;
        $response["message"] = "Incorrect Password/Username";

        echo json_encode($response);
   
          
          
      }
      
  }
    
}

    
  



?>
    
