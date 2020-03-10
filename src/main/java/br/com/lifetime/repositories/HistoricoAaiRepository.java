package br.com.lifetime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lifetime.domain.HistoricoAai;

@Repository
public interface HistoricoAaiRepository extends JpaRepository<HistoricoAai, Integer>{

}
