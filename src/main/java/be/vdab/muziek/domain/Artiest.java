package be.vdab.muziek.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest(String naam) {
        this.id = 0;
        this.naam = naam;
    }
    public Artiest(){}
}
