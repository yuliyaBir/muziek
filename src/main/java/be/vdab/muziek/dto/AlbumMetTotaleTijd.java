package be.vdab.muziek.dto;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;

import java.time.LocalTime;
import java.util.Set;

public record AlbumMetTotaleTijd(String naam, String artiestNaam, int jaar, String labelNaam, LocalTime totaleTijd, Set<Track> tracks) {
    public AlbumMetTotaleTijd (Album album, LocalTime totaleTijd){
        this(album.getNaam(), album.getArtiest().getNaam(), album.getJaar(), album.getLabel().getNaam(), totaleTijd, album.getTracks());
    }
}
