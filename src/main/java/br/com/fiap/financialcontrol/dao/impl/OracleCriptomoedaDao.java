package br.com.fiap.financialcontrol.dao.impl;

import br.com.fiap.financialcontrol.dao.CriptomoedaDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.ConnectionFactory;
import br.com.fiap.financialcontrol.model.Criptomoeda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleCriptomoedaDao implements CriptomoedaDao {

    private Connection conexao;

    @Override
    public void cadastrarCriptomoeda(Criptomoeda criptomoeda) throws DBException {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO T_FNC_CRIPTOMOEDAS (CD_USUARIO, NM_CRIPTOMOEDA, VL_CRIPTOMOEDA) VALUES (?, ?, ?)";

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, criptomoeda.getCdUsuario());
            stmt.setString(2, criptomoeda.getNomeCriptomoeda());
            stmt.setDouble(3, criptomoeda.getValorCriptomoeda());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar criptomoeda.", e);
        } finally {
            fecharConexao(stmt, conexao);
        }
    }

    @Override
    public List<Criptomoeda> getAllCriptomoeda(int codigoUsuario) {
        PreparedStatement stmt = null;
        List<Criptomoeda> criptomoedas = new ArrayList<>();
        ResultSet resultadoCriptomoeda = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT * FROM T_FNC_CRIPTOMOEDAS WHERE CD_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoUsuario);
            resultadoCriptomoeda = stmt.executeQuery();

            while (resultadoCriptomoeda.next()) {
                int codigoCriptomoeda = resultadoCriptomoeda.getInt("CD_CRIPTOMOEDA");
                String nomeCriptomoeda = resultadoCriptomoeda.getString("NM_CRIPTOMOEDA");
                double valorCriptomoeda = resultadoCriptomoeda.getDouble("VL_CRIPTOMOEDA");

                Criptomoeda criptomoeda = new Criptomoeda(codigoCriptomoeda, codigoUsuario, nomeCriptomoeda, valorCriptomoeda);
                criptomoedas.add(criptomoeda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(stmt, conexao);
        }
        return criptomoedas;
    }

    @Override
    public void atualizarCriptomoeda(Criptomoeda criptomoeda) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();

            String sql = "UPDATE T_FNC_CRIPTOMOEDAS SET NM_CRIPTOMOEDA = ?, VL_CRIPTOMOEDA = ? WHERE CD_CRIPTOMOEDA = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, criptomoeda.getNomeCriptomoeda());
            stmt.setDouble(2, criptomoeda.getValorCriptomoeda());
            stmt.setInt(3, criptomoeda.getCodigoCriptomoeda());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar criptomoeda.", e);
        } finally {
            fecharConexao(stmt, conexao);
        }
    }

    @Override
    public void removerCriptomoeda(int codigoCriptomoeda) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "DELETE FROM T_FNC_CRIPTOMOEDAS WHERE CD_CRIPTOMOEDA = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoCriptomoeda);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover criptomoeda.", e);
        } finally {
            fecharConexao(stmt, conexao);
        }
    }

    private void fecharConexao(PreparedStatement stmt, Connection conexao) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}