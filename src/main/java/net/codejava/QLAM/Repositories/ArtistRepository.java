package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.Album;
import net.codejava.QLAM.Models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
   Artist findByArtistName(String artistName);
   List<Artist> findByArtistNameContains(String search);
}
