package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.UploadSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadSongRepository extends JpaRepository<UploadSong, Integer> {
    List<UploadSong> findByUserIdTemp(int userIdTemp);
    UploadSong findBySongName(String songName);
}
