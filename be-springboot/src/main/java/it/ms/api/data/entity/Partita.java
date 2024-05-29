package it.ms.api.data.entity;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "Partita")
public class Partita {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    @ManyToOne
    @JoinColumn(name = "giocatore1", nullable = false)
    private Giocatore giocatore1;

    @ManyToOne
    @JoinColumn(name = "giocatore2", nullable = false)
    private Giocatore giocatore2;

    @OneToMany(mappedBy = "partita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mossa> mosse;

    @ManyToOne
    @JoinColumn(name = "vincitore_id")
    private Giocatore vincitore_id;

    public Partita(){

    }
    public Partita(Giocatore g1,Giocatore g2){

        this.giocatore1 = g1;
        this.giocatore2 = g2;

    }

    public Giocatore getGiocatore1_id() {
        return giocatore1;
    }
    public Giocatore getGiocatore2_id() {
        return giocatore2;
    }
    public long getId() {
        return id;
    }
    public List<Mossa> getMosse() {
        return mosse;
    }
    public Giocatore getVincitore_id() {
        return vincitore_id;
    }
    public void setGiocatore1_id(Giocatore giocatore1_id) {
        this.giocatore1 = giocatore1_id;
    }
    public void setGiocatore2_id(Giocatore giocatore2_id) {
        this.giocatore2 = giocatore2_id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setMosse(List<Mossa> mosse) {
        this.mosse = mosse;
    }
    public void setVincitore(Giocatore vincitore_id) {
        this.vincitore_id = vincitore_id;
    }

}
