package it.ms.api.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ms.api.data.entity.Giocatore;
import it.ms.api.data.entity.Partita;


public interface PartitaRepository extends JpaRepository<Partita, Long> {

    List<Partita> findByGiocatore1OrGiocatore2(Giocatore nome1,Giocatore nome2);
  
}
