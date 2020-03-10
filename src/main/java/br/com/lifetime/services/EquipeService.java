package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.Equipe;
import br.com.lifetime.repositories.AaiRepository;
import br.com.lifetime.repositories.EquipeRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class EquipeService {

	@Autowired
	private EquipeRepository repository;
	@Autowired
	private AaiRepository aaiRepo;
	
	Aai aai = new Aai();
	
	
	public Equipe find(Integer id) {
		Optional<Equipe> obj = repository.findById(id);
		if (obj == null) {
			throw new br.com.lifetime.exceptions.ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}
	
	public Equipe insert(Equipe obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Equipe update(Equipe obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 29)
		aaiRepo.nullAai(id);
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
	
	public List<Equipe> finaAll(){
		return (List<Equipe>) repository.findAll();
	}
	
	
}
