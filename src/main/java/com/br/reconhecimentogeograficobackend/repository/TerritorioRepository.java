package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Territorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface TerritorioRepository extends PagingAndSortingRepository<Territorio, Long> {
    List<Territorio> findByNomeIgnoreCaseContaining(String nome);
    @Query(value =
            "SELECT * FROM territorio ORDER BY ST_XMin(poligono);",
            nativeQuery = true)
    List<Territorio> findByNomeIgnoreCaseContainingOrderByPoligonoDesc(String nome);
    Page<Territorio> findByNomeIgnoreCaseContaining(String nome, Pageable page);
    Page<Territorio> findByNomeContainingIgnoreCaseAndTerritorioPaiId(String nome, Long territorioPaiId, Pageable page);
    List<Territorio> findByTerritorioPaiIdAndIdNot(Long territorioPaiId, Long Id);
    List<Territorio> findByTerritorioPaiId(Long territorioPaiId);
    List<Territorio> findByTerritorioPaiIdIsNull();

}
