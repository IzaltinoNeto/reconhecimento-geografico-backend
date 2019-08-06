package com.br.reconhecimentogeograficobackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ListaDeTrabalho extends AbstractEntity{

    private String nome;
    private String descricao;
    @JsonIgnore
    @OneToMany(mappedBy = "lista", fetch = FetchType.EAGER)
    private List<ItemLista> itens = new ArrayList<>();


    public ListaDeTrabalho() {
    }

    public ListaDeTrabalho(String nome, String descricao, ArrayList<ItemLista> itens) {
        this.nome = nome;
        this.descricao = descricao;
        this.itens = itens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ItemLista> getItens() {
        return  itens;
    }

    public void setItens(ArrayList<ItemLista> itens) {
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
