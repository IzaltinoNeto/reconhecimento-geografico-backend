package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.controller.dto.ComPoligono;
import com.br.reconhecimentogeograficobackend.controller.dto.TerritorioDTO;
import com.br.reconhecimentogeograficobackend.controller.dto.TerritorioDTOMonitoramento;
import com.br.reconhecimentogeograficobackend.model.Territorio;
import com.br.reconhecimentogeograficobackend.repository.ImovelRepository;
import com.br.reconhecimentogeograficobackend.repository.TerritorioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("territorio")
@Api(value = "Territorio")
public class TerritorioController {
    private final TerritorioRepository dao;
    private final ImovelRepository daoImovel;

    @Autowired
    public TerritorioController(TerritorioRepository dao, ImovelRepository daoImovel){
        this.dao = dao;
        this.daoImovel = daoImovel;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Iterable<Territorio> territorios = dao.findAll();
        ArrayList<TerritorioDTO> territoriosDTO = new ArrayList();
        for(Territorio territorio : territorios){
            TerritorioDTO loc = new TerritorioDTO(territorio);
            territoriosDTO.add(loc);
        }
        return new ResponseEntity<>(territoriosDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        //Territorio loc = (Territorio) ;
        Territorio loc = dao.findById(id).get();
        System.out.println("territorio get: "+loc.toString());
        TerritorioDTO territorioDTO = new TerritorioDTO(loc);
        return new ResponseEntity<>(territorioDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TerritorioDTO territorioDTO){

        Territorio territorio= territorioDTO.build();
        dao.save(territorio);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody TerritorioDTO territorioDTO){
        Territorio territorio = territorioDTO.build();

        Territorio territorioPai =this.recuperarPretendenteLocalizacaoPai(territorio.getTerritorioPai());

        if(possuiAutoRelacionamento(territorio, territorioPai)){
            return ResponseEntity.badRequest().body("Um registro não pode ter um relacionamento consigo mesmo");
        }
        dao.save(territorio);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/byName")
    public ResponseEntity<?> getByName(@PathParam("nome") String nome){

        Iterable<Territorio> territorios = dao.findByNomeIgnoreCaseContainingOrderByPoligonoDesc(nome);
        ArrayList<TerritorioDTO> territoriosDTO = new ArrayList();
        for(Territorio territorio : territorios){
            TerritorioDTO loc = new TerritorioDTO(territorio);
            territoriosDTO.add(loc);
        }
        return new ResponseEntity<>(territoriosDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/byNameWithPagination")
    public ResponseEntity<?> getByNameWithPagination(
        @PathParam("nome") String nome,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "100") Integer size,
        @RequestParam(value ="territorioPaiId", required = false) Long territorioPaiId){

        Page<Territorio> territorios;

        if(territorioPaiId == null){
            territorios = dao.findByNomeIgnoreCaseContaining(nome, PageRequest.of(page, size));
        } else {
            territorios = dao.findByNomeContainingIgnoreCaseAndTerritorioPaiId(nome, territorioPaiId, PageRequest.of(page, size));
        }

        //System.out.println("TerritoriosDTOS-> "+territorios.map(TerritorioDTO::new));


        return new ResponseEntity<>(territorios.map(TerritorioDTO::new), HttpStatus.OK);
    }
    @GetMapping(path = "/byTerritorioPai")
    public ResponseEntity<?> getByTerritorioPai(@PathParam("territorioPaiId") Long territorioPaiId,
                                                @PathParam("territorioId") Long territorioId){

        Iterable<Territorio> territorios = dao.findByTerritorioPaiIdAndIdNot(territorioPaiId, territorioId);
        ArrayList<TerritorioDTO> territoriosDTO = new ArrayList();
        for(Territorio territorio : territorios){
            TerritorioDTO loc = new TerritorioDTO(territorio);
            territoriosDTO.add(loc);
        }
        return new ResponseEntity<>(territoriosDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/getHierarchy")
    public ResponseEntity<?> getAllHierarchy(){
        //traz a lista de territorios
        ArrayList<Territorio> lista = (ArrayList<Territorio>) dao.findByTerritorioPaiIdIsNull();
        //Converte para o formato DTO
        ArrayList<TerritorioDTOMonitoramento> lista1 =
          (ArrayList<TerritorioDTOMonitoramento>) lista.stream()
                  .map(TerritorioDTOMonitoramento::new).collect(Collectors.toList());
        lista1 = buscarFilhos( (ArrayList<ComPoligono>) lista1.stream().map(territorio-> (ComPoligono) territorio).collect(Collectors.toList()));


        return new ResponseEntity<>(lista1, HttpStatus.OK);
    }

    private ArrayList<TerritorioDTOMonitoramento> buscarFilhos(ArrayList<ComPoligono> lista1){
        //birutisse
        ArrayList<TerritorioDTOMonitoramento> lista2 = ( ArrayList<TerritorioDTOMonitoramento>) lista1.stream()
                .map(territorio -> TerritorioDTOMonitoramento.convert(territorio)).collect(Collectors.toList());

        //busca os territorios de cada territorio
        lista2 =  (ArrayList<TerritorioDTOMonitoramento>) lista2.stream().
                map(territorio -> {
                            territorio.setFilhosTerritorios(dao.findByTerritorioPaiId(territorio.getId()));
                            //recursividade
                            buscarFilhos(territorio.getFilhos());
                            return territorio;
                        }
                )
                .collect(Collectors.toList());
        //busca os Imoveis filhos de cada territorio
        lista2 =  (ArrayList<TerritorioDTOMonitoramento>) lista2.stream().
                map(territorio -> {
                            territorio.setFilhosImoveis(daoImovel.findByTerritorioId(territorio.getId()));
                            return territorio;
                        }
                )
                .collect(Collectors.toList());


        return lista2;
    }


    /**
     * Ao utilizar o hibernate para recuperar o pai, recuperamos toda a cadeia superior.
     * Assim esse metodo pesquisa no banco o pretendente a pai e recupera a cadeia superior
     * @param territorioPretendente
     * @return
     */
    private Territorio recuperarPretendenteLocalizacaoPai(Territorio territorioPretendente){
        Territorio territorioPai = null;
        //Vamos consultar o pai para trazer toda a listagem acima
        if(territorioPretendente != null && territorioPretendente.getId() != null){
            territorioPai = dao.findById(territorioPretendente.getId()).orElse(null);
        }
        return  territorioPai;
    }

    /**
     * Para checagem se possui autorelacionamento, ou seja, a cadeia superior nao pode
     * chegar no próprio objeto
     * @param territorio
     * @param territorioPai
     * @return
     */
    private boolean possuiAutoRelacionamento(Territorio territorio, Territorio territorioPai){
        if(territorioPai != null){
            return territorio.getId().equals(territorioPai.getId()) || possuiAutoRelacionamento(territorio, territorioPai.getTerritorioPai());
        }
        return false;
    }

}
