<?php
	$server = "localhost";
	$user = "root";
	$password = "";
    $bd = "empresastransporte";

    $conexion = mysqli_connect($server, $user, $password, $bd);
	if (!$conexion){ 
		die('Error de Conexión: ' . mysqli_connect_errno());	
	}

	$ciuDest1 = $_GET['ciuDest1'];
	
	//$query = "SELECT * FROM terminal";
	//$query = "SELECT nomTerm, direccionTerm, ciuDest1 , imgTerm from empresa inner join terminal on empresa.idTerm = terminal.idTerm where ciuDest1 = '$ciuDest1' ";
	$query = "SELECT * from empresa inner join terminal on empresa.idTerm = terminal.idTerm where ciuDest1 = '$ciuDest1' ";
	$resultado = mysqli_query($conexion, $query);
	
	while($row = mysqli_fetch_assoc($resultado)){
            $datos[] = array_map('utf8_encode', $row);
    }
    mysqli_close($conexion);

    $json_string = json_encode($datos, JSON_PRETTY_PRINT);

    echo $json_string;
    




?>