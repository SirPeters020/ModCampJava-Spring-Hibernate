package br.com.lifetime.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.repository.custom.AaiRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela Aai com o banco de dados
 *
 */
@Repository
public interface AaiRepository extends JpaRepository<Aai, Integer>, AaiRepositoryCustom{

}
