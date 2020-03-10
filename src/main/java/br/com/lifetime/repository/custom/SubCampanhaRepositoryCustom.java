package br.com.lifetime.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.SubCampanha;

/**
 * 
 * Interface para conexão da tabela Aai com o banco de dados
 *
 */
@Repository
public interface SubCampanhaRepositoryCustom {

	List<SubCampanha> nullSubCampanhaCamp(Integer campId);
	
	List<SubCampanha> nullSubCampanhaEstrategia(Integer estrategiaId);
	
	List<SubCampanha> nullAll(Integer id);
	
	List<SubCampanha> setAssocCampanha(Integer assocId, Integer id);
	
	List<SubCampanha> setAssocEstrategia(Integer assocId, Integer id);
	
}
