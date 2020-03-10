package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.SubCampanha;
import br.com.lifetime.repository.custom.SubCampanhaRepositoryCustom;

/**
 * 
 * Interface para conexao da tabela SubCampanha com o banco de dados
 *
 */
@Repository
public interface SubCampanhaRepository extends JpaRepository<SubCampanha, Integer>, SubCampanhaRepositoryCustom{

}
