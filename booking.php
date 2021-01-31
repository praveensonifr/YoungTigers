<?php require_once("include/db.php"); ?>
<?php require_once("include/function.php"); ?>
<?php require_once("include/session.php"); ?>


<?php



function CheckIfHotalExist($hid,$user_id){
    global $db;
    $sql = "SELECT * FROM  booking WHERE UserID = :user AND HotelID =:hid";
    $stmt = $db->prepare($sql);
    $stmt->bindValue('user',$user_id);
    $stmt->bindValue(':hid',$hid);
    $result = $stmt->execute();
    $count = $stmt->rowCount();

    if($count>0){
     
     return true;

    }

    return false;
    
}


function CheckHotal($hid){
    global $db;
    $sql = "SELECT * FROM  hotels WHERE HotelID =:hid";
    $stmt = $db->prepare($sql);
    $stmt->bindValue(':hid',$hid);
     $stmt->execute();
    $count = $stmt->rowCount();

    if($count>0){
     
     return true;

    }

    return false;
    
}
 

$response = array();


if(isset($_GET["hid"])) {

        $hid = $_GET['hid'];
        
        if(!isset($_SESSION['id'])){

        $response["success"] = 0;
        $response["message"] = "You must login to book hotals";
        echo json_encode($response);
        exit();


        }
        $user_id = $_SESSION['id'];
      

        if(!CheckHotal($hid)){

        $response["success"] = 0;
        $response["message"] = "Invalid hotals";
        echo json_encode($response);


        }

        elseif(CheckIfHotalExist($hid,$user_id)){

        $response["success"] = 0;
        $response["message"] = "You already booked this hotals";
        echo json_encode($response);


        }

        else {
        date_default_timezone_set('Asia/Calcutta');
        $Fdate = date("Y-m-d H:i:s");
        $guest = '1';
        $sql = "INSERT INTO booking(UserID,BDateFrom,HotelID,NoOfGuest)";
        $sql .= "VALUES(:user_id,:Fdate,:hotel_id,:guest)";
        $stmt = $db->prepare($sql);
        $stmt->bindValue(':user_id',$user_id);
        $stmt->bindValue(':Fdate',$Fdate);
        $stmt->bindValue(':hotel_id',$hid);
        $stmt->bindValue(':guest',$guest);
        $result=$stmt->execute();
        if ($result) {
        
        $response["success"] = 1;
        $response["message"] = "This hotal has been  succssefully booked";

        echo json_encode($response);
          

        } else {
             
        $response["success"] = 0;
        $response["message"] = "Database error: Could not make booking";
        echo json_encode($response);

        }

     }

     }   



        ?>