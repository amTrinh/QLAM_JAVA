package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Models.HearedSong;
import net.codejava.QLAM.Models.Song;
import net.codejava.QLAM.Models.User;
import net.codejava.QLAM.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path="/api/v1/hearedsongs")
public class HearedSongController {
    @Autowired
    private HearedSongRepository hearedSongRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    List<HearedSong> getAllHearedSongs(){return hearedSongRepository.findAll();}

    @GetMapping("/{id}")
    List<Song> getAllHearedSongsByUser(@PathVariable int id){
        User user = userRepository.findById(id).get();
        var hearedSongs = hearedSongRepository.findByUserOrderByCreatedDateDesc(user);
        var songs = new ArrayList<Song>();
        for (var heardsong : hearedSongs) {
                    songs.add(heardsong.getSong());
        }

        return songs;
    }
    @PostMapping("/{userid}/songs/{songid}")
    HearedSong addHearedSong(
            @PathVariable int userid,
            @PathVariable int songid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Song song = songRepository.findById(songid).get();
            var hearedSongs = hearedSongRepository.findAll();
            for (var hearedSong : hearedSongs                 ) {
                if(hearedSong.getSong().getId() == songid && hearedSong.getUser().getId() == userid){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    hearedSong.setCreatedDate(date);
                    hearedSongRepository.save(hearedSong);
                    song.setHearedTimes(song.getHearedTimes() +1);
                    songRepository.save(song);
                    return hearedSong;
                }
            }
            HearedSong hearedSong = new HearedSong();
            hearedSong.setUser(user);
            hearedSong.setSong(song);
            song.setHearedTimes(song.getHearedTimes() +1);
            songRepository.save(song);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            hearedSong.setCreatedDate(date);

            return hearedSongRepository.save(hearedSong);
        }catch(Exception e){
            return new HearedSong();
        }
    }


}
