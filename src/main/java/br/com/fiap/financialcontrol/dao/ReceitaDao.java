package br.com.fiap.financialcontrol.dao;

import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.model.Receita;

import java.util.List;

public interface ReceitaDao {

    void cadastrarReceita(Receita receita) throws DBException;

    void atualizarReceita(Receita receita) throws DBException;

    void removerReceita(int codigoReceita) throws DBException;

    List<Receita> getAllReceita(int codigoUsuario);

}