package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.controller.dto.ImovelDTO;
import com.br.reconhecimentogeograficobackend.model.Imovel;
import com.br.reconhecimentogeograficobackend.model.Territorio;
import com.br.reconhecimentogeograficobackend.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("imovel")
public class ImovelController {
    private final ImovelRepository dao;

    @Autowired
    public ImovelController(ImovelRepository dao){
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Iterable<Imovel> imoveis = dao.findAll();
        ArrayList<ImovelDTO> imoveisDTO = new ArrayList();
        for(Imovel imovel : imoveis){
            ImovelDTO loc = new ImovelDTO(imovel);
            imoveisDTO.add(loc);
        }
        return new ResponseEntity<>(imoveisDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        //Imovel loc = (Imovel) ;
        Imovel loc = dao.findById(id).get();
        System.out.println("imovel get: "+loc.toString());
        ImovelDTO imovelDTO = new ImovelDTO(loc);
        return new ResponseEntity<>(imovelDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ImovelDTO imovelDTO){

        Imovel imovel= imovelDTO.build();
        dao.save(imovel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ImovelDTO imovelDTO){
        Imovel imovel = imovelDTO.build();
        dao.save(imovel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/byName")
    public ResponseEntity<?> getByName(@PathParam("logradouro") String logradouro){

        Iterable<Imovel> imoveis = dao.findByLogradouroIgnoreCaseContaining(logradouro);
        ArrayList<ImovelDTO> imoveisDTO = new ArrayList();
        for(Imovel imovel : imoveis){
            ImovelDTO loc = new ImovelDTO(imovel);
            imoveisDTO.add(loc);
        }
        return new ResponseEntity<>(imoveisDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/byLogradouroWithPagination")
    public ResponseEntity<?> getByNameWithPagination(
        @PathParam("logradouro") String logradouro,
        @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
        @RequestParam(value = "size", defaultValue = "100") Integer size,
        @RequestParam(value ="territorioId", required = false) Long territorioId){
        System.out.println("Logradouro:"+logradouro);
        if(logradouro == null)
            logradouro = "";
        Page<Imovel> imoveis;

        if(territorioId == null){
            imoveis = dao.findByLogradouroIgnoreCaseContaining(logradouro, PageRequest.of(page, size));
        } else {
            imoveis = dao.findByLogradouroContainingIgnoreCaseAndTerritorioId(logradouro, territorioId, PageRequest.of(page, size));
        }

        //System.out.println("ImovelsDTOS-> "+imoveis.map(ImovelDTO::new));

        return new ResponseEntity<>(!imoveis.isEmpty() ? imoveis.map(ImovelDTO::new) : "Nada encontrado", HttpStatus.OK);
    }
    @GetMapping(path = "/byTerritorio")
    public ResponseEntity<?> getByTerritorio(@PathParam("territorioId") Long territorioId,
                                                @PathParam("imovelId") Long imovelId){

        Iterable<Imovel> imoveis = dao.findByTerritorioIdAndIdNot(territorioId, imovelId);
        ArrayList<ImovelDTO> imoveisDTO = new ArrayList();
        for(Imovel imovel : imoveis){
            ImovelDTO loc = new ImovelDTO(imovel);
            imoveisDTO.add(loc);
        }
        return new ResponseEntity<>(imoveisDTO, HttpStatus.OK);
    }





}
