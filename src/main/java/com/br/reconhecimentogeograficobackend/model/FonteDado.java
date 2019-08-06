package com.br.reconhecimentogeograficobackend.model;

import javax.persistence.Entity;

@Entity
public class FonteDado extends AbstractEntity{

    private String descricao;


    public FonteDado() {
    }


    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FonteDado(String descricao) {
        this.descricao = descricao;
    }
}
