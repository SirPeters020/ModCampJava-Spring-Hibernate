package br.com.lifetime.resources;

import java.net.URI;
import java.util.List;
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

import br.com.lifetime.domain.Equipe;
import br.com.lifetime.services.EquipeService;

/**
 * 
 * Classe contendo os Requests da tablea Equipe
 *
 */
@RestController
@RequestMapping (value = "/equipe")
public class EquipeResource {

	@Autowired
	private EquipeService service;
	
	@RequestMapping("/login")
	public String home() {
		return "login";
	}
	// metodo GET, para um registro, com id especifico
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Equipe>find(@PathVariable Integer id) {
		Equipe obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Equipe obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void>update(@Valid @RequestBody Equipe obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void>delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// metodo GET para todos os registros da tabela
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Equipe>>findAll() {
		List<Equipe> list = service.finaAll();
		List<Equipe> listDto = list.stream().map(obj -> new Equipe(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
