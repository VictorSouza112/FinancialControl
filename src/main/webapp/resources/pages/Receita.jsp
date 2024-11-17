<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receitas</title>

    <%@include file="Links_Header.jsp"%>

    <!-- CSS da página de receitas -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleReceita.css">

    <!-- css do header e do footer -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
    <link rel="shortcut icon" type="imagex/png" href="../Imagens/Logo/miniatura.svg">
</head>
<body>
<%@include file="Header.jsp"%>

<!-- começo conteúdo -->
<!-- botão - adicionar receita -->
<button type="button" class="btn adicionar p-2 px-5" data-bs-toggle="modal" data-bs-target="#exampleModal">
    <img src="${pageContext.request.contextPath}/resources/Imagens/Receita/receita.svg">
    Adicionar nova receita
</button>
<!-- fim botão - adicionar receita -->

<!-- modal - adicionar receita -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title" id="exampleModalLabel">Receitas</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="linha-modal"></div>
            <!-- formulário modal -->
            <form action="${pageContext.request.contextPath}/receitas?acaoReceita=cadastrar" method="post">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="nomeReceita" id="floatingNomeReceita" required>
                            <option selected disabled>Selecione a fonte da receita</option>
                            <option value="Salário">Salário</option>
                            <option value="Investimentos">Investimentos</option>
                            <option value="Aluguéis">Aluguéis</option>
                            <option value="Freelancing">Freelancing</option>
                            <option value="Vendas">Vendas</option>
                            <option value="Restituições">Restituições</option>
                            <option value="Outros">Outros</option>
                        </select>
                        <label for="floatingNomeReceita">Nome da receita</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="valorReceita" id="floatingInput" placeholder="R$00,00" required>
                        <label for="floatingInput">Valor</label>
                    </div>
                </div>
                <div class="linha-modal"></div>
                <!-- botões modal -->
                <div class="modal-footer">
                    <button type="button" class="btn fechar" data-bs-dismiss="modal">Fechar</button>
                    <button type="submit" class="btn salvar">Salvar receita</button>
                </div>
            </form>
            <!-- fim formulário modal -->
        </div>
    </div>
</div>
<!-- fim modal - adicionar receita -->

<!-- tabela de receitas -->
<table>
    <!-- colunas -->
    <thead class="text-center">
    <tr>
        <th>
            <div class="colunas">
                Nome
            </div>
        </th>
        <th>
            <div class="colunas">
                Valor
            </div>
        </th>
        <th>
            <div class="colunas">
                #
            </div>
        </th>
    </tr>
    </thead>
    <!-- fim colunas -->

    <!-- conteúdo de exemplo -->
    <tbody class="text-center body">
    <tr>
        <td data-label="LinhaTabela Nome">Nome da receita</td>
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
    <c:forEach items="${receitas}" var="receita">
        <tr>
            <td data-label="Nome">${receita.nomeReceita}</td>
            <td data-label="Valor"> <fmt:formatNumber value="${receita.valorReceita}"/></td>
            <td data-label="#" class="funções">
                <button type="button" class="btn editar" data-bs-toggle="modal" data-bs-target="#editarModal"
                        onclick="codigoReceitaEditar.value = '${receita.codigoReceita}';
                                nomeReceitaEditar.value = '${receita.nomeReceita}';
                                valorReceitaEditar.value = '${receita.valorReceita}';">
                    <i class="bi bi-pencil-square"></i> Editar
                </button>
                <button type="button" class="btn excluir" data-bs-toggle="modal"
                        data-bs-target="#excluirModal"
                        onclick="codigoExcluir.value = ${receita.codigoReceita}">
                    <i class="bi bi-trash3-fill"></i> Excluir
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <!-- fim conteúdo de exemplo -->
</table>

<!-- Modal - Editar Receita -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title" id="editarReceitaModalLabel">Editar receita</h1>
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
            <form action="${pageContext.request.contextPath}/receitas?acaoReceita=editar" method="post">
                <input type="hidden" name="codigoReceita" id="codigoReceitaEditar">
                <div class="modal-body">
                    <div class="form-floating mb-3">
                        <select class="form-select" name="nomeReceita" id="nomeReceitaEditar" required>
                            <option selected disabled>Selecione a fonte do receita</option>
                            <option value="Salário">Salário</option>
                            <option value="Investimentos">Investimentos</option>
                            <option value="Aluguéis">Aluguéis</option>
                            <option value="Freelancing">Freelancing</option>
                            <option value="Vendas">Vendas</option>
                            <option value="Restituições">Restituições</option>
                            <option value="Outros">Outros</option>
                        </select>
                        <label for="nomeReceitaEditar">Nome da receita</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="valorReceita" id="valorReceitaEditar" placeholder="R$00,00" required>
                        <label for="valorReceitaEditar">Valor</label>
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
                <h4>Você confirma a exclusão desta receita?</h4>
                <p><strong>Atenção!</strong> Esta ação é irreversível.</p>
            </div>
            <div class="modal-footer">
                <form action="${pageContext.request.contextPath}/receitas?acaoReceita=excluir" method="post">
                    <input
                            type="hidden"
                            name="acao"
                            value="excluirReceita">
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
        crossorigin="anonymous"></script>

</body>
</html>