package br.com.lifetime.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Cliente;

/**
 * 
 * Interface para conex�o da tabela Cliente com o banco de dados
 *
 */
@Repository
public interface ClienteRepositoryCustom {

	// metodo para setar Aai como null, porem quando é chamado pelo Aai service;
	List<Cliente> nullAai(Integer aaiId);
	
	// metodo para setar Aai como null, porem quando é chamado pelo proprio cliente Serice;
	List<Cliente> nullAaiCliente(Integer id);
	
	List<Cliente> setAssoc(Integer assocId, Integer id);
	
}
