package br.com.lifetime.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.DTO.TabelaMesaDTO;
import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.domain.HistoricoContCamp;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.repositories.HistoricoContCampRepository;
import br.com.lifetime.services.exception.DataIntegrityException;

/**
 * 
 * Classe contendo todas as regras de negocio 
 *
 */
@Service
public class TabelaMesaService {

	@Autowired
	private ControleCampanhaRepository repository;
	@Autowired
	private HistoricoContCampRepository histRepo;
	
	public List<TabelaMesaDTO> consultaTable(){
		return repository.consultaTable();
	}
	
	public ControleCampanha find(Integer id) {
		Optional<ControleCampanha> obj = repository.findById(id);
		if (obj == null) {
			throw new br.com.lifetime.exceptions.ObjectNotFoundException("Arquivo não encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}
	
	public HistoricoContCamp insert(HistoricoContCamp obj) {
		obj.setId(null);
		return histRepo.save(obj);
	}
	
	public ControleCampanha update(ControleCampanha obj) {
		return repository.save(obj);
	}
	
	public void delete (Integer id) {
		find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 37)
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e ) {
			throw new DataIntegrityException("não foi possivel excluir !");
		}
	}
	
}
