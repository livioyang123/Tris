package it.ms.api.data.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Giocatore")
public class Giocatore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    public Giocatore() {
    }

    public Giocatore(String nome) {
        this.nome = nome;
    }

    public Giocatore(Giocatore giocatore) {
        this.nome = giocatore.getNome();
        this.id = giocatore.getId();
    }

    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
