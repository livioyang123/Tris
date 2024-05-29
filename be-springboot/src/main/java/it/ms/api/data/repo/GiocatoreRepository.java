package it.ms.api.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ms.api.data.entity.Giocatore;

public interface GiocatoreRepository extends JpaRepository<Giocatore, Long>{

    List<Giocatore> findByNome(String nome);
    
}
