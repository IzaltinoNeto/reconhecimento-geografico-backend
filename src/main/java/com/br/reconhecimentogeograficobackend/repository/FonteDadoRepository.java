package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import com.br.reconhecimentogeograficobackend.model.FonteDado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FonteDadoRepository extends CrudRepository<FonteDado, Long> {
    List<FonteDado> findByDescricaoIgnoreCaseContaining(String descricao);

}
