package br.com.lifetime.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lifetime.DTO.TabelaMesaDTO;
import br.com.lifetime.domain.ControleCampanha;

/**
 * 
 * @author pedro.silva
<<<<<<< HEAD
 * Classe para metodos para consulta personalizada no BD
 *
 */

@Repository
public interface ControleCampanhaRepositoryCustom  {
	
	List<TabelaMesaDTO> consultaTable();
	
	List<ControleCampanha> nullControleCampanhaSub(Integer subCampanha);
	
	List<ControleCampanha> nullControleCampanhaCliente(Integer clienteId);
	
	List<ControleCampanha> setAssocCliente(Integer assocId, Integer id);
	
	List<ControleCampanha> setAssocSub(Integer assocId, Integer id);

}