package br.com.fiap.financialcontrol.dao;

import br.com.fiap.financialcontrol.exception.DBException;
import br.com.fiap.financialcontrol.model.Usuario;

public interface UsuarioDao {

    void cadastrarUsuario(Usuario usuario) throws DBException;
    Usuario validarUsuario(Usuario usuario);
}