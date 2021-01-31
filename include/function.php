<?php require_once("include/db.php"); ?>

<?php

function redirect_to($url){

    header("location:$url");
    exit;


}

//login attempt if account found will login otherwise else part will run
function login_atempt($UserName,$Password){
    global  $db;
    $sql="SELECT * from users WHERE email=:email AND Pwd=:passWord LIMIT 1";
    $stmt= $db->prepare($sql);
    $stmt->bindValue(':email',$UserName);
    $stmt->bindValue(':passWord',$Password);
    $stmt->execute();
    $result= $stmt->rowcount();
    if($result==1){
        return $founduser=$stmt->fetch();
    }
    else
    {
        return null;
    }
}


?>