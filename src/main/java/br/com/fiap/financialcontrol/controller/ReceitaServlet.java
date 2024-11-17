package br.com.fiap.financialcontrol.controller;

import br.com.fiap.financialcontrol.dao.ReceitaDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.DaoFactory;
import br.com.fiap.financialcontrol.model.Receita;
import br.com.fiap.financialcontrol.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/receitas")
public class ReceitaServlet extends HttpServlet {

    private ReceitaDao receitaDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        receitaDao = DaoFactory.getReceitaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acaoReceita = req.getParameter("acaoReceita");

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioVerificado");

        if (usuario == null) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        switch (acaoReceita) {
            case "cadastrar":
                cadastrarReceita(req, resp, usuario.getCodigoUsuario());
                break;
            case "editar":
                editarReceita(req, resp);
                break;
            case "excluir":
                excluirReceita(req, resp);
                break;
        }
    }

    private void cadastrarReceita(HttpServletRequest req, HttpServletResponse resp, int codigoUsuario)
            throws ServletException, IOException {
        try {
            String nomeReceita = req.getParameter("nomeReceita");
            double valorReceita = Double.parseDouble(req.getParameter("valorReceita"));

            Receita receita = new Receita(0, codigoUsuario, nomeReceita, valorReceita);
            receitaDao.cadastrarReceita(receita);

            req.setAttribute("mensagem", "Receita cadastrada com sucesso!");
            List<Receita> receitas = receitaDao.getAllReceita(codigoUsuario);
            req.setAttribute("receitas", receitas);

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar receita.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Valor inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar receita.");
        }
        req.getRequestDispatcher("/resources/pages/Receita.jsp").forward(req, resp);
    }

    private void editarReceita(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoReceita = Integer.parseInt(req.getParameter("codigoReceita"));
            String nomeReceita = req.getParameter("nomeReceita");
            double valorReceita = Double.parseDouble(req.getParameter("valorReceita"));

            Receita receita = new Receita(codigoReceita, nomeReceita, valorReceita);
            receitaDao.atualizarReceita(receita);

            req.setAttribute("mensagem", "Receita atualizada com sucesso!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar receita.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Valor inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar receita.");
        }
        listarReceitas(req, resp);
    }

    private void excluirReceita(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int codigoReceita = Integer.parseInt(req.getParameter("codigoExcluir"));
            receitaDao.removerReceita(codigoReceita);
            req.setAttribute("mensagem", "Receita removida com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover receita.");
        }
        listarReceitas(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        listarReceitas(req, resp);
    }

    private void listarReceitas(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Obtem a sessão sem criá-la

        if (session == null || session.getAttribute("usuarioVerificado") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioVerificado"); // Recupera o objeto Usuario da sessão

        List<Receita> receitas = receitaDao.getAllReceita(usuario.getCodigoUsuario());
        req.setAttribute("receitas", receitas);
        req.getRequestDispatcher("/resources/pages/Receita.jsp").forward(req, resp);
    }
}