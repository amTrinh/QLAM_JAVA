package net.codejava.QLAM.Dto;

import net.codejava.QLAM.Models.Artist;
import net.codejava.QLAM.Models.Country;
import net.codejava.QLAM.Models.Song;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Search {
    public Set<AlbumsDto> albums = new HashSet<>();
    public List<Song> songs = new ArrayList<>();
    public List<Artist> artists = new ArrayList<>();

    public Search(Set<AlbumsDto> album, List<Song> songs, List<Artist> artists) {
        this.albums = album;
        this.songs = songs;
        this.artists = artists;
    }
}
