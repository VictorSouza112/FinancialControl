package br.com.fiap.financialcontrol.dao;

import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.model.Criptomoeda;
import br.com.fiap.financialcontrol.model.Receita;

import java.util.List;

public interface HomeDao {
    // Métodos para recuperar informações do usuário
    String buscarNomeUsuario(int codigoUsuario) throws DBException; // Corrigido para codigoUsuario

    // Métodos para recuperar informações de criptomoedas
    List<Criptomoeda> buscarCriptomoedas(int codigoUsuario) throws DBException; // Corrigido para codigoUsuario
    double buscarTotalCriptomoeda(int codigoUsuario) throws DBException; // Corrigido para codigoUsuario

    // Métodos para recuperar informações de receitas
    List<Receita> buscarReceitas(int codigoUsuario) throws DBException; // Corrigido para codigoUsuario
    double buscarTotalReceita(int codigoUsuario) throws DBException; // Corrigido para codigoUsuario
}