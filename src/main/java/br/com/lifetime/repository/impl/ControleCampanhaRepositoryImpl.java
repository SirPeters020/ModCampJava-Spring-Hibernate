package br.com.lifetime.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lifetime.DTO.TabelaMesaDTO;
import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.Cliente;
import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.repository.custom.ControleCampanhaRepositoryCustom;

/**
 * 
 * @author pedro.silva
 *  Classe de implementação das consultas personalizadas
 *  através da classe ControleCampanhaRepositoryCustom
 *
 */


@Repository
public class ControleCampanhaRepositoryImpl implements ControleCampanhaRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	
	// implementação da query personalizada
	@Override
//	@Transactional
	public List<TabelaMesaDTO> consultaTable(){
		String jpql = "SELECT new br.com.lifetime.DTO.TabelaMesaDTO(cc.elegivel, cc.fomento, "
				+ "cc.fomentoRealizado, cc.operacaoEnviada, cc.permissaoRecebida, "
				+ "cc.ordemExecutada, cc.qtdeValor, cl.id, cl.nmCliente, "
				+ "cl.perfilXp, a.nmAai, e.nmEquipe) "
				+ "FROM ControleCampanha cc "
				+ "INNER JOIN cc.cliente cl "
				+ "INNER JOIN cl.aai a "
				+ "INNER JOIN a.equipe e";
		Query query = em.createQuery(jpql);
		List<TabelaMesaDTO> result = query.getResultList();
		return result;
	}


	@Override
	@Transactional
	public List<ControleCampanha> nullControleCampanhaCliente(Integer subCampanha) { 
		String jpql = "UPDATE br.com.lifetime.domain.ControleCampanha SET cliente_id = null WHERE cliente_id = :subCampanha ";
		Query query = em.createQuery(jpql);
		query.setParameter("subCampanha", subCampanha);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<ControleCampanha> nullControleCampanhaSub(Integer clienteId) { 
		String jpql = "UPDATE br.com.lifetime.domain.ControleCampanha SET sub_campanha_id = null WHERE sub_campanha_id = :clienteId";
		Query query = em.createQuery(jpql);
		query.setParameter("clienteId", clienteId);
		query.executeUpdate();
		return null;
	}

	@Override
	@Transactional
	public List<ControleCampanha> setAssocCliente(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.ControleCampanha SET cliente_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<ControleCampanha> setAssocSub(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.ControleCampanha SET sub_campanha_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
}
