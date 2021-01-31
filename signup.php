<?php require_once("include/db.php"); ?>
<?php require_once("include/function.php"); ?>
<?php require_once("include/session.php"); ?>
<?php

//check already existing of email
function checkemail($email) {
    global $db;
    $sql = "SELECT * FROM users WHERE email='$email' LIMIT 1";
    $stmt= $db->query($sql);
    $result=$stmt->rowcount();
    if ($result >0) {

      return true;

    }

    return false;
}
//in json format (from array) we will get data from sql
$response = array();

//to get post request from anroid UI
if (isset($_POST['fname'],$_POST['email'],$_POST['age'],$_POST['password'])) {
    $fullname =$_POST['fname'];
    $email = $_POST['email'];
    $age = $_POST['age'];
    $address = $_POST['add'];
    $phone = $_POST['phone'];
    $password = $_POST['password'];
    if(empty($_POST['fname'])) {
  
        $response["success"] = 0;
        $response["message"] = "fullname required.";
        echo json_encode($response);
   
    }

     elseif (empty($_POST['age'])) {

        $response["success"] = 0;
        $response["message"] = "Age required";
        echo json_encode($response);
    }

    elseif (empty($_POST['add'])) {
        
        $response["success"] = 0;
        $response["message"] = "Address required";
        echo json_encode($response);
    }

    elseif (empty($_POST['email'])) {
    
        $response["success"] = 0;
        $response["message"] = "Email required";
        echo json_encode($response);
    }

    elseif (empty($_POST['phone'])) {
        
        $response["success"] = 0;
        $response["message"] = "Phone number required";
        echo json_encode($response);

    }
    elseif (empty($_POST['password'])) {
  
        $response["success"] = 0;
        $response["message"] = "Password required";
        echo json_encode($response);
    }
    elseif (isset($_POST['password']) && $_POST['password'] !== $_POST['passwordConf']) {
      //   $_SESSION["errormsg"] = 'The two passwords do not match';
        $response["success"] = 0;
        $response["message"] = "The two passwords do not match";
        echo json_encode($response);
    }

    else if(checkemail($email)) {
        
        $response["success"] = 0;
        $response["message"] = "Email already exists";
        echo json_encode($response);
   

  }

  else {
    
    
  
  //data insertion into table
        $sql = "INSERT INTO users(FullName,Age,Email,Phone,Adress,Pwd)";
        $sql .= "VALUES(:FullNamer,:Age,:Email,:Phone,:Adress,:Pwd)";
        $stmt = $db ->prepare($sql);
        $stmt->bindValue(':FullNamer',$fullname);
        $stmt->bindValue(':Age',$age);
        $stmt->bindValue(':Email',$email);
        $stmt->bindValue(':Phone',$phone);
        $stmt->bindValue(':Adress',$address);
        $stmt->bindValue(':Pwd',$password);
        $result=$stmt->execute();
        if ($result) {
        
        $response["success"] = 1;
        $response["message"] = "You are succsefully register";

        echo json_encode($response);
          

        } else {
             
        $response["success"] = 0;
        $response["message"] = "Database error: Could not register user";
        echo json_encode($response);

        }
    }

}


?>