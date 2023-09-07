package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.dto.AlbumMetTotaleTijd;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
    private record AlbumBeknopt(String naam, String artiestNaam, int jaar){
        private AlbumBeknopt(Album album) {
            this(album.getNaam(), album.getArtiest().getNaam(), album.getJaar());
        }
    }
    @GetMapping
    Stream<AlbumBeknopt> findAll(){
        return albumService.findAll()
                .stream()
                .map(album -> new AlbumBeknopt(album));
    }
    @GetMapping("{id}")
    AlbumMetTotaleTijd findAlbumMetTotaleTijdById(@PathVariable long id){
        return albumService.findAlbumMetTotaleTijdById(id);
    }
}
