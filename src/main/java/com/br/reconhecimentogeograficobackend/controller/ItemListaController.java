package com.br.reconhecimentogeograficobackend.controller;

import com.br.reconhecimentogeograficobackend.controller.dto.ImovelDTO;
import com.br.reconhecimentogeograficobackend.controller.dto.ItemListaDTO;
import com.br.reconhecimentogeograficobackend.controller.dto.ListaDeTrabalhoDTO;
import com.br.reconhecimentogeograficobackend.model.ItemLista;
import com.br.reconhecimentogeograficobackend.model.ListaDeTrabalho;
import com.br.reconhecimentogeograficobackend.repository.ItemListaRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("item-lista")
@Api(value = "ItemLista")
public class ItemListaController {
    private final ItemListaRepository dao;

    @Autowired
    public ItemListaController(ItemListaRepository dao){
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Integer size,
            @RequestParam(value ="listaId") Long listaId){
        return new ResponseEntity<>(dao.findAllByListaId(listaId, PageRequest.of(page, size))
                .map(ItemListaDTO::new), HttpStatus.OK);
    }

    @GetMapping(path = "/byImovel")
    public ResponseEntity<?> getByImovel(
            @RequestParam(value ="imovelId") Long imovelId){
       /*ArrayList<ItemListaDTO> resposta=
               (ArrayList<ItemListaDTO>) dao.findByImovelId(imovelId).stream().map(ItemListaDTO::new).collect(Collectors.toList());*/
        return new ResponseEntity<>(new ItemListaDTO(dao.findByImovelId(imovelId)), HttpStatus.OK);
    }

    @GetMapping(path = "/byTerritorio")
    public ResponseEntity<?> getByTerritorio(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Integer size,
            @RequestParam(value ="territorioId") Long territorioId){

        return new ResponseEntity<>(dao.findAllByImovelTerritorioTerritorioPaiId(territorioId, PageRequest.of(page, size))
                .map(ItemListaDTO::new), HttpStatus.OK);
    }
    @GetMapping(path = "/totalByTerritorio")
    public ResponseEntity<?> getTotalByTerritorio(
            @RequestParam(value ="territorioId") Long territorioId){
        return new ResponseEntity<>((ArrayList<ItemListaDTO>) dao.findByImovelTerritorioTerritorioPaiIdOrImovelTerritorioId(territorioId, territorioId)
                .stream().map(ItemListaDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ItemLista.ItemListaId item){
        System.out.println("item"+ item);
        ItemLista created = new ItemLista();
        created.setId(item);
        dao.save(created);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(path = "/salvarInteiro")
    public ResponseEntity<?> postItem(@RequestBody ItemListaDTO item){

        System.out.println("itemListaDTO: "+ item.toString());
        System.out.println("itemListaDTO Formulario: "+ item.getReconhecimento().getFormulario().getId());
        System.out.println("itemLIsta: "+ item.build().toString());
        System.out.println("Reconhecimennto: "+ item.build().getReconhecimento().getFormulario().getId());
        dao.save(item.build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/atualizarListaInteira")
    public ResponseEntity<?> updateLista(@RequestBody ListaDeTrabalhoDTO listaDTO){
        ListaDeTrabalho lista = listaDTO.build();
        lista.getItens().forEach(item -> dao.save(item));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ItemListaDTO itemDTO){
        ItemLista item = itemDTO.build();
        dao.save(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam(value = "listaId") Long listaId,
                                    @RequestParam(value = "imovelId") Long imovelId){
        ItemLista.ItemListaId id = new ItemLista.ItemListaId(imovelId,listaId);
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
