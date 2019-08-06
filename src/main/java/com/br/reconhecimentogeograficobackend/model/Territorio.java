package com.br.reconhecimentogeograficobackend.model;

import com.vividsolutions.jts.geom.Polygon;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Territorio extends AbstractEntity{



    private String codigo;
    @ManyToOne
    private FonteDado fonteDado;
    private String nome;
    private String sigla;
    @ManyToOne
    private  Categoria categoria;
    @ManyToOne
    private  Territorio territorioPai;
    private  String cep;
    private  String zona;
    private  String croqui;
    private  Polygon poligono;
    private  String status;
    private  String altitude;

    public Territorio() {
    }

    public Territorio(String codigo, FonteDado fonteDado, String nome, String sigla, Categoria categoria, Territorio territorioPai, String cep, String zona, String croqui, Polygon poligono, String status, String altitude) {
        this.codigo = codigo;
        this.fonteDado = fonteDado;
        this.nome = nome;
        this.sigla = sigla;
        this.categoria = categoria;
        this.territorioPai = territorioPai;
        this.cep = cep;
        this.zona = zona;
        this.croqui = croqui;
        this.poligono = poligono;
        this.status = status;
        this.altitude = altitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public FonteDado getFonteDado() {
        return fonteDado;
    }

    public void setFonteDado(FonteDado fonteDado) {
        this.fonteDado = fonteDado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCroqui() {
        return croqui;
    }

    public void setCroqui(String croqui) {
        this.croqui = croqui;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Territorio getTerritorioPai() {
        return territorioPai;
    }

    public void setTerritorioPai(Territorio territorioPai) {
        this.territorioPai = territorioPai;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }




    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public Polygon getPoligono() {
        return poligono;
    }

    public void setPoligono(Polygon poligono) {
        this.poligono = poligono;
    }


}
