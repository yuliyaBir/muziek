package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
//    @Query("""
//        select a from Album a
//        join fetch a.artiest
//        order by a.naam
//        """)
    @Override
    @EntityGraph(attributePaths = "artiest")
    List<Album> findAll();

    @EntityGraph(attributePaths = {"tracks", "artiest", "label"})
    Optional<Album> findAlbumMetTotaleTijdById(Long id);
    @EntityGraph(attributePaths = "artiest")
    List<Album> findByJaarOrderByNaam(int jaar);
}