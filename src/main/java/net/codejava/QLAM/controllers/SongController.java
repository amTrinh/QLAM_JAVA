package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Dto.SongDto;
import net.codejava.QLAM.Models.*;
import net.codejava.QLAM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/songs")
public class SongController {
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

    @GetMapping("")
    List<Song> getAllSongs(){
        return songRepository.findAll();
    }

//    @GetMapping("/{id}")
//    List<SongDto> getAllSongsWithLikedSong(@PathVariable int id){
//        var songs = songRepository.findAll();
//
//        var user = userRepository.findById(id);
//
//        List<Song> likedSong = new ArrayList<Song>();
//        List<Playlist> playlists = new ArrayList<Playlist>();
//        if(user.isPresent()){
//            likedSong = user.get().getLikedSong().stream().toList();
//            playlists = user.get().getPlaylist().stream().toList();
//        }
//        List<SongDto> result = new ArrayList<SongDto>();
//
//        for (Song song: songs) {
//            var songDto = new SongDto();
//            songDto.id = song.getId();
//            songDto.songImage = song.getSongImage();
//            songDto.songName = song.getSongName();
//            songDto.hearedTimes = song.getHearedTimes();
//            songDto.likedTimes = song.getLikedTimes();
//            songDto.songLink = s
//
//        }
//    }

    @GetMapping("/{id}")
    Song getSongById(@PathVariable int id){
        Optional<Song> foundBaiHat = songRepository.findById(id);
        return foundBaiHat.isPresent() ?
            foundBaiHat.get() :
            new Song();
    }

    @GetMapping("/recently")
    List<Song> getSongRecently(){
       var foundBaiHat = songRepository.findByOrderByReleaseDateDesc();
       var result = new ArrayList<Song>();
       var count = 0;
        for (var bh: foundBaiHat) {
            count++;
            if(count <= 9)
                result.add(bh);
        }
        return result;
    }

    @GetMapping("/search/{search}")
    List<Song> searchSongs(@PathVariable String search){
        var foundBaiHat = songRepository.findBySongNameContains(search);
        var result = new ArrayList<Song>();
        var count = 0;
        for (var bh: foundBaiHat) {
            count++;
            if(count <= 9)
                result.add(bh);
        }
        return result;
    }

    @GetMapping("/ontop")
    List<Song> getSongOnTop(){
        var foundBaiHat = songRepository.findAll(Sort.by("ReleaseDate").descending().and(Sort.by("HearedTimes")));
        var result = new ArrayList<Song>();
        var count = 0;
        for (var bh: foundBaiHat) {
            count++;
            if(count <= 9)
                result.add(bh);
        }
        return result;
    }

    @PostMapping("")
    Song saveBaiHat(@RequestBody Song newBaiHat){
        Song foundBaiHats = songRepository.findBySongName((newBaiHat.getSongName().trim()));

        if(foundBaiHats != null)
            return new Song();
        if(newBaiHat.getCountry() != null){
            Country country = countryRepository.findByCountryName(newBaiHat.getCountry().getCountryName());
            if(country != null){
                newBaiHat.setCountry(country);
            }
        }
        if(newBaiHat.getGenre() != null){
            Genre genre = genreRepository.findByGenreName(newBaiHat.getGenre().getGenreName());
            if(genre != null){
                newBaiHat.setGenre(genre);
            }
        }

        return songRepository.save(newBaiHat);
    }
    //update, insert is update if found, otherwise insert
    @PutMapping("/{id}")
    Song updateBaiHat(@RequestBody Song newBaiHat, @PathVariable int id){
        Country country = countryRepository.findByCountryName(newBaiHat.getCountry().getCountryName());
        Song updateBaiHat = songRepository.findById(id)
                .map(baihat -> {
                   baihat.setSongName(newBaiHat.getSongName());
                   baihat.setSongImage(newBaiHat.getSongImage());
                   baihat.setLyric(newBaiHat.getLyric());
                   baihat.setHearedTimes(newBaiHat.getHearedTimes());
                   baihat.setLikedSong(newBaiHat.getLikedSong());
                   baihat.setTimeLimit(newBaiHat.getTimeLimit());
                   baihat.setReleaseDate(newBaiHat.getReleaseDate());
                   baihat.setSongLink(newBaiHat.getSongLink());
                   baihat.setCountry(country);
                   return songRepository.save(baihat);
                }).orElseGet(() ->{
                    return songRepository.save(newBaiHat);
                });
        return updateBaiHat;
    }

    @DeleteMapping("/{id}")
    Boolean deleteBaiHat(@PathVariable int id){
        boolean exists = songRepository.existsById(id);
        if(!exists)
            return false;
        songRepository.deleteById(id);
        return true;
    }

    @PutMapping("/{songid}/country/{countryid}")
    Song addCountry(
            @PathVariable int songid,
            @PathVariable int countryid
    ){
        try{
            Country quocGia = countryRepository.findById(countryid).get();
            Song baiHat = songRepository.findById(songid).get();

            baiHat.setCountry(quocGia);
            return songRepository.save(baiHat);
        }catch (Exception e){
            return new Song();
        }
    }

    @PutMapping("/{songid}/genre/{genreid}")
    Song addGenre(
            @PathVariable int songid,
            @PathVariable int genreid
    ){
        try{
            Genre genre = genreRepository.findById(genreid).get();
            Song baiHat = songRepository.findById(songid).get();

            baiHat.setGenre(genre);
            return songRepository.save(baiHat);
        }catch (Exception e){
            return new Song();
        }
    }

    @PutMapping("/{songid}/album/{albumid}")
    Song addAlbum(
            @PathVariable int songid,
            @PathVariable int albumid
    ){
        try{
            Album album = albumRepository.findById(albumid).get();
            Song baiHat = songRepository.findById(songid).get();

            baiHat.setAlbum(album);
            return songRepository.save(baiHat);
        }catch (Exception e){
            return new Song();
        }
    }

    @PutMapping("/{songid}/representation/{artistid}")
    Song Representation(
            @PathVariable int songid,
            @PathVariable int artistid
    ){
        try{
            Artist artist = artistRepository.findById(artistid).get();
            Song song = songRepository.findById(songid).get();

            song.addRepresentation(artist);
            songRepository.save(song);
            return songRepository.findById(songid).get();
        }catch (Exception e){
            return new Song();
        }
    }

    @PutMapping("/{songid}/composing/{artistid}")
    Song Composing(
            @PathVariable int songid,
            @PathVariable int artistid
    ){
        try{
            Artist artist = artistRepository.findById(artistid).get();
            Song song = songRepository.findById(songid).get();

            song.addComposing(artist);
            songRepository.save(song);
            return songRepository.findById(songid).get();
        }catch (Exception e){
            return new Song();
        }
    }

}
