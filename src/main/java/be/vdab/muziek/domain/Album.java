package be.vdab.muziek.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
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
    private Set<Track> tracks = new LinkedHashSet<Track>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return barcode == album.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
