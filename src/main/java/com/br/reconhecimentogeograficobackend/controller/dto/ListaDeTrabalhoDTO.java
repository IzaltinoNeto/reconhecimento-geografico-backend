package com.br.reconhecimentogeograficobackend.controller.dto;

import com.br.reconhecimentogeograficobackend.model.ItemLista;
import com.br.reconhecimentogeograficobackend.model.ListaDeTrabalho;
import com.br.reconhecimentogeograficobackend.model.ReconhecimentoGeografico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListaDeTrabalhoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private ArrayList<ItemListaDTO> itens;


    public ListaDeTrabalhoDTO() {
    }

    public ListaDeTrabalhoDTO(Long id, String nome, String descricao, ArrayList<ItemListaDTO> itens) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.itens = itens;
    }

    public ListaDeTrabalhoDTO(ListaDeTrabalho lista){
        this.id = lista.getId();
        this.nome = lista.getNome();
        this.descricao = lista.getDescricao();
        List<ItemListaDTO> novoItens = lista.getItens().stream().
                map(item -> new ItemListaDTO(item)).collect(Collectors.toList());
        this.itens = (ArrayList<ItemListaDTO>) novoItens;
    }

    public ListaDeTrabalho build(){

        ListaDeTrabalho lista = new ListaDeTrabalho();
        lista.setId(this.id);
        lista.setNome(this.nome);
        lista.setDescricao(this.descricao);

        List<ItemLista> novoItens = this.itens.stream().
                                map(item -> item.build()).collect(Collectors.toList());

        lista.setItens((ArrayList<ItemLista>) novoItens);

        return lista;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<ItemListaDTO> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemListaDTO> itens) {
        this.itens = itens;
    }
}
