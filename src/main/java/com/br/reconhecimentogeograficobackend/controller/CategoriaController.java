package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import com.br.reconhecimentogeograficobackend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("categoria")
public class CategoriaController {
    private final CategoriaRepository dao;

    @Autowired
    public CategoriaController(CategoriaRepository dao){
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(dao.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Categoria categoria){
        System.out.println("categoria"+ categoria);
        dao.save(categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Categoria categoria){
        dao.save(categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/byName")
    public ResponseEntity<?> getByStatus(@PathParam("nome") String nome){
        return new ResponseEntity<>(dao.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
    }
}
