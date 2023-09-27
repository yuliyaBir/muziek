package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.dto.AlbumMetNaamEnJaar;
import be.vdab.muziek.dto.AlbumMetTotaleTijd;
import be.vdab.muziek.exceptions.ArtiestNietGevondenException;
import be.vdab.muziek.services.ArtiestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("artiesten")
public class ArtiestController {
    private final ArtiestService artiestService;

    public ArtiestController(ArtiestService artiestService) {
        this.artiestService = artiestService;
    }
    @GetMapping("{id}/albums")
    Stream<AlbumMetNaamEnJaar> findAlbumsById(@PathVariable long id){
        return artiestService.findById(id)
                .orElseThrow(ArtiestNietGevondenException::new)
                .getAlbums().stream()
                .map(album -> new AlbumMetNaamEnJaar(album));
    }
}