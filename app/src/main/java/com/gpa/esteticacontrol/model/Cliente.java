package com.gpa.esteticacontrol.model;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Cliente {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String sobreNome;
    @NonNull
    private Date dtNasc;
    @NonNull
    private String genero;
    private boolean indicacao;
    private String ondeEncontrou;

    public Cliente() {
    }

//    public Cliente(Long id, String nome, String sobreNome, Date dtNasc, String genero, boolean indicacao, String ondeEncontrou) {
//        this.id = id;
//        this.nome = nome;
//        this.sobreNome = sobreNome;
//        this.dtNasc = dtNasc;
//        this.genero = genero;
//        this.indicacao = indicacao;
//        this.ondeEncontrou = ondeEncontrou;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(@NonNull String sobreNome) {
        this.sobreNome = sobreNome;
    }

    @NonNull
    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(@NonNull Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    @NonNull
    public String getGenero() {
        return genero;
    }

    public void setGenero(@NonNull String genero) {
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
