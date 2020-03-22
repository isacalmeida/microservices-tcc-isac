package br.edu.unoesc.pessoaservice.web.controller.estado;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unoesc.pessoaservice.common.model.Cep;
import br.edu.unoesc.pessoaservice.common.model.Cidade;
import br.edu.unoesc.pessoaservice.common.model.Estado;
import br.edu.unoesc.pessoaservice.persistence.service.CepService;
import br.edu.unoesc.pessoaservice.persistence.service.CidadeService;
import br.edu.unoesc.pessoaservice.persistence.service.EstadoService;

@RestController
@RequestMapping("/estados/{idEstado}/cidades/{idCidade}/ceps")
public class EstadoCidadeCepController {

    // == fields ==
    private Environment environment;
    private EstadoService estadoService;
    private CidadeService cidadeService;
    private CepService cepService;

    // == constructors ==
    @Autowired
    public EstadoCidadeCepController(Environment environment, EstadoService estadoService, CidadeService cidadeService, CepService cepService){
        this.environment = environment;
        this.estadoService = estadoService;
        this.cidadeService = cidadeService;
        this.cepService = cepService;
    }

    // == CRUD HTTP methods ==
    @GetMapping
    public List<Cep> listAll(@PathVariable Long idEstado, @PathVariable Long idCidade){
        return cepService.getAllByEstadoAndCidade(idEstado, idCidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cep> findOne(@PathVariable Long idEstado, @PathVariable Long idCidade, @PathVariable Long id){
        Optional<Cep> cepFind = cepService.getOne(id);
        if (cepFind.isPresent())
            if (!cepFind.get().getCidade().getEstado().getIdEstado().equals(idEstado))
                if(!cepFind.get().getCidade().getIdCidade().equals(idCidade))
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return cepFind
                .map(cep -> ResponseEntity.ok().body(cep))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cep> create(@PathVariable Long idEstado, @PathVariable Long idCidade, @RequestBody Cep cep){
        if(cep == null) {
            return ResponseEntity.noContent().build();
        }
        
		Optional<Estado> estado = estadoService.getOne(idEstado);
		if (estado.isEmpty()) {
		    return ResponseEntity.notFound().build();
		}
		Optional<Cidade> cidade = cidadeService.getOne(idCidade);
		if (cidade.isEmpty()) {
		    return ResponseEntity.notFound().build();
		}
		cidade.get().setEstado(estado.get());
		cep.setCidade(cidade.get());

		Cep cepCreated = cepService.create(cep);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(cepCreated.getIdCep()).toUri();

		return ResponseEntity.created(location).body(cepCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cep> update(@PathVariable Long idEstado, @PathVariable Long idCidade, @PathVariable Long id, @RequestBody Cep cep){
        Optional<Estado> estado = estadoService.getOne(idEstado);
        Optional<Cidade> cidade = cidadeService.getOne(idCidade);
        Optional<Cep> cepUpdated = cepService.getOne(id);

        if(cepUpdated.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
		if(cep == null) {
		    return ResponseEntity.noContent().build();
		}
		cidade.get().setEstado(estado.get());
		cep.setCidade(cidade.get());
		cep.setDataCriacao(cepUpdated.get().getDataCriacao());
		cep.setDataAlteracao(Calendar.getInstance().getTime());
		cep.setIdCep(cepUpdated.get().getIdCep());
		return ResponseEntity.ok(cepService.update(cep));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long idEstado, @PathVariable Long idCidade, @PathVariable Long id){
    	return cepService.getOne(id)
                .map(cep -> {
                    cepService.delete(id);
                    return ResponseEntity.ok().body(id);
                }).orElse(ResponseEntity.notFound().build());
    }
    // == CRUD HTTP methods ==


    // == HATEOAS HTTP methods ==

    // == HATEOAS HTTP methods ==


    // == Specific HTTP methods ==
    @GetMapping("/port")
    public Integer getPort(){
        return Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")));
    }
    // == Specific HTTP methods ==
}
