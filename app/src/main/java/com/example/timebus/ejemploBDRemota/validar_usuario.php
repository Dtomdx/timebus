<?php
include 'conexion1.php';

$dni=$_POST['dni'];
$contraseUsuario=$_POST['password'];
//$dni="70776127";
//$contraseUsuario="12345";



$sentencia=$conexion->prepare("SELECT * FROM usuarioscolaborador WHERE dni=? AND contraseUsuario=?");
$sentencia->bind_param('ss',$dni,$contraseUsuario);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}

$sentencia->close();
$conexion->close();

?>