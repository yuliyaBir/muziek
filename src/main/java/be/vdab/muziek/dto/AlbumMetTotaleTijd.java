package be.vdab.muziek.dto;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;

import java.time.LocalTime;
import java.util.Set;

public record AlbumMetTotaleTijd(String naam, String artiestNaam, int jaar, String labelNaam, LocalTime totaleTijd, Set<Track> tracks) {
}
