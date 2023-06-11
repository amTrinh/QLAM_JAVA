package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Dto.AlbumSongs;
import net.codejava.QLAM.Dto.AlbumsDto;
import net.codejava.QLAM.Models.*;
import net.codejava.QLAM.Repositories.AlbumRepository;
import net.codejava.QLAM.Repositories.ArtistRepository;
import net.codejava.QLAM.Repositories.CountryRepository;
import net.codejava.QLAM.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/api/v1/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("")
    HashSet<AlbumsDto> getAllAlbums(){
        var albums = albumRepository.findAll();
        var songs = songRepository.findAll();
        var result = new HashSet<AlbumsDto>();
        for (var al: albums) {
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
            result.add(album);
        }
        return result;
    }
    @GetMapping("/recently")
    List<AlbumsDto> getAlbumRecently(){
        var albums = albumRepository.findByOrderByReleaseDateDesc();
        var songs = songRepository.findAll();
        var result = new ArrayList<AlbumsDto>();
        var count = 1;
        for (var al: albums) {
            if(count <= 9){
                count++;
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
                result.add(album);
            }
        }
        return result;
    }
    @GetMapping("/top")
    HashSet<AlbumsDto> getAllAlbumsTop(){
        var albums = albumRepository.findAll();
        var songs = songRepository.findAll();
        var result = new HashSet<AlbumsDto>();
        var count = 0;
        for (var al: albums) {
            count ++;
            if(count <= 4){
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
                result.add(album);
            }
        }
        return result;
    }
    @GetMapping("/{id}")
    AlbumSongs getAlbum(@PathVariable int id){
        var album =  albumRepository.findById(id);
        var songs = new HashSet<Song>();
        var songList = songRepository.findAll();
        var country = new HashSet<Country>();
        if(album.isPresent()){
            var result = new AlbumSongs();
            var al = album.get();
            result.id = al.getId();
            result.albumName = al.getAlbumName();
            result.albumImage = al.getAlbumImage();
            result.interestTimes = al.getInterestTimes();
            result.releaseDate = al.getReleaseDate();
            result.artist = al.getArtistAlbum();
            for (var song: songList) {
                if(song != null){
                    if(song.getAlbum()!= null)
                        if(song.getAlbum().getId() == id)
                            songs.add(song);

                    if(song.getCountry() != null)
                        country.add(song.getCountry());
                }
            }
            result.songs = songs;
            result.country = country;
            return result;
        }
        return new AlbumSongs();
    }
    @PostMapping("/insert")
    Album saveAlbum(@RequestBody Album newAlbum){

        Album foundAlbums = albumRepository.findByAlbumName((newAlbum.getAlbumName().trim()));
        if(foundAlbums != null)
            return new Album();
        return  albumRepository.save(newAlbum);
    }
    @PutMapping("/{id}")
    Album updateAlbum(@RequestBody Album newAlbum, @PathVariable int id){
        Album updateAlbum = albumRepository.findById(id)
                .map(album -> {
                    album.setAlbumName(newAlbum.getAlbumName());
                    album.setAlbumImage(newAlbum.getAlbumImage());
                    album.setReleaseDate(newAlbum.getReleaseDate());
                    album.setInterestTimes(newAlbum.getInterestTimes());
                    album.setArtistAlbum(newAlbum.getArtistAlbum());
                    return albumRepository.save(album);
                }).orElseGet(() ->{
                    return albumRepository.save(newAlbum);
                });
        return updateAlbum;
    }
    @PutMapping("/{albumid}/artist/{artistid}")
    Album addCountry(
            @PathVariable int albumid,
            @PathVariable int artistid
    ){
        try{
            Artist artist = artistRepository.findById(artistid).get();
            Album album = albumRepository.findById(albumid).get();

            album.addArtist(artist);
            albumRepository.save(album);
            return albumRepository.findById(albumid).get();
        }catch (Exception e){
            return new Album();
        }
    }

    @GetMapping("/countries/top/{num}")
    HashSet<AlbumsDto> getAllAlbumsTops(@PathVariable int num){
        var albums = albumRepository.findByOrderByInterestTimesDesc();
        var songs = songRepository.findAll();
        var result = new HashSet<AlbumsDto>();
        var count = 0;
        for (var al: albums) {
            if(count < num){
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
                count ++;
                result.add(album);
            }
        }
        return result;
    }

    @GetMapping("/countries/{countryid}/top/{num}")
    HashSet<AlbumsDto> getAllAlbumsTopByCountry(
            @PathVariable int countryid,
            @PathVariable int num
    ){
        var albums = albumRepository.findByOrderByInterestTimesDesc();
        var country = countryRepository.findById(countryid).get();
        var songs = songRepository.findByCountry(country);
        var result = new HashSet<AlbumsDto>();
        var count = 0;
        for (var al: albums) {
            if(count < num){
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
                if(album.country.contains(country)){
                    count ++;
                    result.add(album);
                }
            }
        }
        return result;
    }
}
