<?php require_once("include/db.php"); ?>
<?php require_once("include/function.php"); ?>
<?php require_once("include/session.php"); ?>
<?php

$response = array();
 

$sql = "SELECT * FROM hotels";
$smt = $db->query($sql);
$count = $smt->rowCount();
if($count > 0) {
    
    $response["hotals"] = array();
 
    while ($row = $smt->fetch()) {
    
        $hotal = array();
        $hotal["HotelID"] = $row["HotelID"];
        $hotal["HotelName"] = $row["HotelName"];
        $hotal["HotelLoc"] = $row["HotelLoc"];
        $hotal["NoRooms"] = $row["NoRooms"];
        $hotal["Price"] = $row["Price"];
        $hotal["DateFrom"] = $row["DateFrom"];
        $hotal["DateTo"] = $row["DateTo"];
        $hotal["wifi"] = $row["wifi"];
        $hotal["Breakfast"] = $row["Breakfast"];
        $hotal["ACNonAC"] = $row["ACNonAC"];
        $hotal["RoomAvailability"] = $row["RoomAvailability"];
 

        array_push($response["hotals"], $hotal);
    }

    $response["success"] = 1;
    echo json_encode($response);
} else {

    $response["success"] = 0;
    $response["message"] = "No products found";
    echo json_encode($response);
}


?>