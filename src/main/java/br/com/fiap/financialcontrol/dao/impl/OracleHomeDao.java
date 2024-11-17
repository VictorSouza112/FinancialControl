package br.com.fiap.financialcontrol.dao.impl;

import br.com.fiap.financialcontrol.dao.HomeDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.ConnectionFactory;
import br.com.fiap.financialcontrol.model.Receita;
import br.com.fiap.financialcontrol.model.Criptomoeda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleHomeDao implements HomeDao {

    private static final Logger logger = Logger.getLogger(OracleHomeDao.class.getName());

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public String buscarNomeUsuario(int codigoUsuario) throws DBException {
        String sql = "SELECT NM_USUARIO FROM T_FNC_USUARIO WHERE CD_USUARIO = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("NM_USUARIO");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar nome do usuário. SQL: " + sql, e);
            throw new DBException("Erro ao buscar nome do usuário.");
        }
        return null;
    }

    // Métodos para recuperar informações de criptomoeda
    @Override
    public List<Criptomoeda> buscarCriptomoedas(int codigoUsuario) throws DBException {
        String sql = "SELECT * FROM T_FNC_CRIPTOMOEDAS WHERE CD_USUARIO = ?";
        List<Criptomoeda> criptomoedas = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Criptomoeda criptomoeda = new Criptomoeda(
                            rs.getInt("CD_CRIPTOMOEDA"),
                            rs.getInt("CD_USUARIO"),
                            rs.getString("NM_CRIPTOMOEDA"),
                            rs.getDouble("VL_CRIPTOMOEDA")
                    );
                    criptomoedas.add(criptomoeda);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar criptomoedas. SQL: " + sql, e);
            throw new DBException("Erro ao buscar criptomoedas.");
        }
        return criptomoedas;
    }

    @Override
    public double buscarTotalCriptomoeda(int codigoUsuario) throws DBException {
        String sql = "SELECT NVL(SUM(VL_CRIPTOMOEDA), 0) AS TOTAL_CRIPTOMOEDA FROM T_FNC_CRIPTOMOEDAS WHERE CD_USUARIO = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TOTAL_CRIPTOMOEDA");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar total criptomoeda. SQL: " + sql, e);
            throw new DBException("Erro ao buscar total criptomoeda.");
        }
        return 0;
    }

    // Métodos para recuperar informações de receitas
    @Override
    public List<Receita> buscarReceitas(int codigoUsuario) throws DBException {
        String sql = "SELECT * FROM T_FNC_RECEITAS WHERE CD_USUARIO = ?";
        List<Receita> receitas = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Receita receita = new Receita(
                            rs.getInt("CD_RECEITA"),
                            rs.getInt("CD_USUARIO"),
                            rs.getString("NM_RECEITA"),
                            rs.getDouble("VL_RECEITA")
                    );
                    receitas.add(receita);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar receitas. SQL: " + sql, e);
            throw new DBException("Erro ao buscar receitas.");
        }
        return receitas;
    }

    @Override
    public double buscarTotalReceita(int codigoUsuario) throws DBException {
        String sql = "SELECT NVL(SUM(VL_RECEITA), 0) AS TOTAL_RECEITA FROM T_FNC_RECEITAS WHERE CD_USUARIO = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TOTAL_RECEITA");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar total receita. SQL: " + sql, e);
            throw new DBException("Erro ao buscar total receita.");
        }
        return 0;
    }
}