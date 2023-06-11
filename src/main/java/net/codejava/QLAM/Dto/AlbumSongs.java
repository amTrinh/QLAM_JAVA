package net.codejava.QLAM.Dto;

import net.codejava.QLAM.Models.Album;
import net.codejava.QLAM.Models.Artist;
import net.codejava.QLAM.Models.Country;
import net.codejava.QLAM.Models.Song;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlbumSongs {
    public int id;
    public String albumName;
    public int interestTimes;
    public Date releaseDate;
    public String albumImage;
    public Set<Artist> artist = new HashSet<>();
    public Set<Country> country = new HashSet<>();
    public Set<Song> songs;



    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }


    public AlbumSongs() {
    }
}
