package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class ControleCampanhaService {

	@Autowired
	private ControleCampanhaRepository repository;

	public ControleCampanha find(Integer id) {
		Optional<ControleCampanha> obj = repository.findById(id);
		if (obj == null) {
			throw new br.com.lifetime.exceptions.ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}

	public ControleCampanha insert(ControleCampanha obj) {
		try {
		obj.setId(null);
		repository.save(obj);
		Integer id = obj.getId();
		int cliId = obj.getCliId();
		int subId = obj.getSubId();
		repository.setAssocCliente(cliId, id);
		repository.setAssocSub(subId, id);
		return obj;
		} catch (ConstraintViolationException e) {
			throw new br.com.lifetime.services.exception.ConstraintViolationException("não foi possivel adicionar !");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException ("n�o foi possivel adicionar registro, ID do cliente ou SubCampanha referenciado invalido");
		}
	}

	public ControleCampanha update(ControleCampanha obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete (Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 28)
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
	
	public List<ControleCampanha> finaAll(){
		return (List<ControleCampanha>) repository.findAll();
	}
	
}
