package br.edu.unoesc.pessoaservice.persistence.service;

import java.util.List;
import java.util.Optional;

import br.edu.unoesc.pessoaservice.common.model.Cidade;

public interface CidadeService {

    // == service CRUD ==
    List<Cidade> getAll();

    List<Cidade> getAllByEstado(Long idEstado);

    Optional<Cidade> getOne(Long id);

    Cidade create(Cidade cidade);

    Cidade update(Cidade cidade);

    void delete(Long id);

    List<Cidade> findAllCidadeBySiglaAndDescricao(String sigla, String descricao);
}