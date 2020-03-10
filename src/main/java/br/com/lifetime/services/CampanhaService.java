package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.Campanha;
import br.com.lifetime.exceptions.ObjectNotFoundException;
import br.com.lifetime.repositories.CampanhaRepository;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository repository;
	
	public Campanha find(Integer id) {
		Optional<Campanha> obj = repository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}
	
	
	public Campanha insert(Campanha obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	
	public Campanha update (Campanha obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 29)
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não foi possivel concluir a exclusão !");
		}
		catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException ("nenhum registro com id " + id + "encontrado !");
		}
	}
	
	public List<Campanha> finaAll(){
		return (List<Campanha>) repository.findAll();
	}
	
}

