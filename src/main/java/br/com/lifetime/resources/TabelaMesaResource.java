package br.com.lifetime.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lifetime.DTO.TabelaMesaDTO;
import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.domain.HistoricoContCamp;
import br.com.lifetime.services.TabelaMesaService;

/**
 * 
 * Classe contendo os Requests
 *
 */
@RestController
@RequestMapping(value = "/DataTableDTO")
public class TabelaMesaResource {

	@Autowired
	private TabelaMesaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TabelaMesaDTO>>custom() {
		List<TabelaMesaDTO> list = service.consultaTable();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Void> insert(@Valid @RequestBody HistoricoContCamp obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()	//fromCurrentRequest utiliza URI atual : path o caminho que sera adicionado
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //buildAndExpand(obj.getId()).toUri() pega o id do obj e conver para ser passado na uri
		return ResponseEntity.created(uri).build(); //created(uri) retorna o status create com o URI que foi criadp
	}
	
	@RequestMapping (method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Void> update (@Valid @RequestBody HistoricoContCamp obj){
		ControleCampanha oldObj = new ControleCampanha();
		oldObj = service.find(obj.getId());
		oldObj.setElegivel(obj.isElegivel());
		oldObj.setFomentoRealizado(obj.isFomentoRealizado());
		oldObj.setQtdeValor(obj.getQtdeValor());
		oldObj = service.update(oldObj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		ControleCampanha cc = service.find(id);
		HistoricoContCamp newObj = new HistoricoContCamp(cc);
		service.insert(newObj);
		service.delete(id);
		ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.noContent().build();
	}
}
