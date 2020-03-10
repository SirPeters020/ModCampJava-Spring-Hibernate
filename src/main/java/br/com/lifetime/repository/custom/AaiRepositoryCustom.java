package br.com.lifetime.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Aai;

/**
 * 
 * Interface para conex�o da tabela Aai com o banco de dados
 *
 */
@Repository
public interface AaiRepositoryCustom {

	List<Aai> nullEquipe(Integer equipeId);
	
	List<Aai> reassocEquipe(Integer equipeId, Integer head);
	
	List<Aai> nullAai(Integer aaiId);
	
	List<Aai> setAssoc(Integer assocId, Integer id);
	
}
