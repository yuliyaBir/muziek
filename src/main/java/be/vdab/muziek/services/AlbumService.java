package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.dto.AlbumMetTotaleTijd;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    public List<Album> findAll(){
        return albumRepository.findAll();
    }
    public Optional<Album> findById(long id){
        return albumRepository.findById(id);
    }
    public AlbumMetTotaleTijd findAlbumMetTotaleTijdById(long id){
        var album = albumRepository.findAlbumMetTotaleTijdById(id).orElseThrow(AlbumNietGevondenException::new);
        LocalTime som = LocalTime.MIN;
        for (var track: album.getTracks()) {
            var tijd = track.getTijd();
            som = som.plusHours(tijd.getHour()).plusMinutes(tijd.getMinute()).plusSeconds(tijd.getSecond());
        }
       return new AlbumMetTotaleTijd(album.getNaam(),
               album.getArtiest().getNaam(),
               album.getJaar(),
               album.getLabel().getNaam(),
               som,
               album.getTracks());
    }
    public List<Album> findByJaar(int jaar){
        return albumRepository.findByJaarOrderByNaam(jaar);
    }
    @Transactional
    public void wijzigScore(long id, int score){
        albumRepository.findById(id)
                .orElseThrow(AlbumNietGevondenException::new)
                .setScore(score);
    }
}
