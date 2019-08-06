package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.ItemLista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ItemListaRepository extends PagingAndSortingRepository<ItemLista, ItemLista.ItemListaId> {

    Page<ItemLista> findAllByListaId(Long listaId, Pageable var1);
    ItemLista findByImovelId(Long imovelId);
    Page<ItemLista> findAllByImovelTerritorioTerritorioPaiId(Long territorioId, Pageable var1);
    List<ItemLista> findByImovelTerritorioTerritorioPaiIdOrImovelTerritorioId(Long territorioPaiId, Long territorioId);


}
