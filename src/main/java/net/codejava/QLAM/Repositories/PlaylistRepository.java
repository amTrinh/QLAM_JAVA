package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    Playlist findByPlaylistName(String playlistName);

}
