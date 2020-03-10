package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.Cliente;
import br.com.lifetime.exceptions.ObjectNotFoundException;
import br.com.lifetime.repositories.ClienteRepository;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class ClienteService {

	
	@Autowired
	private ClienteRepository repository; 
	@Autowired
	private ControleCampanhaRepository contRepo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}
	
	public Cliente insert(Cliente obj) {
		try {
		obj.setId(null);
		repository.save(obj);
		Integer id = obj.getId();
		int aId = obj.getaId();
		repository.setAssoc(aId, id);
		return obj;
		} catch (ConstraintViolationException e) {
			throw new br.com.lifetime.services.exception.ConstraintViolationException("não foi possivel adicionar !");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException ("n�o foi possivel adicionar registro, ID do Aai referenciado invalido");
		}
	}
	
	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void setNull (Integer id) {
		contRepo.nullControleCampanhaCliente(id);
		repository.nullAaiCliente(id);
	}
	
	public void delete (Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 31)
		setNull(id);
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
	public List<Cliente> finaAll(){
		return (List<Cliente>) repository.findAll();
	}
	
}
