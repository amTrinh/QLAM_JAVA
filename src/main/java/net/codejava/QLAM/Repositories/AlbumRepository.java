package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.Album;
import net.codejava.QLAM.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findByAlbumName(String albumName);
    List<Album> findByAlbumNameContains(String search);
    List<Album> findByOrderByReleaseDateDesc();
    List<Album> findByOrderByInterestTimesDesc();
}
