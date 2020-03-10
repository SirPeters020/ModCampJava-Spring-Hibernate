package br.com.lifetime.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.repository.custom.AaiRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela Aai com o banco de dados
 *
 */
@Repository
public class AaiRepositoryImpl implements AaiRepositoryCustom{

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public List<Aai> nullEquipe(Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.Aai SET equipe_id = null WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<Aai> reassocEquipe(Integer id, Integer head) { 
		String jpql = "UPDATE br.com.lifetime.domain.Aai SET equipe_id = head WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		query.setParameter("head", head);
		query.executeUpdate();
		return null;
	}
	
	
	@Override
	@Transactional
	public List<Aai> nullAai(Integer aaiId) { 
		String jpql = "UPDATE br.com.lifetime.domain.Aai SET equipe_id = null WHERE equipe_id = :aaiId";
		Query query = em.createQuery(jpql);
		query.setParameter("aaiId", aaiId);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<Aai> setAssoc(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.Aai SET equipe_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	

}