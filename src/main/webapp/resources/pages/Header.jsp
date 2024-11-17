<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- começo header -->
<nav class="navbar fixed-top header-container">
  <div class="container-fluid header">
    <!-- conteúdo da esquerda -->
    <div class="left-side">
      <div class="logo">
        <img src="${pageContext.request.contextPath}/resources/Imagens/Landing_Page/logo.svg" alt="logo">
      </div>

      <div class="nav-item">
        <i class="bi bi-house"></i>
        <a class="nav-link" style="color: #FFFFFF;" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
      </div>

      <div class="nav-item ms-4">
        <i class="bi bi-coin"></i>
        <a class="nav-link" style="color: #FFFFFF;" href="${pageContext.request.contextPath}/criptomoedas">Criptomoedas</a>
      </div>

      <div class="nav-item ms-4">
        <i class="bi bi-cash-stack"></i>
        <a class="nav-link" style="color: #FFFFFF;" href="${pageContext.request.contextPath}/receitas">Receitas</a>
      </div>
    </div>
    <!-- fim conteúdo da esquerda -->

    <!-- conteúdo da direita -->
    <div class="right-side px-3">
        <div class="profile-icon mx-1 text-center">
          <img class="me-3" src="${pageContext.request.contextPath}/resources/Imagens/Header/header.svg">
          <h5>Perfil</h5>
        </div>
    </div>
    <!-- fim conteúdo da direita -->
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script></body>
<!-- fim header -->