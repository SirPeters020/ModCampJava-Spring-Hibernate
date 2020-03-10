package br.com.lifetime.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.HistoricoAai;
import br.com.lifetime.services.AaiService;

/**
 * 
 * Classe contendo os Requests da tabela Aai
 *
 */
@RestController
@RequestMapping(value = "/aai")
public class AaiResource {

	@Autowired
	private AaiService service;
	
	// metodo GET, para um registro, com id especifico
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aai> find(@PathVariable Integer id) {
		Aai obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Aai obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody Aai obj, @PathVariable Integer id){
		obj.setId(id);
		obj =service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Aai aai = service.find(id);
		HistoricoAai newObj = new HistoricoAai(aai);
		service.insertHist(newObj);
		service.delete(id);
		ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.noContent().build();
	}
	
	// metodo GET para todos os registros da tabela
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aai>>findAll() {
		List<Aai> list = service.findAll();
		List<Aai> listDto = list.stream().map(obj -> new Aai(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
