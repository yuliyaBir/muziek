package be.vdab.muziek.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "labels")
public class Label {
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

    public Label(String naam) {
        this.naam = naam;
    }
    public Label(){}
}
