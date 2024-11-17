<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Financial Control - Cadastro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleCadastro_Usuario.css">
    <link rel="shortcut icon" type="imagex/png" href="../Imagens/Logo/miniatura.svg">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 box-cadastro">
            <h1>Faça seu login</h1>
            <form action="${pageContext.request.contextPath}/usuarios?acao=cadastrar" method="post">
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nomeUsuario" placeholder="Digite seu nome" required>
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input type="text" class="form-control" id="telefone" name="telefone" placeholder="(99) 99999-9999" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="exemplo@dominio.com" required>
                </div>
                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
                    <i class="fa fa-eye" aria-hidden="true" id="show-senha"></i>
                </div>
                <div class="form-group">
                    <label for="confirmarSenha">Confirmar Senha:</label>
                    <input type="password" class="form-control" id="confirmarSenha" name="confirmarSenha" placeholder="Digite sua senha novamente" required>
                    <i class="fa fa-eye" aria-hidden="true" id="show-confirmarSenha"></i>
                </div>
                <button type="submit" class="btn btn-primary">Criar conta</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>