package it.ms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ms.api.data.entity.Tutorial;
import it.ms.api.data.entity.Giocatore;
import it.ms.api.data.entity.Mossa;
import it.ms.api.data.entity.Response;
import it.ms.api.data.repo.TutorialRepository;
import it.ms.api.data.entity.Partita;

import it.ms.api.data.repo.MossaRepository;
import it.ms.api.data.repo.PartitaRepository;
import it.ms.api.data.repo.GiocatoreRepository;


@RestController
@RequestMapping("tris")
public class TestController {
    String[][] array;
    int index = 0;
    int sizeX = 3;
    int sizeY = 3;
    Response response = new Response();

    Giocatore g1;
    Giocatore g2;
    Partita partita;


    public TestController() {
        this.array = new String[sizeX][sizeY];
        reset();
    }

    @Autowired
    TutorialRepository tutorialRepo;

    @Autowired
    MossaRepository mossaRepo;

    @Autowired
    PartitaRepository partitaRepo;

    @Autowired
    GiocatoreRepository giocatoreRepo;

    @GetMapping("list")
    public List<Tutorial> list() {
        return tutorialRepo.findAll();
    }

    @GetMapping("start")
    public void start(@RequestParam("p1") String player1, @RequestParam("p2") String player2){

        if(giocatoreRepo.findByNome(player1).isEmpty()) {

            g1 = new Giocatore(player1);
            giocatoreRepo.save(g1);
        }else g1 = giocatoreRepo.findByNome(player1).get(0);
            
        if(giocatoreRepo.findByNome(player2).isEmpty()) {

            g2 = new Giocatore(player2);
            giocatoreRepo.save(g2);
        }else g2 = giocatoreRepo.findByNome(player2).get(0);

        partita = new Partita(g1,g2);

        reset();
        
    }

    @PostMapping("pos")
    public Response getPos(@RequestBody Mossa mossa) {

        array[mossa.getPosizioneX()][mossa.getPosizioneY()] = mossa.getSegno();
        // partitaRepo.save(partita);
        // mossa.setPartita(partita);
        // mossaRepo.save(mossa);
        index++;

        // Controllo vittoria
        if (isEnd_win(mossa.getSegno())) {
            response.setMsg("vincitore: " + mossa.getSegno());

            // Giocatore winner = getWinner(mossa, g1, g2);
            // partita.setVincitore(winner);
            
            // partitaRepo.save(partita);
            reset();

            return response;
        }

        // Controllo pareggio
        if (index == sizeX * sizeY) {
            response.setMsg("pareggio");

            // partita.setVincitore(null);
            // partitaRepo.save(partita);
            reset();
            return response;
        }

        // Nessun vincitore né pareggio
        response.setMsg("continuare");

        return response;
    }

    private void reset() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                array[i][j] = "-";
            }
        }
        index = 0;
    }

    private boolean isEnd_win(String segno) {
        // Controllo righe e colonne
        for (int i = 0; i < sizeX; i++) {
            if ((array[i][0] != null && array[i][0].equals(segno) &&
                 array[i][1] != null && array[i][1].equals(segno) &&
                 array[i][2] != null && array[i][2].equals(segno)) ||
                (array[0][i] != null && array[0][i].equals(segno) &&
                 array[1][i] != null && array[1][i].equals(segno) &&
                 array[2][i] != null && array[2][i].equals(segno))) {
                return true;
            }
        }
    
        // Controllo diagonali
        if ((array[0][0] != null && array[0][0].equals(segno) &&
             array[1][1] != null && array[1][1].equals(segno) &&
             array[2][2] != null && array[2][2].equals(segno)) ||
            (array[0][2] != null && array[0][2].equals(segno) &&
             array[1][1] != null && array[1][1].equals(segno) &&
             array[2][0] != null && array[2][0].equals(segno))) {
            return true;
        }
    
        return false;
    }

    public Giocatore getWinner(Mossa m,Giocatore g1,Giocatore g2){
        if(m.getGiocatore().equals(g1.getNome())) return g1;
        if(m.getGiocatore().equals(g2.getNome())) return g2;
        return null;
    }
}