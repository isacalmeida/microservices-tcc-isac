package br.edu.unoesc.sistemautils.arquitetura.business;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import br.edu.unoesc.sistemautils.arquitetura.common.AbstractDetailEntity;
import br.edu.unoesc.sistemautils.arquitetura.common.AbstractMasterEntity;
import br.edu.unoesc.sistemautils.arquitetura.persistence.IDetailRepository;
import br.edu.unoesc.sistemautils.arquitetura.persistence.IMasterRepository;

public abstract class AbstractDetailCrudService<EM extends AbstractMasterEntity, ED extends AbstractDetailEntity<EM>, RM extends IMasterRepository<EM, Long>, RD extends IDetailRepository<EM, ED, Long>> implements IDetailCrudService<EM, ED> {

	@Autowired
	private RM masterRepository;

	@Autowired
	private RD detailRepository;

	@Override
	public Page<ED> getAllPaged(Long idParent, Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ED> getAll(Long idParent, Class<ED> classDetailEntity) {
		ED detailEntity = null;
		try {
			 detailEntity = classDetailEntity.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			// TODO
		}
		if(Objects.isNull(detailEntity)) {
			// TODO
		}
		EM masterEntity = masterRepository.getOne(idParent);
		detailEntity.setMasterEntity(masterEntity);
		Example<ED> detailEntityExample = Example.of(detailEntity);
		return detailRepository.findAll(detailEntityExample);
	}

	@Override
	public Optional<ED> getOne(Long idParent, Long id) {
		Optional<ED> detailEntityOptional = detailRepository.findById(id);
		if(detailEntityOptional.isEmpty()) {
			return detailEntityOptional;
		}
		ED detailEntity = detailEntityOptional.get();
		EM masterEntity = masterRepository.getOne(idParent);
		if(!detailEntity.getMasterEntity().getId().equals(masterEntity.getId())) {
			return Optional.empty();
		}
		return detailEntityOptional;
	}

	@Override
	public ED create(Long idParent, ED detailEntity) {
		EM masterEntity = masterRepository.getOne(idParent);
		detailEntity.setMasterEntity(masterEntity);
		return detailRepository.save(detailEntity);
	}

	@Override
	public ED update(Long idParent, ED detailEntity) {
		EM masterEntity = masterRepository.getOne(idParent);
		masterEntity.setDataAlteracao(Calendar.getInstance().getTime());
		masterRepository.save(masterEntity);
		return detailRepository.save(detailEntity);
	}

	@Override
	public void delete(Long idParent, Long id) {
		EM masterEntity = masterRepository.getOne(idParent);
		ED detailEntity = detailRepository.getOne(id);
		if(!masterEntity.getId().equals(detailEntity.getMasterEntity().getId())) {
			// TODO
		}
		detailRepository.delete(detailEntity);
	}
}