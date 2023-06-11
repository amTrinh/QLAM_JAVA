package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.Country;
import net.codejava.QLAM.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {
    Song findBySongName(String songName);
    List<Song> findBySongNameContains(String search);
    List<Song> findByOrderByReleaseDateDesc();
    List<Song> findByCountry(Country country);
}
