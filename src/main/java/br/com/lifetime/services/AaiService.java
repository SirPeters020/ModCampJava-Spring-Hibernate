package br.com.lifetime.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.Cliente;
import br.com.lifetime.domain.HistoricoAai;
import br.com.lifetime.exceptions.ObjectNotFoundException;
import br.com.lifetime.repositories.AaiRepository;
import br.com.lifetime.repositories.ClienteRepository;
import br.com.lifetime.repositories.HistoricoAaiRepository;
import br.com.lifetime.resources.SugarConnection;
import br.com.lifetime.services.exception.DataIntegrityException;
import br.com.lifetime.services.exception.EmptyResultDataAccessException;

/**
 * 
 * Classe contendo todas as regras de negocio
 *
 */
@Service
public class AaiService {

	@Autowired
	private AaiRepository repository;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private HistoricoAaiRepository histRepo;
	@Autowired
	private SugarConnection sugar;

	Cliente cliente = new Cliente();
//	@Autowired
//	private Equipe equipe;

	public Aai find(Integer id) {
		Optional<Aai> obj = repository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Arquivo nao encontrado! Codigo: " + id);
		}
		return obj.orElse(null);
	}

	public Aai insert(Aai obj) {
		try {
			obj.setId(null);
			repository.save(obj);
			Integer id = obj.getId();
			int eq = obj.getIdEquipe();
			repository.setAssoc(eq, id);
			return obj;
//			return repository.save(obj);
		} catch (ConstraintViolationException e) {
			throw new br.com.lifetime.services.exception.ConstraintViolationException("não foi possivel adicionar !");
		}
	}

	public HistoricoAai insertHist(HistoricoAai obj) {
		obj.setId(null);
		return histRepo.save(obj);
	}

	public Aai update(Aai obj) {
		find(obj.getId());
		repository.save(obj);
		Integer id = obj.getId();
		int idEquipe = obj.getIdEquipe();
		repository.setAssoc(idEquipe, id);
		return obj;
//		return repository.save(obj);
	}

	public void setNull(Integer id) {
		find(id);
		clienteRepo.nullAai(id);
//		repository.nullEquipe(id);
		Map<String, String> condicao = (Map<String, String>) sugar.sugarResponse();
		condicao = condicao.get("userId");
		repository.reassocEquipe(id, id);
	}

	public void delete(Integer id) {
		Aai obj = find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 29)
		Map<String, String> condicao = (Map<String, String>) sugar.sugarResponse();
		if ((condicao.containsValue("Senior")) && condicao.get("userId").equals(obj.getId())) {
			setNull(id);
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("não foi possivel concluir a exclusão !");
			} catch (EmptyResultDataAccessException e) {
				throw new EmptyResultDataAccessException("nenhum registro com id " + id + "encontrado !");
			}
		}
	}
	
	
	// classe para testar request 
//	public void delete(Integer id) {
//		Aai obj = find(id);// find aqui apenas para caso o registro não for encontrado ele ja retornar o tratamento do ObjectNotFoundException (linha 29)
//		System.out.println(obj.getId());
//		Map<String, String> condicao = (Map<String, String>) sugar.sugarResponse();
//		if ((!condicao.containsValue("Senior"))) {
//			System.out.println("sucesso");
//			System.out.println(condicao.get("userId"));
//		} else {
//			System.out.println("fail");
//		}
//	}
//
//	public List<Aai> finaAll() {
//		return repository.findAll();
//	}

}
