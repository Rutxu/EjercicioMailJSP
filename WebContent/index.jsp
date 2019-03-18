<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<h1 style="color: blue">Inicio Sesión</h1>
	<form method="post" action="login">
		<div class="container">
			<label for="uname"><b>Nombre de Usuario</b></label> 
			<br>
			<input type="text" placeholder="Nombre de usuario" name="uname" required> 
			<br>
			<label for="psw"><b>Contraseña</b></label> 
			<br>
			<input type="password" placeholder="Contraseña" name="psw" required>
			<br>
			<button type="submit">Iniciar</button>
		</div>
	</form>

	<h1 style="color: blue">Registrarse</h1>	
	<form method="post" action="signup">
		<div class="container">
			<label for="uname"><b>Nombre de Usuario</b></label> 
			<br>
			<input type="text" placeholder="Nombre de usuario" name="uname" required> 
			<br>
			<label for="email"><b>Email</b></label> 
			<br>
			<input type="email" placeholder="Email de contacto" name="email" required> 
			<br>
			<label for="psw"><b>Contraseña</b></label> 
			<br>
			<input type="password" placeholder="Contraseña" name="psw" required>
			<br>
			<button type="submit">Registrar</button>
		</div>
	</form>

</body>
</html>