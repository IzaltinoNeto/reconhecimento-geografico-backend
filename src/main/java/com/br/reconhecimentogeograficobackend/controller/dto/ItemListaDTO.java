package com.br.reconhecimentogeograficobackend.controller.dto;

import com.br.reconhecimentogeograficobackend.model.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

import java.util.ArrayList;

public class ItemListaDTO {
    private ItemLista.ItemListaId id;
    private ImovelDTO imovel;
    private ListaDeTrabalho lista;
    private ReconhecimentoGeografico reconhecimento;


    public ItemListaDTO() {
    }

    public ItemListaDTO(ItemLista itemLista) {
        this.id = itemLista.getId();
        this.imovel = new ImovelDTO(itemLista.getImovel());
        this.lista = itemLista.getLista();
        this.reconhecimento = itemLista.getReconhecimento();
    }

    public ReconhecimentoGeografico getReconhecimento() {
        return reconhecimento;
    }

    public void setReconhecimento(ReconhecimentoGeografico reconhecimento) {
        this.reconhecimento = reconhecimento;
    }

    public ItemLista build(){

        ItemLista itemLista = new ItemLista();
        itemLista.setId(this.id);
        itemLista.setImovel(this.imovel.build());
        itemLista.setLista(this.lista);
        itemLista.setReconhecimento(this.reconhecimento);

        return itemLista;
    }

    public ItemLista.ItemListaId getId() {
        return id;
    }

    public void setId(ItemLista.ItemListaId id) {
        this.id = id;
    }

    public ImovelDTO getImovel() {
        return imovel;
    }

    public void setImovel(ImovelDTO imovel) {
        this.imovel = imovel;
    }

    public ListaDeTrabalho getLista() {
        return lista;
    }

    public void setLista(ListaDeTrabalho lista) {
        this.lista = lista;
    }


    @Override
    public String toString() {
        return "ItemListaDTO{" +
                "id=" + id +
                ", imovel=" + imovel +
                ", lista=" + lista +
                ", reconhecimento=" + reconhecimento +
                '}';
    }
}
