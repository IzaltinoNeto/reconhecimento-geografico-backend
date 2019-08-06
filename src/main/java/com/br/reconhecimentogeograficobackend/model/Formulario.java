package com.br.reconhecimentogeograficobackend.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Formulario extends AbstractEntity {

    private String versao;

    private String titulo;

    private String descricao;


    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Object surveyJS;

    public Formulario() {
    }

    public Formulario(String versao, String titulo, String descricao, Object surveyJS) {
        this.versao = versao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.surveyJS = surveyJS;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }


    public Object getSurveyJS() {
        return surveyJS;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSurveyJS(Object surveyJS) {
        this.surveyJS = surveyJS;
    }

    @Override
    public String toString() {
        return "Formulario{" +
                "versao='" + versao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", surveyJS=" + surveyJS +
                '}';
    }
}
