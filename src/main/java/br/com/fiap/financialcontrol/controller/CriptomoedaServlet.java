package br.com.fiap.financialcontrol.controller;

import br.com.fiap.financialcontrol.dao.CriptomoedaDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.DaoFactory;
import br.com.fiap.financialcontrol.model.Criptomoeda;
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

@WebServlet("/criptomoedas")
public class CriptomoedaServlet extends HttpServlet {

    private CriptomoedaDao criptomoedaDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        criptomoedaDao = DaoFactory.getCriptomoedaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acaoCriptomoeda = req.getParameter("acaoCriptomoeda");

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioVerificado");

        if (usuario == null) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        switch (acaoCriptomoeda) {
            case "cadastrar":
                cadastrarCriptomoeda(req, resp, usuario.getCodigoUsuario());
                break;
            case "editar":
                editarCriptomoeda(req, resp);
                break;
            case "excluir":
                excluirCriptomoeda(req, resp);
                break;
        }
    }

    private void cadastrarCriptomoeda(HttpServletRequest req, HttpServletResponse resp, int codigoUsuario)
            throws ServletException, IOException {
        try {
            String nomeCriptomoeda = req.getParameter("nomeCriptomoeda");
            double valorCriptomoeda = Double.parseDouble(req.getParameter("valorCriptomoeda"));

            Criptomoeda criptomoeda = new Criptomoeda(0, codigoUsuario, nomeCriptomoeda, valorCriptomoeda);
            criptomoedaDao.cadastrarCriptomoeda(criptomoeda);

            req.setAttribute("mensagem", "Criptomoeda cadastrado com sucesso!");
            List<Criptomoeda> criptomoedas = criptomoedaDao.getAllCriptomoeda(codigoUsuario);
            req.setAttribute("criptomoedas", criptomoedas);

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar criptomoeda.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Valor inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar criptomoeda.");
        }
        req.getRequestDispatcher("/resources/pages/Criptomoeda.jsp").forward(req, resp);
    }

    private void editarCriptomoeda(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoCriptomoeda = Integer.parseInt(req.getParameter("codigoCriptomoeda"));
            String nomeCriptomoeda = req.getParameter("nomeCriptomoeda");
            double valorCriptomoeda = Double.parseDouble(req.getParameter("valorCriptomoeda"));

            Criptomoeda criptomoeda = new Criptomoeda(codigoCriptomoeda, nomeCriptomoeda, valorCriptomoeda);
            criptomoedaDao.atualizarCriptomoeda(criptomoeda);

            req.setAttribute("mensagem", "Criptomoeda atualizado com sucesso!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar criptomoeda.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Valor inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar criptomoeda.");
        }
        listarCriptomoedas(req, resp);
    }

    private void excluirCriptomoeda(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int codigoCriptomoeda = Integer.parseInt(req.getParameter("codigoExcluir"));
            criptomoedaDao.removerCriptomoeda(codigoCriptomoeda);
            req.setAttribute("mensagem", "Criptomoeda removido com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover criptomoeda.");
        }
        listarCriptomoedas(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        listarCriptomoedas(req, resp);
    }

    private void listarCriptomoedas(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Obtem a sessão sem criá-la

        if (session == null || session.getAttribute("usuarioVerificado") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioVerificado"); // Recupera o objeto Usuario da sessão

        List<Criptomoeda> criptomoedas = criptomoedaDao.getAllCriptomoeda(usuario.getCodigoUsuario());
        req.setAttribute("criptomoedas", criptomoedas);
        req.getRequestDispatcher("/resources/pages/Criptomoeda.jsp").forward(req, resp);
    }
}