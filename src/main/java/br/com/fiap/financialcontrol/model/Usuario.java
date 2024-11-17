package br.com.fiap.financialcontrol.model;

import br.com.fiap.financialcontrol.util.CriptografiaUtils;

public class Usuario {

    private int codigoUsuario;
    private String nomeUsuario;
    private String telefone;
    private String email;
    private String senha;

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com todos os atributos
    public Usuario(int codigoUsuario, String nomeUsuario, String telefone, String email, String senha) {
        this.codigoUsuario = codigoUsuario;
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    // Construtor sem o código do usuário
    public Usuario(String nomeUsuario, String telefone, String email, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    // Construtor para validação
    public Usuario(String email, String senha) {
        super();
        this.email = email;
        setSenha(senha);
    }

    // Métodos getters e setters
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            this.senha = CriptografiaUtils.criptografar(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}