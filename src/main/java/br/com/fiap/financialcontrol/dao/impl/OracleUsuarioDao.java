package br.com.fiap.financialcontrol.dao.impl;

import br.com.fiap.financialcontrol.dao.UsuarioDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.ConnectionFactory;
import br.com.fiap.financialcontrol.model.Usuario;

import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleUsuarioDao implements UsuarioDao {

    private static final Logger logger = Logger.getLogger(OracleUsuarioDao.class.getName());
    private Connection conexao;

    @Override
    public void cadastrarUsuario(Usuario usuario) throws DBException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO T_FNC_USUARIO (NM_USUARIO, TELEFONE, EMAIL, SENHA) VALUES (?, ?, ?, ?)";

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar usuário.", e);
            throw new DBException("Erro ao cadastrar usuário.");
        } finally {
            try {
                Objects.requireNonNull(stmt).close();
                conexao.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao fechar recursos no método cadastrar.", e);
            }
        }
    }

    @Override
    public Usuario validarUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM T_FNC_USUARIO WHERE EMAIL = ? AND SENHA = ?";

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigoUsuario = rs.getInt("CD_USUARIO");
                String nomeUsuario = rs.getString("NM_USUARIO");
                String telefone = rs.getString("TELEFONE");
                String email = rs.getString("EMAIL");
                String senha = rs.getString("SENHA");

                return new Usuario(codigoUsuario, nomeUsuario, telefone, email, senha);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao validar usuário.", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao fechar recursos no método validarUsuario.", e);
            }
        }
        return null;
    }
}