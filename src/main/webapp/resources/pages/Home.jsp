<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home</title>

  <%@include file="Links_Header.jsp"%>

  <!-- CSS da página de criptomoedas -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleHome.css">

  <!-- css do header e do footer -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
  <link rel="shortcut icon" type="imagex/png" href="../Imagens/Logo/miniatura.svg">
</head>
<body>
<%@include file="Header.jsp"%>

<main class="container">

  <section class="home">

    <div class="home-user">
      <div class="home-user-text">
        <h2>Olá, <span class="user-name">${usuarioVerificado.nomeUsuario}</span></h2>
        <p>Seu saldo: R$ <span class="saldo">${totalReceita - totalCriptomoeda}</span></p>
        <p>Total inserido: R$ <span class="total-inserido">${totalReceita}</span></p>
      </div>
      <div class="home-user-image">
        <img src="${pageContext.request.contextPath}/resources/Imagens/Home/robot.svg" alt="Robô">
      </div>
    </div>

    <div class="home-crypto">
      <h2>Em alta</h2>
      <table>
        <thead>
        <tr>
          <th>Criptomoeda</th>
          <th>Valor</th>
          <th>Variação</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td><img src="${pageContext.request.contextPath}/resources/Imagens/Home/btc.svg" alt="BTC"> BTC</td>
          <td>$85.338,14</td>
          <td class="variacao-crypto">+4%</td>
        </tr>
        <tr>
          <td><img src="${pageContext.request.contextPath}/resources/Imagens/Home/eth.svg" alt="ETH"> ETH</td>
          <td>$3.226,73</td>
          <td class="variacao-crypto">+2%</td>
        </tr>
        <tr>
          <td><img src="${pageContext.request.contextPath}/resources/Imagens/Home/usdt.svg" alt="USDT"> USDT</td>
          <td>$1,00</td>
          <td class="variacao-crypto">+20%</td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="home-criptomoedas">
      <h2>Criptomoedas</h2>
      <div class="info-criptomoedas">
        <p>Valor criptomoeda = R$ <span class="valor-total-criptomoeda">${totalCriptomoeda}</span></p>
        <a href="${pageContext.request.contextPath}/criptomoedas" class="btn btn-primary">Acesse</a>
      </div>
      <table>
        <thead>
        <tr>
          <th>Criptomoeda</th>
          <th>Valor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="criptomoeda" items="${criptomoedas}">
          <tr>
            <td>${criptomoeda.nomeCriptomoeda}</td>
            <td>R$ <span class="valor-criptomoeda">${criptomoeda.valorCriptomoeda}</span></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="home-receitas">
      <h2>Receitas</h2>
      <div class="info-receitas">
        <p>Dinheiro inserido = R$ <span class="valor-total-receitas">${totalReceita}</span></p>
        <a href="${pageContext.request.contextPath}/receitas" class="btn btn-primary">Acesse</a>
      </div>
      <table>
        <thead>
        <tr>
          <th>Fonte</th>
          <th>Valor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="receita" items="${receitas}">
          <tr>
            <td>${receita.nomeReceita}</td>
            <td>R$ <span class="valor-receita">${receita.valorReceita}</span></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="home-sair">
      <a href="${pageContext.request.contextPath}/resources/pages/Login.jsp" class="btn btn-primary">Sair</a>
    </div>

  </section>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

</body>
</html>