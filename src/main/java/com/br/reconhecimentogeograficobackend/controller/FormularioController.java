package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.model.Formulario;
import com.br.reconhecimentogeograficobackend.repository.FormularioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("formulario")
@Api(value = "Formulario")
public class FormularioController {
    private final FormularioRepository dao;

    @Autowired
    public FormularioController(FormularioRepository dao){
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
    public ResponseEntity<?> post(@RequestBody Formulario formulario){
        System.out.println("formulario"+ formulario);
        dao.save(formulario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Formulario formulario){
        dao.save(formulario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
