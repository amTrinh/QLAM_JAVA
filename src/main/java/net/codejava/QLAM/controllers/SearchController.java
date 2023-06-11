package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Dto.AlbumsDto;
import net.codejava.QLAM.Dto.Search;
import net.codejava.QLAM.Models.*;
import net.codejava.QLAM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/search")
public class SearchController {
    //DI: Dependency Injection
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;
    @GetMapping("/{search}")
    Search searchSongs(@PathVariable String search){
        var foundBaiHat = songRepository.findBySongNameContains(search);
        var foundALbum = albumRepository.findByAlbumNameContains(search);
        var foundArtist = artistRepository.findByArtistNameContains(search);
        var songs = songRepository.findAll();

        var resultSong = new ArrayList<Song>();
        var count = 0;
        for (var bh: foundBaiHat) {
            count++;
            if(count <= 9)
                resultSong.add(bh);
        }
        //
        var resultAL = new HashSet<AlbumsDto>();
        for (var al: foundALbum) {
            var album = new AlbumsDto();
            album.id = al.getId();
            album.albumName = al.getAlbumName();
            album.albumImage = al.getAlbumImage();
            album.interestTimes = al.getInterestTimes();
            album.releaseDate = al.getReleaseDate();
            album.artist = al.getArtistAlbum();
            for (var song: songs) {
                if(song.getAlbum() != null){
                    if(song.getCountry() != null){
                        if(song.getAlbum().getId() == al.getId())
                            album.country.add(song.getCountry());
                    }
                }
            }
            resultAL.add(album);
        }

        if(foundBaiHat.size() > 0){
            for (var song: foundBaiHat) {
                var alTrung = false;
                if(song.getAlbum() != null){
                    for (var al: foundALbum) {

                            if(song.getAlbum().getId() == al.getId()){
                                alTrung = true;
                                break;
                            }
                    }
                    if(!alTrung){
                        var album = new AlbumsDto();
                        album.id = song.getAlbum().getId();
                        album.albumName = song.getAlbum().getAlbumName();
                        album.albumImage = song.getAlbum().getAlbumImage();
                        album.interestTimes = song.getAlbum().getInterestTimes();
                        album.releaseDate = song.getAlbum().getReleaseDate();
                        album.artist = song.getAlbum().getArtistAlbum();
                        album.country.add(song.getCountry());
                        resultAL.add(album);
                    }
                }

                var arTrung = false;
                    for(var pre : song.getRepresentation()){
                        for(var ar : foundArtist){
                            if(ar.getId() == pre.getId()){
                                arTrung = true;
                                break;
                            }
                        }
                        if(!arTrung){
                            arTrung = false;
                            foundArtist.add(pre);
                        }
                    }
            }
        }
        return new Search(resultAL, resultSong, foundArtist);
    }
}
