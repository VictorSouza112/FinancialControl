package br.com.fiap.financialcontrol.controller;

import br.com.fiap.financialcontrol.dao.HomeDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.DaoFactory;
import br.com.fiap.financialcontrol.model.Criptomoeda;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HomeServlet.class.getName());
    private HomeDao homeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            homeDao = DaoFactory.getHomeDAO();
            logger.log(Level.INFO, "Home DAO inicializado com sucesso.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao inicializar o Home DAO.", e);
            throw new ServletException("Erro ao inicializar o Home DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuarioVerificado = (Usuario) session.getAttribute("usuarioVerificado");

        if (usuarioVerificado == null) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            carregarDadosHome(req, usuarioVerificado);
            req.getRequestDispatcher("/resources/pages/Home.jsp").forward(req, resp);
        } catch (DBException e) {
            logger.log(Level.SEVERE, "Erro ao carregar os dados do home para o usuário: " + usuarioVerificado.getCodigoUsuario(), e);
            req.setAttribute("erro", "Erro ao carregar os dados do home.");
            req.getRequestDispatcher("/resources/pages/Home.jsp").forward(req, resp);
        }
    }

    private void carregarDadosHome(HttpServletRequest req, Usuario usuarioVerificado) throws DBException {
        try {
            // Busca as informações do usuário
            String nomeUsuario = homeDao.buscarNomeUsuario(usuarioVerificado.getCodigoUsuario());

            // Busca os criptomoedas do usuário
            List<Criptomoeda> criptomoedas = homeDao.buscarCriptomoedas(usuarioVerificado.getCodigoUsuario());
            double totalCriptomoeda = homeDao.buscarTotalCriptomoeda(usuarioVerificado.getCodigoUsuario());

            // Busca os receitas do usuário
            List<Receita> receitas = homeDao.buscarReceitas(usuarioVerificado.getCodigoUsuario());
            double totalReceita = homeDao.buscarTotalReceita(usuarioVerificado.getCodigoUsuario());

            // Adiciona os dados ao request
            req.setAttribute("usuarioVerificado", usuarioVerificado);
            req.setAttribute("nomeUsuario", nomeUsuario);
            req.setAttribute("criptomoedas", criptomoedas);
            req.setAttribute("totalCriptomoeda", totalCriptomoeda);
            req.setAttribute("receitas", receitas);
            req.setAttribute("totalReceita", totalReceita);

        } catch (DBException e) {
            logger.log(Level.SEVERE, "Erro ao carregar dados do home para o usuário: " + usuarioVerificado.getCodigoUsuario(), e);
            throw new DBException("Erro ao carregar dados do home para o usuário: " + usuarioVerificado.getCodigoUsuario(), e);
        }
    }
}