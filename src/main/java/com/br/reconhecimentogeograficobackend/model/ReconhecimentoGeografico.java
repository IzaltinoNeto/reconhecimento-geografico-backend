package com.br.reconhecimentogeograficobackend.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.Calendar;

@Entity
public class ReconhecimentoGeografico extends AbstractEntity{

    private String titulo;
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createDateTime;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Object conteudo;
    @ManyToOne()
    private Formulario formulario;


    public ReconhecimentoGeografico() {
    }


    public ReconhecimentoGeografico(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return titulo;
    }

    public void setNome(String titulo) {
        this.titulo = titulo;
    }

    public ReconhecimentoGeografico(String titulo, Instant createDateTime, Object conteudo, Formulario formulario) {
        this.titulo = titulo;
        this.createDateTime = createDateTime;
        this.conteudo = conteudo;
        this.formulario = formulario;
    }

    public Object getConteudo() {
        return conteudo;
    }

    public void setConteudo(Object conteudo) {
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Instant getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Instant createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    @Override
    public String toString() {
        return "ReconhecimentoGeografico{" +
                "titulo='" + titulo + '\'' +
                ", createDateTime=" + createDateTime +
                ", conteudo=" + conteudo +
                ", formulario=" + formulario +
                '}';
    }
}
