<?php
    $con=mysqli_connect("localhost","i947285_mood1","310783Jla","i947285_mood1");

    $username = $_POST["Username"];
    $password = $_POST["Password"];

    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE Username = ? AND Password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $nombre, $email, $username, $password);

    $user = array();

    while(mysqli_stmt_fetch($statement)){
        $user["Nombre"] = $nombre;
        $user["Email"] = $email;
        $user["Username"] = $username;
        $user["Password"] = $password;
    }
    
    echo json_encode($user);
    mysqli_close($con);
?>