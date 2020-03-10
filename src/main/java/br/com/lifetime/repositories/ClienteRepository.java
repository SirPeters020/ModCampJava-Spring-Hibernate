package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Cliente;
import br.com.lifetime.repository.custom.ClienteRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela Cliente com o banco de dados
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, ClienteRepositoryCustom{

}
