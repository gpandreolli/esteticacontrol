package com.gpa.esteticacontrol.model;

import java.util.Date;

public class Cliente {

    private Long id;
    private String nome;
    private String sobreNome;
    private Date dtNasc;
    private String genero;
    private boolean indicacao;
    private String ondeEncontrou;

    public Cliente(Long id, String nome, String sobreNome, Date dtNasc, String genero, boolean indicacao, String ondeEncontrou) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dtNasc = dtNasc;
        this.genero = genero;
        this.indicacao = indicacao;
        this.ondeEncontrou = ondeEncontrou;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isIndicacao() {
        return indicacao;
    }

    public void setIndicacao(boolean indicacao) {
        this.indicacao = indicacao;
    }

    public String getOndeEncontrou() {
        return ondeEncontrou;
    }

    public void setOndeEncontrou(String ondeEncontrou) {
        this.ondeEncontrou = ondeEncontrou;
    }
}
