package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import com.br.reconhecimentogeograficobackend.model.TipoImovel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TipoImovelRepository extends CrudRepository<TipoImovel, Long> {
    List<TipoImovel> findByNomeIgnoreCaseContaining(String nome);

}
