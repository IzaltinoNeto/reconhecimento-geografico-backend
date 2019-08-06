package com.br.reconhecimentogeograficobackend.controller.dto;

import com.br.reconhecimentogeograficobackend.model.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

import java.util.ArrayList;

public class ImovelDTO implements ComPoligono{
    private Long id;
    private TerritorioDTO territorioDTO;
    private TipoImovel tipoImovel;
    private String pontoEstrategico;
    private  String cep;
    private String logradouro;
    private String numero;
    private String sequencia;
    private String complemento;
    private  String numQuarteirao;
    private  String ladoQuarteirao;
    private ArrayList<ArrayList> poligono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImovelDTO() {
    }

    public ImovelDTO(Imovel imovel) {
        System.out.println("Territorio"+imovel.getTerritorio());
        this.id = imovel.getId();
        this.territorioDTO = new TerritorioDTO(imovel.getTerritorio());
        this.tipoImovel = imovel.getTipoImovel();
        this.pontoEstrategico = imovel.getPontoEstrategico();
        this.logradouro = imovel.getLogradouro();
        this.numero = imovel.getNumero();
        this.sequencia = imovel.getSequencia();
        this.complemento = imovel.getComplemento();
        this.numQuarteirao = imovel.getNumQuarteirao();
        this.ladoQuarteirao = imovel.getLadoQuarteirao();
        this.cep = imovel.getCep();

        Coordinate[] coordenadas = imovel.getPoligono().getCoordinates();
        this.poligono = new ArrayList<ArrayList>();
        for(Coordinate coord : coordenadas){
            ArrayList elemento = new ArrayList();
            elemento.add(coord.x);
            elemento.add(coord.y);

            this.poligono.add(elemento);
        }

    }



    public Imovel build(){
        Imovel imovel = new Imovel();
        imovel.setId(this.id);
        imovel.setLogradouro(this.logradouro);
        imovel.setComplemento(this.complemento);
        imovel.setTerritorio(this.territorioDTO.build());
        imovel.setCep(this.cep);
        imovel.setLadoQuarteirao(this.ladoQuarteirao);
        imovel.setNumero(this.numero);
        imovel.setTipoImovel(this.tipoImovel);
        imovel.setPontoEstrategico(this.pontoEstrategico);
        imovel.setNumQuarteirao(this.numQuarteirao);
        imovel.setSequencia(this.sequencia);


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
        imovel.setPoligono(poligono);
        return imovel;
    }

    public ArrayList<ArrayList> getPoligono() {
        return poligono;
    }

    public void setPoligono(ArrayList<ArrayList> poligono) {
        this.poligono = poligono;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public TerritorioDTO getTerritorio() {
        return territorioDTO;
    }

    public void setTerritorio(TerritorioDTO territorio) {
        this.territorioDTO = territorio;
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
