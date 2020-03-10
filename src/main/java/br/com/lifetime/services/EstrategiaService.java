package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.Estrategia;
import br.com.lifetime.exceptions.ObjectNotFoundException;
import br.com.lifetime.repositories.EstrategiaRepository;
import br.com.lifetime.repositories.SubCampanhaRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class EstrategiaService {

	@Autowired
	private EstrategiaRepository repository;
	@Autowired
	private SubCampanhaRepository subRepo;
	
	public Estrategia find(Integer id) {
		Optional<Estrategia> obj = repository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}
	
	public Estrategia insert (Estrategia obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Estrategia update(Estrategia obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 30)
		subRepo.nullSubCampanhaEstrategia(id);
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
		
	public List<Estrategia> finaAll(){
		return (List<Estrategia>) repository.findAll();
	}
}
