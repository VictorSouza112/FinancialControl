package br.com.fiap.financialcontrol.model;

public class Receita {

    private int codigoReceita;
    private int cdUsuario;
    private String nomeReceita;
    private double valorReceita;

    // Construtor padrão
    public Receita() {
    }

    // Construtor com todos os atributos
    public Receita(int codigoReceita, int cdUsuario, String nomeReceita, double valorReceita) {
        this.codigoReceita = codigoReceita;
        this.cdUsuario = cdUsuario;
        this.nomeReceita = nomeReceita;
        this.valorReceita = valorReceita;
    }

    // Construtor sem codigoReceita (para edição)
    public Receita(int codigoReceita, String nomeReceita, double valorReceita) {
        this.codigoReceita = codigoReceita;
        this.nomeReceita = nomeReceita;
        this.valorReceita = valorReceita;
    }

    // Getters e Setters
    public int getCodigoReceita() {
        return codigoReceita;
    }

    public void setCodigoReceita(int codigoReceita) {
        this.codigoReceita = codigoReceita;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }

    public double getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(double valorReceita) {
        this.valorReceita = valorReceita;
    }
}