package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.ListaDeTrabalho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ListaDeTrabalhoRepository extends PagingAndSortingRepository<ListaDeTrabalho, Long> {
    List<ListaDeTrabalho> findByNomeIgnoreCaseContaining(String nome);
    Page<ListaDeTrabalho> findByNomeIgnoreCaseContaining(String nome, Pageable page);
    Page<ListaDeTrabalho> findDistinctByNomeIgnoreCaseContainingAndItensReconhecimentoIsNotNull(String nome,Pageable page);
    Page<ListaDeTrabalho> findDistinctByNomeIgnoreCaseContainingAndItensReconhecimentoConteudoIsNotNull(String nome,Pageable page);
    Page<ListaDeTrabalho> findByItensReconhecimentoIsNotNull(Pageable page);




}
