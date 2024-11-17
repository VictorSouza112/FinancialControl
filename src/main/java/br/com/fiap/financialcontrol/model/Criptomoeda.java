package br.com.fiap.financialcontrol.model;

public class Criptomoeda {

    private int codigoCriptomoeda;
    private int cdUsuario;
    private String nomeCriptomoeda;
    private double valorCriptomoeda;

    // Construtor padrão
    public Criptomoeda() {
    }

    // Construtor com todos os atributos
    public Criptomoeda(int codigoCriptomoeda, int cdUsuario, String nomeCriptomoeda, double valorCriptomoeda) {
        this.codigoCriptomoeda = codigoCriptomoeda;
        this.cdUsuario = cdUsuario;
        this.nomeCriptomoeda = nomeCriptomoeda;
        this.valorCriptomoeda = valorCriptomoeda;
    }

    // Construtor sem codigoUsuario (para edição)
    public Criptomoeda(int codigoCriptomoeda, String nomeCriptomoeda, double valorCriptomoeda) {
        this.codigoCriptomoeda = codigoCriptomoeda;
        this.nomeCriptomoeda = nomeCriptomoeda;
        this.valorCriptomoeda = valorCriptomoeda;
    }

    // Getters e Setters
    public int getCodigoCriptomoeda() {
        return codigoCriptomoeda;
    }

    public void setCodigoCriptomoeda(int codigoCriptomoeda) {
        this.codigoCriptomoeda = codigoCriptomoeda;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNomeCriptomoeda() {
        return nomeCriptomoeda;
    }

    public void setNomeCriptomoeda(String nomeCriptomoeda) {
        this.nomeCriptomoeda = nomeCriptomoeda;
    }

    public double getValorCriptomoeda() {
        return valorCriptomoeda;
    }

    public void setValorCriptomoeda(double valorCriptomoeda) {
        this.valorCriptomoeda = valorCriptomoeda;
    }
}