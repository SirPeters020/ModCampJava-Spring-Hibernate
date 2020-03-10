package br.com.lifetime.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.Cliente;
import br.com.lifetime.repository.custom.ClienteRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela Cliente com o banco de dados
 *
 */
@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryCustom{

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public List<Cliente> nullAai(Integer aaiId) { 
		String jpql = "UPDATE br.com.lifetime.domain.Cliente  SET aai_id = null WHERE aai_id = :aaiId";
		Query query = em.createQuery(jpql);
		query.setParameter("aaiId", aaiId);
		query.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<Cliente> nullAaiCliente(Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.Cliente  SET aai_id = null WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		query.executeUpdate();
//		String second = "DELETE FROM br.com.lifetime.domain.Cliente WHERE id = :id";
//		Query del = em.createQuery(second);
//		del.setParameter("id", id);
//		del.executeUpdate();
		return null;
	}
	
	@Override
	@Transactional
	public List<Cliente> setAssoc(Integer assocId, Integer id) { 
		String jpql = "UPDATE br.com.lifetime.domain.Cliente SET aai_id = :assocId WHERE id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("assocId", assocId);
		query.setParameter("id", id);
		query.executeUpdate();
		return null;
	}
	
}
