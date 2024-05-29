package it.ms.api.data.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "Mossa")
public class Mossa {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @ManyToOne
    @JoinColumn(name = "id_partita")
    private Partita partita;

    @Column(name = "posizioneX")
	private int posizioneX;

    @Column(name = "posizioneY")
    private int posizioneY;

    @Column(name = "segno")
	private String segno;

    @Column(name = "nomeGiocatore")
	private String nomeGiocatore;

    public Mossa(){

    }

    public int getPosizioneX() {
        return posizioneX;
    }
    public int getPosizioneY() {
        return posizioneY;
    }
    public void setPosizioneX(int posizioneX) {
        this.posizioneX = posizioneX;
    }
    public void setPosizioneY(int posizioneY) {
        this.posizioneY = posizioneY;
    }

    public String getSegno() {
        return segno;
    }
    public void setSegno(String segno) {
        this.segno = segno;
    }
    public String getGiocatore() {
        return nomeGiocatore;
    }
    public void setGiocatore(String giocatore) {
        this.nomeGiocatore = giocatore;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setPartita(Partita partita) {
        this.partita = partita;
    }
}
