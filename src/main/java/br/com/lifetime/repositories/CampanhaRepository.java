package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Campanha;

/**
 * 
 * Interface para conexão da tabela Campanha com o banco de dados
 *
 */
@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer>{

}
