<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Criptomoedas</title>

    <%@include file="Links_Header.jsp"%>

    <!-- CSS da página de criptomoedas -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleCriptomoeda.css">

    <!-- css do header -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="shortcut icon" type="imagex/png" href="../Imagens/Logo/miniatura.svg">
</head>
<body>
<%@include file="Header.jsp"%>

<!-- começo conteúdo -->
<!-- botão - adicionar criptomoeda -->
<button type="button" class="btn adicionar p-2 px-5" data-bs-toggle="modal" data-bs-target="#exampleModal">
    <img src="${pageContext.request.contextPath}/resources/Imagens/Criptomoeda/criptomoeda.svg">
    Adicionar novo criptomoeda
</button>
<!-- fim botão - adicionar criptomoeda -->

<!-- modal - adicionar criptomoeda -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title" id="exampleModalLabel">Criptomoedas</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="linha-modal"></div>
            <!-- formulário modal -->
            <form action="${pageContext.request.contextPath}/criptomoedas?acaoCriptomoeda=cadastrar" method="post">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="nomeCriptomoeda" id="floatingNomeCriptomoeda" required>
                            <option selected disabled>Selecione uma Criptomoeda</option>
                            <option value="Bitcoin">Bitcoin (BTC)</option>
                            <option value="Ethereum">Ethereum (ETH)</option>
                            <option value="Tether">Tether (USDT)</option>
                            <option value="Binance Coin">Binance Coin (BNB)</option>
                            <option value="XRP">XRP (XRP)</option>
                            <option value="Cardano">Cardano (ADA)</option>
                            <option value="Solana">Solana (SOL)</option>
                            <option value="Dogecoin">Dogecoin (DOGE)</option>
                            <option value="Polygon">Polygon (MATIC)</option>
                            <option value="Chainlink">Chainlink (LINK)</option>
                        </select>
                        <label for="floatingNomeCriptomoeda">Nome da Criptomoeda</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="valorCriptomoeda" id="floatingInput" placeholder="R$00,00" required>
                        <label for="floatingInput">Valor</label>
                    </div>
                </div>
                <div class="linha-modal"></div>
                <!-- botões modal -->
                <div class="modal-footer">
                    <button type="button" class="btn fechar" data-bs-dismiss="modal">Fechar</button>
                    <button type="submit" class="btn salvar">Salvar criptomoeda</button>
                </div>
            </form>
            <!-- fim formulário modal -->
        </div>
    </div>
</div>
<!-- fim modal - adicionar criptomoeda -->

<!-- tabela de criptomoedas -->
<table>
    <!-- colunas -->
    <thead class="text-center">
    <tr>
        <th>
            <div>
                Nome
            </div>
        </th>
        <th>
            <div>
                Valor
            </div>
        </th>
        <th>
            <div>
                #
            </div>
        </th>
    </tr>
    </thead>
    <!-- fim colunas -->

    <!-- conteúdo de exemplo -->
    <tbody class="text-center body">
    <tr>
        <td data-label="LinhaTabela Nome">Nome da moeda</td>
        <td data-label="LinhaTabela Valor">R$ 00,00</td>
        <td data-label="LinhaTabela #" class="funções">
            <button type="button" class="btn editar">
                <i class="bi bi-pencil-square"></i> Editar
            </button>
            <button type="button" class="btn excluir">
                <i class="bi bi-trash3-fill"></i> Excluir
            </button>
        </td>
    </tr>
    <c:forEach items="${criptomoedas}" var="criptomoeda">
        <tr>
            <td data-label="Nome">${criptomoeda.nomeCriptomoeda}</td>
            <td data-label="Valor"> <fmt:formatNumber value="${criptomoeda.valorCriptomoeda}"/></td>
            <td data-label="#" class="funções">
                <button type="button" class="btn editar" data-bs-toggle="modal" data-bs-target="#editarModal"
                        onclick="codigoEditar.value = '${criptomoeda.codigoCriptomoeda}';
                                nomeCriptomoedaEditar.value = '${criptomoeda.nomeCriptomoeda}';
                                valorCriptomoedaEditar.value = '${criptomoeda.valorCriptomoeda}';">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button type="button" class="btn excluir" data-bs-toggle="modal"
                        data-bs-target="#excluirModal"
                        onclick="codigoExcluir.value = ${criptomoeda.codigoCriptomoeda}">
                    <i class="bi bi-trash3-fill"></i> Excluir
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <!-- fim conteúdo de exemplo -->
</table>

<!-- Modal - Editar Criptomoeda -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title" id="editarModalLabel">Editar Criptomoeda</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="linha-modal"></div>
            <c:choose>
                <c:when test="${not empty mensagem}">
                    <div class="alert alert-success ms-2 me-2 m-auto mt-2">
                            ${mensagem}
                    </div>
                </c:when>
                <c:when test="${not empty erro}">
                    <div class="alert alert-danger ms-2 me-2 m-auto mt-2">
                            ${erro}
                    </div>
                </c:when>
            </c:choose>
            <form action="${pageContext.request.contextPath}/criptomoedas?acaoCriptomoeda=editar" method="post">
                <input type="hidden" name="codigoCriptomoeda" id="codigoEditar">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="nomeCriptomoeda" id="nomeCriptomoedaEditar" required>
                            <option selected disabled>Selecione uma Criptomoeda</option>
                            <option value="Bitcoin">Bitcoin (BTC)</option>
                            <option value="Ethereum">Ethereum (ETH)</option>
                            <option value="Tether">Tether (USDT)</option>
                            <option value="Binance Coin">Binance Coin (BNB)</option>
                            <option value="XRP">XRP (XRP)</option>
                            <option value="Cardano">Cardano (ADA)</option>
                            <option value="Solana">Solana (SOL)</option>
                            <option value="Dogecoin">Dogecoin (DOGE)</option>
                            <option value="Polygon">Polygon (MATIC)</option>
                            <option value="Chainlink">Chainlink (LINK)</option>
                        </select>
                        <label for="nomeCriptomoedaEditar">Nome da Criptomoeda</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="valorCriptomoeda" id="valorCriptomoedaEditar" placeholder="R$00,00" required>
                        <label for="valorCriptomoedaEditar">Valor</label>
                    </div>
                </div>
                <div class="linha-modal"></div>
                <div class="modal-footer">
                    <button type="button" class="btn fechar" data-bs-dismiss="modal">Fechar</button>
                    <button type="submit" value="Salvar" class="btn salvar">Salvar alterações</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal excluir -->
<div
        class="modal fade"
        id="excluirModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1
                        class="modal-title fs-5"
                        id="exampleModalLabel2">
                    Confirmar Exclusão
                </h1>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <div class="modal-body">
                <h4>Você confirma a exclusão deste criptomoeda?</h4>
                <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
            </div>
            <div class="modal-footer">
                <form action="${pageContext.request.contextPath}/criptomoedas?acaoCriptomoeda=excluir" method="post">
                    <input
                            type="hidden"
                            name="acao"
                            value="excluirCriptomoeda">
                    <input
                            type="hidden"
                            name="codigoExcluir"
                            id="codigoExcluir">
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">
                        Não
                    </button>
                    <button
                            type="submit"
                            class="btn btn-danger">
                        Sim
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- fim modal-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script></body>
</html>