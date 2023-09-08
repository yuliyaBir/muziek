package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.dto.AlbumMetTotaleTijd;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(params = "jaar")
    Stream<AlbumBeknopt> findByJaar(int jaar){
        return albumService.findByJaar(jaar).stream()
                .map(album -> new AlbumBeknopt(album));
    }
    private record GewijzigdeScore(@Min(0)  @Max(10) int score){}
    @PatchMapping("{id}/score")
    void wijzigScore(@PathVariable long id, @RequestBody @Valid GewijzigdeScore gewijzigdeScore){
        albumService.wijzigScore(id, gewijzigdeScore.score);
    }
}
