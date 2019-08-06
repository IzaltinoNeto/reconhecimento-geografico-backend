package com.br.reconhecimentogeograficobackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemLista implements Serializable {
    @EmbeddedId
    private ItemListaId id;


    @ManyToOne()
    @JoinColumn(name = "imovel_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Imovel imovel;

    @ManyToOne()
    @JoinColumn(name = "lista_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ListaDeTrabalho lista;

    @OneToOne(cascade = CascadeType.ALL)
    private ReconhecimentoGeografico reconhecimento;

    public ItemLista() {
    this.id = new ItemListaId(null, null);
    }

    public ItemLista(Imovel imovel, ListaDeTrabalho lista, ReconhecimentoGeografico reconhecimento) {
        this.imovel = imovel;
        this.lista = lista;
        this.reconhecimento = reconhecimento;
    }

    public ItemListaId getId() {
        return id;
    }

    public void setId(ItemListaId id) {
        this.id = id;
    }

    public ReconhecimentoGeografico getReconhecimento() {
        return reconhecimento;
    }

    public void setReconhecimento(ReconhecimentoGeografico reconhecimento) {
        this.reconhecimento = reconhecimento;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public ListaDeTrabalho getLista() {
        return lista;
    }

    public void setLista(ListaDeTrabalho lista) {
        this.lista = lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemLista itemLista = (ItemLista) o;
        return Objects.equals(id, itemLista.id) &&
                Objects.equals(imovel, itemLista.imovel) &&
                Objects.equals(lista, itemLista.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imovel, lista);
    }

    @Embeddable
    public static class ItemListaId implements Serializable {

        @Column(name = "imovel_id")
        protected Long imovelId;

        @Column(name = "lista_id")
        protected Long listaId;

        public ItemListaId() {

        }

        public ItemListaId(Long imovelId, Long listaId) {
            this.imovelId = imovelId;
            this.listaId = listaId;

        }


        public Long getImovelId() {
            return imovelId;
        }

        public void setImovelId(Long imovelId) {
            this.imovelId = imovelId;
        }

        public Long getListaId() {
            return listaId;
        }

        public void setListaId(Long listaId) {
            this.listaId = listaId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemListaId that = (ItemListaId) o;
            return Objects.equals(imovelId, that.imovelId) &&
                    Objects.equals(listaId, that.listaId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(imovelId, listaId);
        }
    }

    @Override
    public String toString() {
        return "ItemLista{" +
                "id=" + id +
                ", imovel=" + imovel +
                ", lista=" + lista +
                ", reconhecimento=" + reconhecimento +
                '}';
    }
}
