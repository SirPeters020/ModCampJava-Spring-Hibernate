package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.SubCampanha;
import br.com.lifetime.exceptions.ObjectNotFoundException;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.repositories.SubCampanhaRepository;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio
 *
 */
@Service
public class SubCampanhaService {

	@Autowired
	private SubCampanhaRepository repository;
	@Autowired
	private ControleCampanhaRepository contRepo;

	public SubCampanha find(Integer id) {
		Optional<SubCampanha> obj = repository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}

	public SubCampanha insert(SubCampanha obj) {
		try {
			obj.setId(null);
			repository.save(obj);
			Integer id = obj.getId();
			int estrategId = obj.getEstrategId();
			int campId = obj.getCampId();
			repository.setAssocEstrategia(estrategId, id);
			repository.setAssocCampanha(campId, id);
			return obj;
		} catch (ConstraintViolationException e) {
			throw new br.com.lifetime.services.exception.ConstraintViolationException("não foi possivel adicionar !");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"n�o foi possivel adicionar registro, ID da campanha/estrategia referenciado invalido");
		}
	}

	public SubCampanha update(SubCampanha obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o
					// tratamento do ObjectNotFoundException (linha 29)
		contRepo.nullControleCampanhaSub(id);
		repository.nullAll(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não foi possivel concluir a exclusão !");
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("nenhum registro com id " + id + "encontrado !");
		}
	}

	public List<SubCampanha> finaAll() {
		return (List<SubCampanha>) repository.findAll();
	}

}
