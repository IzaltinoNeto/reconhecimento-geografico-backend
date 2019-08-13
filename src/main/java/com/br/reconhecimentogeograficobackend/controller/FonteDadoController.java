package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.model.FonteDado;
import com.br.reconhecimentogeograficobackend.repository.FonteDadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("fonte-dado")
public class FonteDadoController {
    private final FonteDadoRepository dao;

    @Autowired
    public FonteDadoController(FonteDadoRepository dao){
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
    public ResponseEntity<?> post(@RequestBody FonteDado fonteDado){
        System.out.println("fonteDado"+ fonteDado);
        dao.save(fonteDado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody FonteDado fonteDado){
        dao.save(fonteDado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/byName")
    public ResponseEntity<?> getByName(@PathParam("nome") String nome){
        return new ResponseEntity<>(dao.findByDescricaoIgnoreCaseContaining(nome), HttpStatus.OK);
    }
}
