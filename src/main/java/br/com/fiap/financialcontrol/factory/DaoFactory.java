package br.com.fiap.financialcontrol.factory;

import br.com.fiap.financialcontrol.dao.*;
import br.com.fiap.financialcontrol.dao.impl.*;

public class DaoFactory {
    public static UsuarioDao getUsuarioDAO() {
        return new OracleUsuarioDao();
    }
    public static CriptomoedaDao getCriptomoedaDAO() {
        return new OracleCriptomoedaDao();
    }
    public static ReceitaDao getReceitaDAO() {
        return new OracleReceitaDao();
    }
    public static HomeDao getHomeDAO() {
        return new OracleHomeDao();
    }
}