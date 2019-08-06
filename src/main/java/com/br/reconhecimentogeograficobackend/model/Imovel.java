package com.br.reconhecimentogeograficobackend.model;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Imovel extends AbstractEntity{


    @ManyToOne
    private Territorio territorio;
    @ManyToOne
    private TipoImovel tipoImovel;
    private String pontoEstrategico;
    private  String cep;
    private String logradouro;
    private String numero;
    private String sequencia;
    private String complemento;
    private  Polygon poligono;
    private  String numQuarteirao;
    private  String ladoQuarteirao;

    public Imovel() {
    }

    public Imovel(Territorio territorio, TipoImovel tipoImovel, String pontoEstrategico, String cep, String logradouro, String numero, String sequencia, String complemento, Polygon poligono, String numQuarteirao, String ladoQuarteirao) {
        this.territorio = territorio;
        this.tipoImovel = tipoImovel;
        this.pontoEstrategico = pontoEstrategico;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.sequencia = sequencia;
        this.complemento = complemento;
        this.poligono = poligono;
        this.numQuarteirao = numQuarteirao;
        this.ladoQuarteirao = ladoQuarteirao;
    }

    public Territorio getTerritorio() {
        return territorio;
    }

    public void setTerritorio(Territorio territorio) {
        this.territorio = territorio;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getPontoEstrategico() {
        return pontoEstrategico;
    }

    public void setPontoEstrategico(String pontoEstrategico) {
        this.pontoEstrategico = pontoEstrategico;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Polygon getPoligono() {
        return poligono;
    }

    public void setPoligono(Polygon poligono) {
        this.poligono = poligono;
    }

    public String getNumQuarteirao() {
        return numQuarteirao;
    }

    public void setNumQuarteirao(String numQuarteirao) {
        this.numQuarteirao = numQuarteirao;
    }

    public String getLadoQuarteirao() {
        return ladoQuarteirao;
    }

    public void setLadoQuarteirao(String ladoQuarteirao) {
        this.ladoQuarteirao = ladoQuarteirao;
    }
}
