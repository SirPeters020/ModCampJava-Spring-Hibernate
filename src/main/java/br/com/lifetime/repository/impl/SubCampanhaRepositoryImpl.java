package br.com.lifetime.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lifetime.domain.Cliente;
import br.com.lifetime.domain.SubCampanha;
import br.com.lifetime.repository.custom.SubCampanhaRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela Aai com o banco de dados
 *
 */
@Repository
public class SubCampanhaRepositoryImpl implements SubCampanhaRepositoryCustom{

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public List<SubCampanha> nullSubCampanhaCamp(Integer campId) { 
		String jpql = "UPDATE br.com.lifetime.domain.SubCampanha SET campanha_id = null WHERE campanha_id = :campId";
		Query query = em.createQuery(jpql);
		query.setParameter("campId", campId);
		query.executeUpdate();
		return null;
	}
	
	
	@Override
	@Transactional
	public List<SubCampanha> nullSubCampanhaEstrategia(Integer estrategiaId) { 
		String jpql = "UPDATE br.com.lifetime.domain.SubCampanha SET estrategia_id = null WHERE estrategia_id = :estrategiaId ";
		Query query = em.createQuery(jpql);
		query.setParameter("estrategiaId", estrategiaId);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<SubCampanha> nullAll(Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.SubCampanha SET estrategia_id = null, campanha_id = null WHERE id = :id ";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<SubCampanha> setAssocCampanha(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.SubCampanha SET campanha_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}

	@Override
	@Transactional
	public List<SubCampanha> setAssocEstrategia(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.SubCampanha SET estrategia_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
}