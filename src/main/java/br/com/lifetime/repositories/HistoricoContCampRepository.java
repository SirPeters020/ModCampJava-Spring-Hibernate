package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.HistoricoContCamp;

@Repository
public interface HistoricoContCampRepository extends JpaRepository<HistoricoContCamp, Integer>{

}
