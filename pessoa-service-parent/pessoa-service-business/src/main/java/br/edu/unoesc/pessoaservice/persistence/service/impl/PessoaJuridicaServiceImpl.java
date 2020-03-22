package br.edu.unoesc.pessoaservice.persistence.service.impl;

import org.springframework.stereotype.Service;

import br.edu.unoesc.pessoaservice.common.model.PessoaJuridica;
import br.edu.unoesc.pessoaservice.persistence.repository.PessoaJuridicaRepository;
import br.edu.unoesc.pessoaservice.persistence.service.PessoaJuridicaService;
import br.edu.unoesc.sistemautils.arquitetura.business.AbstractMasterCrudService;

@Service
public class PessoaJuridicaServiceImpl extends AbstractMasterCrudService<PessoaJuridica, PessoaJuridicaRepository> implements PessoaJuridicaService { 

}
