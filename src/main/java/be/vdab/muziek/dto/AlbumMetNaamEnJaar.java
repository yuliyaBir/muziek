package be.vdab.muziek.dto;

import be.vdab.muziek.domain.Album;

public record AlbumMetNaamEnJaar(String naam, int jaar) {
    public AlbumMetNaamEnJaar(Album album) {
        this(album.getNaam(), album.getJaar());
    }
}
