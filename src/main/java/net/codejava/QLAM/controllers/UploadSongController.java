package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Dto.PlaylistUser;
import net.codejava.QLAM.Models.Playlist;
import net.codejava.QLAM.Models.UploadSong;
import net.codejava.QLAM.Models.User;
import net.codejava.QLAM.Repositories.UploadSongRepository;
import net.codejava.QLAM.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/uploadSong")
public class UploadSongController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadSongRepository uploadSongRepository;

    @GetMapping("")
    List<UploadSong> getAllUploadSongs(){
        return uploadSongRepository.findAll();
    }

    @GetMapping("/user/{userid}")
    User getUploadSongsByUserId(@PathVariable int userid){
        var result = userRepository.findById(userid);
        return result.get();
    }

    @GetMapping("/usertemp/{userid}")
    List<UploadSong> getUploadSongsByUserIdTemp(@PathVariable int userid){
        var result = uploadSongRepository.findByUserIdTemp(userid);
        return result;
    }

    @PostMapping("/{userid}/insert")
    User savePlaylist(@PathVariable Integer userid,@RequestBody UploadSong newUploadSong){
        Optional<User> user = userRepository.findById(userid);
        UploadSong  uploadedSong = uploadSongRepository.findBySongName(newUploadSong.getSongName());
        if(uploadedSong != null){
            return new User();
        }
        if(user.isPresent() && newUploadSong != null){
            newUploadSong.setUserIdTempd(userid);
            uploadSongRepository.save(newUploadSong);
            user.get().addUploadSong(newUploadSong);
            return userRepository.save(user.get());
        }
        return new User();
    }

    @DeleteMapping("/{songid}/users/{userid}")
    Boolean deleteUploadSongById(@PathVariable int songid, @PathVariable int userid){
        boolean exists = uploadSongRepository.existsById(songid);
        if(!exists)
            return false;
        Optional<UploadSong> foundSong = uploadSongRepository.findById(songid);
        if(foundSong.get().getUserIdTempd() != userid)
            return false;
        uploadSongRepository.deleteById(songid);
        return true;
    }

}
