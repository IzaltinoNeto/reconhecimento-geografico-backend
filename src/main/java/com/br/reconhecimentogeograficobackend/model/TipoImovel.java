package com.br.reconhecimentogeograficobackend.model;

import javax.persistence.Entity;

@Entity
public class TipoImovel extends AbstractEntity{

    private String nome;


    public TipoImovel() {
    }


    public TipoImovel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
