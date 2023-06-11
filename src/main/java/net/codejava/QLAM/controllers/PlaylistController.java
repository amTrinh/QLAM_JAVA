package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Dto.PlaylistUser;
import net.codejava.QLAM.Models.Song;
import net.codejava.QLAM.Models.Playlist;
import net.codejava.QLAM.Models.User;
import net.codejava.QLAM.Repositories.SongRepository;
import net.codejava.QLAM.Repositories.PlaylistRepository;
import net.codejava.QLAM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    HashSet<PlaylistUser> getAllPlaylists(){
        var playlists = playlistRepository.findAll();
        var result = new HashSet<PlaylistUser>();
        for (var pl: playlists) {
            if( pl.getUser()!= null){
                var playlistU = new PlaylistUser();
                playlistU.setId(pl.getId());
                playlistU.setPlaylistName(pl.getPlaylistName());
                playlistU.setPlaylistImage(pl.getPlaylistImage());
                playlistU.setUserName(pl.getUser().getUserName());
                playlistU.setSongPlaylist(pl.getSongPlaylist());
                result.add(playlistU);
            }
        }
        return result;
    }

    @GetMapping("/user/{id}/created")
    HashSet<PlaylistUser> getAllCreatedPlaylists( @PathVariable int id){
        var playlists = playlistRepository.findAll();
        var result = new HashSet<PlaylistUser>();
        for (var pl: playlists) {
            if( pl.getUser()!= null){
                if(pl.getUser().getId() == id){
                    var playlistU = new PlaylistUser();
                    playlistU.setId(pl.getId());
                    playlistU.setPlaylistName(pl.getPlaylistName());
                    playlistU.setPlaylistImage(pl.getPlaylistImage());
                    playlistU.setUserName(pl.getUser().getUserName());
                    playlistU.setSongPlaylist(pl.getSongPlaylist());
                    result.add(playlistU);
                }
            }
        }
        return result;
    }
    @GetMapping("/user/{id}/liked")
    HashSet<PlaylistUser> getAllLikedPlaylists( @PathVariable int id){
        var playlists = playlistRepository.findAll();
        var result = new HashSet<PlaylistUser>();
        for (var pl: playlists) {
            for (var liked: pl.getLikedPlaylist()) {
                if(liked.getId() == id && pl.getUser() != null){
                    var playlistU = new PlaylistUser();
                    playlistU.setId(pl.getId());
                    playlistU.setPlaylistName(pl.getPlaylistName());
                    playlistU.setPlaylistImage(pl.getPlaylistImage());
                    playlistU.setUserName(pl.getUser().getUserName());
                    playlistU.setSongPlaylist(pl.getSongPlaylist());
                    result.add(playlistU);
                }
            }
        }
        return result;
    }

    @GetMapping("/top")
    HashSet<PlaylistUser> getAllPlaylistsTop(){
        var playlists = playlistRepository.findAll();
        var result = new HashSet<PlaylistUser>();
        int a = 0;
        for (var pl: playlists) {
            a++;
            if(a <= 4){
                var playlistU = new PlaylistUser();
                playlistU.setId(pl.getId());
                playlistU.setPlaylistName(pl.getPlaylistName());
                playlistU.setPlaylistImage(pl.getPlaylistImage());
                playlistU.setUserName(pl.getUser().getUserName());
                playlistU.setSongPlaylist(pl.getSongPlaylist());
                result.add(playlistU);
            }else{break;}

        }
        return result;
    }

    @GetMapping("/manysongs")
    List<PlaylistUser> getAllPlaylistsManysongs(){
        var playlists = playlistRepository.findAll();
        var result = new ArrayList<PlaylistUser>();
        int a = 0;
        for (var pl: playlists) {
            if(a < 4 && pl.getSongPlaylist().size() >= 3){
                a++;
                var playlistU = new PlaylistUser();
                playlistU.setId(pl.getId());
                playlistU.setPlaylistName(pl.getPlaylistName());
                playlistU.setPlaylistImage(pl.getPlaylistImage());
                playlistU.setUserName(pl.getUser().getUserName());
                playlistU.setSongPlaylist(pl.getSongPlaylist());
                result.add(playlistU);
            }
        }
        return result;
    }
    @GetMapping("/{id}")
    PlaylistUser getAllPlaylistsById( @PathVariable int id){
        var pl = playlistRepository.findById(id).get();
        var playlistU = new PlaylistUser();
        playlistU.setId(pl.getId());
        playlistU.setPlaylistName(pl.getPlaylistName());
        playlistU.setPlaylistImage(pl.getPlaylistImage());
        playlistU.setUserName(pl.getUser().getUserName());
        playlistU.setSongPlaylist(pl.getSongPlaylist());
        return playlistU;
    }

    @PostMapping("/{userid}/insert")
    String savePlaylist(@PathVariable Integer userid,@RequestBody Playlist newPlaylist){
        Playlist foundPlaylists = playlistRepository.findByPlaylistName((newPlaylist.getPlaylistName().trim()));
        Optional<User> user = userRepository.findById(userid);
        if(foundPlaylists != null || !user.isPresent())
            return "failed";
        newPlaylist.setUser(user.get());
        var newpl = playlistRepository.save(newPlaylist);
        Playlist pl = playlistRepository.findById(newpl.getId()).get();
        user.get().addLikedPlaylist(pl);
        userRepository.save(user.get());
        return "success";
    }
    @PutMapping("/{userid}/edit/{playlistid}")
    String EditPlaylist(
            @PathVariable Integer playlistid,
            @PathVariable Integer userid,
            @RequestBody Playlist pl)
    {
        var foundPlaylist = playlistRepository
                .findById(playlistid);
        Optional<User> user = userRepository.findById(userid);
        if(foundPlaylist == null)
            return "failed";
        foundPlaylist.get().setPlaylistName(pl.getPlaylistName());
        playlistRepository.save(foundPlaylist.get());
        return "success";
    }

    @PutMapping("/{playlistid}/songs/{songid}")
    Playlist addBaiHatToPlaylist(
            @PathVariable int playlistid,
            @PathVariable int songid
    ){
        try{
            Playlist playlist = playlistRepository.findById(playlistid).get();
            Song song = songRepository.findById(songid).get();

            playlist.addSong(song);
            return playlistRepository.save(playlist);
        }catch (Exception e){
            return new Playlist();
        }
    }

    @PutMapping("/{playlistid}/user/{userid}")
    public String deleteById(
            @PathVariable Integer playlistid,
            @PathVariable Integer userid
    ) {
        try {
            var pl = playlistRepository.findById(playlistid);
            var us = userRepository.findById(userid);
            if(pl.isPresent()){
                if(pl.get().getUser().getId() == userid){
                    pl.get().setUser(null);
                    playlistRepository.save(pl.get());
//                    playlistRepository.delete(pl.get());
                }
            }
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
