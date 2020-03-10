package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Equipe;

/**
 * 
 * Interface para conex�oda tabela Estrategia com o banco de dados
 *
 */
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer>{

}
