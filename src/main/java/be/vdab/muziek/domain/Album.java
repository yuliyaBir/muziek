package be.vdab.muziek.domain;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private int jaar;
    private long barcode;
    private int score;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "labelId")
    private Label label;
    @ElementCollection
    @CollectionTable(name = "tracks",
            joinColumns = @JoinColumn(name = "albumId"))
    @OrderBy("naam")
    private Set<Track> tracks;

    public Album(long id, String naam, int jaar, long barcode, int score, Artiest artiest, Label label) {
        this.id = id;
        this.naam = naam;
        this.jaar = jaar;
        this.barcode = barcode;
        this.score = score;
        this.artiest = artiest;
        this.label = label;
        tracks = new LinkedHashSet<Track>();
    }

    public Album (){}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getJaar() {
        return jaar;
    }

    public long getBarcode() {
        return barcode;
    }

    public int getScore() {
        return score;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Label getLabel() {
        return label;
    }
    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }
}
