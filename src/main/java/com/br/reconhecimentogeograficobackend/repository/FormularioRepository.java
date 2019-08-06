package com.br.reconhecimentogeograficobackend.repository;

import com.br.reconhecimentogeograficobackend.model.Categoria;
import com.br.reconhecimentogeograficobackend.model.Formulario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FormularioRepository extends CrudRepository<Formulario, Long> {


}
