package net.codejava.QLAM.controllers;


import net.codejava.QLAM.Dto.AlbumSongs;
import net.codejava.QLAM.Dto.AlbumsDto;
import net.codejava.QLAM.Dto.ArtistSongs;
import net.codejava.QLAM.Models.Artist;
import net.codejava.QLAM.Models.ResponseObject;

import net.codejava.QLAM.Models.Song;
import net.codejava.QLAM.Repositories.AlbumRepository;
import net.codejava.QLAM.Repositories.ArtistRepository;
import net.codejava.QLAM.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/artists")
public class ArtistController {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("")
    List<Artist> getAllArtists(){return artistRepository.findAll();}

    @GetMapping("/{id}")
    ArtistSongs getArtistById(@PathVariable int id){
        var artist =  artistRepository.findById(id);
        var albums = albumRepository.findAll();

        var songs = new HashSet<Song>();
        var als = new HashSet<AlbumsDto>();

        if(artist.isPresent()){
//            songs.addAll(artist.get().getRepresentation());
            for (var pre: artist.get().getRepresentation()  ) {
                songs.add(pre);
            }
            for (var albumItem :albums) {
                for (var item: albumItem.getArtistAlbum()) {
                    if(item.getId() == id){
                        var al = new AlbumsDto();
                        al.id = albumItem.getId();
                        al.albumName = albumItem.getAlbumName();
                        al.albumImage = albumItem.getAlbumImage();
                        al.releaseDate = albumItem.getReleaseDate();
                        al.interestTimes = albumItem.getInterestTimes();
                        als.add(al);
                    }
                }
            }
            return new ArtistSongs(artist.get(), songs, als);
        }
        return new ArtistSongs();
    }

    @PostMapping("")
    Artist saveArtist(@RequestBody Artist newArtist){
        Artist foundArtists = artistRepository.findByArtistName(newArtist.getArtistName().trim());
        if(foundArtists != null)
            return new Artist();
        return artistRepository.save(newArtist);
    }

    @PutMapping("/{id}")
    Artist updateArtist(@RequestBody Artist newArtist, @PathVariable int id){
        Artist updateArtist = artistRepository.findById(id)
                .map(artist -> {
                    artist.setArtistName(newArtist.getArtistName());
                    artist.setArtistImage(newArtist.getArtistImage());
                    artist.setIntroduce(newArtist.getIntroduce());
                    return artistRepository.save(artist);
                }).orElseGet(() -> {
                    return artistRepository.save(newArtist);
                });
        return updateArtist;
    }

    @DeleteMapping("{id}")
    Boolean deleteArtist(@PathVariable int id){
        boolean exists = artistRepository.existsById(id);
        if(!exists)
            return false;
        artistRepository.deleteById(id);
        return true;
    }
}
