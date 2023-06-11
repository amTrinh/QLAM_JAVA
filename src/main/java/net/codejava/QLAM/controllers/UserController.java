package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Models.*;
import net.codejava.QLAM.Repositories.ArtistRepository;
import net.codejava.QLAM.Repositories.PlaylistRepository;
import net.codejava.QLAM.Repositories.SongRepository;
import net.codejava.QLAM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("")
    List<User> getAllUser(){return userRepository.findAll();}

    @GetMapping("/{id}/likedSongs")
    List<Song> getAllLikedSongsByUser(@PathVariable int id){
        var user = userRepository.findById(id);

        List<Song> result = new ArrayList<Song>();
        if(user.isPresent()){
            result = user.get().getLikedSong().stream().toList();
        }
        return result;
    }

    @GetMapping("/{id}/likedPlaylists")
    List<Playlist> getAllLikedPlaylistsByUser(@PathVariable int id){
        var user = userRepository.findById(id);

        List<Playlist> result = new ArrayList<Playlist>();
        if(user.isPresent()){
            result = user.get().getLikedPlaylist().stream().toList();
        }
        return result;
    }

    @GetMapping("/{id}")
    Optional<User> getUser(@PathVariable int id){return userRepository.findById(id);}

    @PostMapping("")
    User saveUser(@RequestBody User nguoidung){
        User foundNguoiDungs = userRepository.findByUserName(nguoidung.getUserName());
        if(foundNguoiDungs != null)
            return new User();
        return userRepository.save(nguoidung);
    }

    @PostMapping("/account/login")
    User login(@RequestBody LoginForm login ) {
            User u = userRepository.findByUserName(login.getUserName());
            User p = userRepository.findByPassword(login.getPassword());
            if(u!= null){
                if(u.equals(p))
                    return u;
            }
            return new User();
    }

    @PutMapping("/{userid}/songs/{songid}")
    User addLikedSong(
            @PathVariable int userid,
            @PathVariable int songid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Song song = songRepository.findById(songid).get();
            song.setLikedTimes(song.getLikedTimes() + 1);
            songRepository.save(song);
            user.addLikedSong(song);
            return userRepository.save(user);
        }catch(Exception e){
            return new User();
        }
    }

    @PutMapping("/{userid}/edit")
    User editUsername(
            @PathVariable int userid,
            @RequestBody User userin
    ){
        try{
            User user = userRepository.findById(userid).get();
            user.setUserName(userin.getUserName());
            return userRepository.save(user);
        }catch(Exception e){
            return new User();
        }
    }

    @PutMapping("/{userid}/songs/{songid}/unlike")
    User removeLikedSong(
            @PathVariable int userid,
            @PathVariable int songid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Song song = songRepository.findById(songid).get();
            song.setLikedTimes(song.getLikedTimes() - 1);
            songRepository.save(song);
            user.removeLikedSong(song);
            return userRepository.save(user);
        }catch(Exception e){
            return new User();
        }
    }

    @PutMapping("/{userid}/playlists/{playlistid}")
    User addLikedPlaylist(
            @PathVariable int userid,
            @PathVariable int playlistid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Playlist pl = playlistRepository.findById(playlistid).get();
            user.addLikedPlaylist(pl);
            return userRepository.save(user);
        }catch(Exception e){
            return new User();
        }
    }

    @PutMapping("/{userid}/playlists/{playlistid}/unlike")
    User removeLikedPlaylist(
            @PathVariable int userid,
            @PathVariable int playlistid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Playlist pl = playlistRepository.findById(playlistid).get();
            user.removeLikedPlaylist(pl);
            return userRepository.save(user);
        }catch(Exception e){
            return new User();
        }
    }

    @PutMapping("/{userid}/follow/{artistid}")
    User addFollower(
            @PathVariable int userid,
            @PathVariable int artistid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Artist at = artistRepository.findById(artistid).get();
            user.addFollower(at);

             userRepository.save(user);
            at.setNumberOfFollower(at.getNumberOfFollower() + 1);

            artistRepository.save(at);
             return userRepository.findById(userid).get();
        }catch(Exception e){
            return new User();
        }
    }
    @PutMapping("/{userid}/unfollow/{artistid}")
    User unFollower(
            @PathVariable int userid,
            @PathVariable int artistid
    ){
        try{
            User user = userRepository.findById(userid).get();
            Artist at = artistRepository.findById(artistid).get();

            user.removeFollower(at);

            userRepository.save(user);
            at.setNumberOfFollower(at.getNumberOfFollower() - 1);

            artistRepository.save(at);
            return userRepository.findById(userid).get();
        }catch(Exception e){
            return new User();
        }
    }

    @GetMapping("/{userid}/isfollow/{artistid}")
    Boolean isFollow(@PathVariable int userid, @PathVariable int artistid){
        var artist = artistRepository.findById(artistid);
        if(artist.isPresent()){
            for (var fl: artist.get().getFollower()                 ) {
                if(fl.getId() == userid){
                    return true;
                }
            }
        }
        return false;
    }
}
