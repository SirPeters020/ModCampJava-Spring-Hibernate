package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.Estrategia;

/**
 * 
 * Interface para conex�oda tabela Estrategia com o banco de dados
 *
 */
@Repository
public interface EstrategiaRepository extends JpaRepository<Estrategia, Integer>{

}
