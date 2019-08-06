package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {
    List<Imovel> findByLogradouroIgnoreCaseContaining(String logradouro);
    Page<Imovel> findByLogradouroIgnoreCaseContaining(String logradouro, Pageable page);
    Page<Imovel> findByLogradouroContainingIgnoreCaseAndTerritorioId(String logradouro, Long territorioId, Pageable page);
    List<Imovel> findByTerritorioIdAndIdNot(Long territorioId, Long Id);
    List<Imovel> findByTerritorioId(Long territorioId);


}
