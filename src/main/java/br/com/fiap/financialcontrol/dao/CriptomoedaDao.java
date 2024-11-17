package br.com.fiap.financialcontrol.dao;

import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.model.Criptomoeda;

import java.util.List;

public interface CriptomoedaDao {

    void cadastrarCriptomoeda(Criptomoeda criptomoeda) throws DBException;

    void atualizarCriptomoeda(Criptomoeda criptomoeda) throws DBException;

    void removerCriptomoeda(int codigoCriptomoeda) throws DBException;

    List<Criptomoeda> getAllCriptomoeda(int codigoUsuario);

}