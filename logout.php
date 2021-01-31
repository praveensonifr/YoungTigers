<?php require_once("include/session.php"); ?>

<?php

session_destroy();
  $response["success"] = 0;
        $response["message"] = "You are successfully logout";
        echo json_encode($response);
        exit();



?>