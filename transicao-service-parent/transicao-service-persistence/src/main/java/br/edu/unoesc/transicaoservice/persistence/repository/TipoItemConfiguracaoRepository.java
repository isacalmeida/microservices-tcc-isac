package br.edu.unoesc.transicaoservice.persistence.repository;

import org.springframework.stereotype.Repository;

import br.edu.unoesc.sistemautils.arquitetura.persistence.repository.IIdentityRepository;
import br.edu.unoesc.transicaoservice.common.model.TipoItemConfiguracao;

@Repository
public interface TipoItemConfiguracaoRepository extends IIdentityRepository<TipoItemConfiguracao, Long> {
}
