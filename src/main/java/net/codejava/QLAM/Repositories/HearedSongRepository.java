package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.HearedSong;
import net.codejava.QLAM.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HearedSongRepository extends JpaRepository<HearedSong, Integer> {
    List<HearedSong> findByUser(User user);
    List<HearedSong> findByUserOrderByCreatedDateDesc(User user);
    List<HearedSong> findByUserOrderByCreatedDateAsc(User user);

}
