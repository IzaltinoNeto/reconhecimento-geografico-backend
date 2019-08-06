package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.controller.dto.ListaDeTrabalhoDTO;
import com.br.reconhecimentogeograficobackend.model.ListaDeTrabalho;
import com.br.reconhecimentogeograficobackend.repository.ListaDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("lista-de-trabalho")
public class ListaDeTrabalhoController {
    private final ListaDeTrabalhoRepository dao;

    @Autowired
    public ListaDeTrabalhoController(ListaDeTrabalhoRepository dao){
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(dao.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ListaDeTrabalho listaDeTrabalho){

        return new ResponseEntity<>(dao.save(listaDeTrabalho), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ListaDeTrabalho listaDeTrabalho){
                ;
        return new ResponseEntity<>(dao.save(listaDeTrabalho), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/byName")
    public ResponseEntity<?> getByName(@PathParam("nome") String nome){
        return new ResponseEntity<>( dao.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
    }

    @GetMapping(path = "/byNameWithPagination")
    public ResponseEntity<?> getByNameWithPagination(
        @RequestParam("nome") String nome,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "100") Integer size
       ){
        System.out.println("nome: "+nome);

      return new ResponseEntity<>( dao.findByNomeIgnoreCaseContaining(nome, PageRequest.of(page, size)), HttpStatus.OK);
    }


    @GetMapping(path = "/byNameAndReconhecimentoNotNullWithPagination")
    public ResponseEntity<?> getByNameAndReconhecimentoNotNullWithPagination(
            @RequestParam("nome") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Integer size
    ){
        System.out.println("nome: "+nome);

        return new ResponseEntity<>( dao.findDistinctByNomeIgnoreCaseContainingAndItensReconhecimentoIsNotNull(nome, PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping(path = "/withItens")
    public ResponseEntity<?> getWithItens(
            @RequestParam("nome") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Integer size
    ){


        return new ResponseEntity<>( dao.findDistinctByNomeIgnoreCaseContainingAndItensReconhecimentoConteudoIsNotNull(nome, PageRequest.of(page, size)).map(ListaDeTrabalhoDTO::new), HttpStatus.OK);
    }





}

