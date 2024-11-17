package br.com.fiap.financialcontrol.dao.impl;

import br.com.fiap.financialcontrol.dao.ReceitaDao;
import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.factory.ConnectionFactory;
import br.com.fiap.financialcontrol.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleReceitaDao implements ReceitaDao {

    private Connection conexao;

    @Override
    public void cadastrarReceita(Receita receita) throws DBException {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO T_FNC_RECEITAS (CD_USUARIO, NM_RECEITA, VL_RECEITA) VALUES (?, ?, ?)";

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, receita.getCdUsuario());
            stmt.setString(2, receita.getNomeReceita());
            stmt.setDouble(3, receita.getValorReceita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar receita.", e);
        } finally {
            fecharConexao(stmt, conexao);
        }
    }

    @Override
    public List<Receita> getAllReceita(int codigoUsuario) {
        PreparedStatement stmt = null;
        List<Receita> receitas = new ArrayList<>();
        ResultSet resultadoReceita = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT * FROM T_FNC_RECEITAS WHERE CD_USUARIO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoUsuario);
            resultadoReceita = stmt.executeQuery();

            while (resultadoReceita.next()) {
                int codigoReceita = resultadoReceita.getInt("CD_RECEITA");
                String nomeReceita = resultadoReceita.getString("NM_RECEITA");
                double valorReceita = resultadoReceita.getDouble("VL_RECEITA");

                Receita receita = new Receita(codigoReceita, codigoUsuario, nomeReceita, valorReceita);
                receitas.add(receita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(stmt, conexao);
        }
        return receitas;
    }

    @Override
    public void atualizarReceita(Receita receita) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();

            String sql = "UPDATE T_FNC_RECEITAS SET NM_RECEITA = ?, VL_RECEITA = ? WHERE CD_RECEITA = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, receita.getNomeReceita());
            stmt.setDouble(2, receita.getValorReceita());
            stmt.setInt(3, receita.getCodigoReceita());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar receita.", e);
        } finally {
            fecharConexao(stmt, conexao);
        }
    }

    @Override
    public void removerReceita(int codigoReceita) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "DELETE FROM T_FNC_RECEITAS WHERE CD_RECEITA = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoReceita);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover receita.", e);
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