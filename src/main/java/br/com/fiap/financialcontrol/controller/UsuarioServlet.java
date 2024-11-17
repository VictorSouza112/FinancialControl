package br.com.fiap.financialcontrol.controller;

import br.com.fiap.financialcontrol.dao.UsuarioDao;
import br.com.fiap.financialcontrol.factory.DaoFactory;
import br.com.fiap.financialcontrol.model.Usuario;
import br.com.fiap.financialcontrol.util.CriptografiaUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDao dao;
    private static final Logger logger = Logger.getLogger(UsuarioServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DaoFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "validar":
                validar(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida.");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nomeUsuario = req.getParameter("nomeUsuario");
            String telefone = req.getParameter("telefone");
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String senhaCriptografada = CriptografiaUtils.criptografar(senha);

            Usuario usuario = new Usuario(nomeUsuario, telefone, email, senhaCriptografada);
            dao.cadastrarUsuario(usuario);

            logger.info("Usuário cadastrado com sucesso!");
            req.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
            req.getRequestDispatcher("/resources/pages/Login.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar usuário", e);
            req.setAttribute("erro", "Erro ao cadastrar usuário. Por favor, valide os dados.");
            req.getRequestDispatcher("/resources/pages/Cadastro_Usuario.jsp").forward(req, resp);
        }
    }

    private void validar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            Usuario usuario = new Usuario(email, senha);
            Usuario usuarioVerificado = dao.validarUsuario(usuario);
            req.getSession().setAttribute("usuarioVerificado", usuarioVerificado);

            if (usuarioVerificado != null) {
                req.setAttribute("mensagem", "Usuário válido.");
            } else {
                req.setAttribute("erro", "Usuário inválido.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao validar usuário", e);
            req.setAttribute("erro", "Erro ao validar usuário.");
        }
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/pages/Login.jsp").forward(req, resp);
    }
}