package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    List<Categoria> findByNomeIgnoreCaseContaining(String nome);

}
