package com.br.reconhecimentogeograficobackend.controller.dto;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import com.br.reconhecimentogeograficobackend.model.FonteDado;
import com.br.reconhecimentogeograficobackend.model.Imovel;
import com.br.reconhecimentogeograficobackend.model.Territorio;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerritorioDTOMonitoramento implements ComPoligono{
    private Long id;
    private String codigo;
    private FonteDado fonteDado;
    private String sigla;
    private String nome;
    private Categoria categoria;
    private TerritorioDTOMonitoramento territorioPai;
    private String cep;
    private String zona;
    private String croqui;
    private ArrayList<ArrayList> poligono;
    private String status;
    private String altitude;
    private ArrayList<ComPoligono> filhos;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TerritorioDTOMonitoramento() {
    }

    public ArrayList<ComPoligono> getFilhos() {
        return filhos;
    }

    public void setFilhosImoveis(List<Imovel> filhos) {

        this.filhos.addAll((ArrayList<ComPoligono>) filhos.stream().map( imovel -> (ComPoligono) new ImovelDTO(imovel)).collect(Collectors.toList()));

    }

    public void setFilhosTerritorios(List<Territorio> filhos) {
        ArrayList<ComPoligono> novosFilhos= (ArrayList<ComPoligono>) filhos.stream().map(territorio-> (ComPoligono) new TerritorioDTOMonitoramento(territorio)).collect(Collectors.toList());
        this.filhos.addAll(novosFilhos);
    }

    public static TerritorioDTOMonitoramento convert(ComPoligono pol){
        return (TerritorioDTOMonitoramento) pol;
    }

    public TerritorioDTOMonitoramento(Territorio territorio) {
        this.id = territorio.getId();
        this.codigo = territorio.getCodigo();
        this.fonteDado = territorio.getFonteDado();
        this.sigla = territorio.getSigla();
        this.nome = territorio.getNome();
        this.categoria = territorio.getCategoria();
        if(territorio.getTerritorioPai() != null)
            this.territorioPai = new TerritorioDTOMonitoramento(territorio.getTerritorioPai());
        this.cep = territorio.getCep();
        this.zona = territorio.getZona();
        this.croqui = territorio.getCroqui();
        this.status = territorio.getStatus();
        this.altitude = territorio.getAltitude();
        this.filhos = new ArrayList<ComPoligono>();
        Coordinate[] coordenadas = territorio.getPoligono().getCoordinates();
        this.poligono = new ArrayList<ArrayList>();
        for(Coordinate coord : coordenadas){
            ArrayList elemento = new ArrayList();
            elemento.add(coord.x);
            elemento.add(coord.y);

            this.poligono.add(elemento);
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Territorio build(){
        Territorio territorio = new Territorio();
        territorio.setId(this.id);
        territorio.setCodigo(this.codigo);
        territorio.setFonteDado(this.fonteDado);
        territorio.setSigla(this.sigla);
        territorio.setNome(this.getNome());
        territorio.setCategoria(this.getCategoria());
        if(this.getTerritorioPai()!= null)
            territorio.setTerritorioPai(this.getTerritorioPai().build());
        territorio.setCep(this.getCep());
        territorio.setZona(this.getZona());
        territorio.setStatus(this.getStatus());
        territorio.setCroqui(this.getCroqui());
        territorio.setAltitude(this.getAltitude());

        GeometryFactory gf = new GeometryFactory();
        //Polygon poligono = gf.createPolygon();
        Coordinate[] coordenadas = new Coordinate[getPoligono().size()];
        int index = 0;
        for (ArrayList pol : getPoligono()) {
            Coordinate coord = new Coordinate(Double.valueOf(pol.get(0).toString()), Double.valueOf(pol.get(1).toString()));
            coordenadas[index] = coord;
            index++;
        }

        Polygon poligono = gf.createPolygon(coordenadas);
        territorio.setPoligono(poligono);
        return territorio;
    }

    public ArrayList<ArrayList> getPoligono() {
        return poligono;
    }

    public void setPoligono(ArrayList<ArrayList> poligono) {
        this.poligono = poligono;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TerritorioDTOMonitoramento getTerritorioPai() {
        return territorioPai;
    }

    public void setTerritorioPai(TerritorioDTOMonitoramento territorioPai) {
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
}
