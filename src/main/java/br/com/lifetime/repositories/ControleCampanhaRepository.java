package br.com.lifetime.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.DTO.TabelaMesaDTO;
import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.repository.custom.ControleCampanhaRepositoryCustom;

/**
 * 
 * Interface para conex�o da tabela ControleCampanha com o banco de dados
 *
 */
@Repository
public interface ControleCampanhaRepository extends JpaRepository<ControleCampanha, Integer>, ControleCampanhaRepositoryCustom{
	
	
}
