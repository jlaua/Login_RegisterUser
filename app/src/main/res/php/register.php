<?php
    $con=mysqli_connect("localhost","i947285_mood1","310783Jla","i947285_mood1");

    $nombre = $_POST["Nombre"];
    $username = $_POST["Username"];
    $password = $_POST["Password"];
    $email = $_POST["Email"];

    $statement = mysqli_prepare($con, "INSERT INTO User (Nombre, Email, Username, Password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $nombre, $email, $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_close($statement);

    mysqli_close($con);
?>