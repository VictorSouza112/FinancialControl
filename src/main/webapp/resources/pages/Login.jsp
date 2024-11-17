<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Financial Control - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleLogin.css">
    <link rel="shortcut icon" type="imagex/png" href="../Imagens/Logo/miniatura.svg">
</head>

<body>
<div class="container">
    <div class="box-login">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/resources/Imagens/Landing_Page/logo.svg" alt="Logo Financial Control">
        </div>
        <h1>Faça seu login</h1>
        <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
            </div>
            <div class="form-group">
                <label for="senha">Senha:</label>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
                <i class="fa fa-eye" aria-hidden="true" id="show-senha"></i>
            </div>
            <button type="submit" class="btn btn-primary">Entrar</button>
            <p>Não tem uma conta? <a style="color: #4338CA" href="${pageContext.request.contextPath}/resources/pages/Cadastro_Usuario.jsp">Cadastre-se agora!</a></p>
        </form>
    </div>
</div>
</body>
</html>