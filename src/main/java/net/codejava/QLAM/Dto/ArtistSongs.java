package net.codejava.QLAM.Dto;

import net.codejava.QLAM.Models.Album;
import net.codejava.QLAM.Models.Artist;
import net.codejava.QLAM.Models.Song;

import java.util.Set;

public class ArtistSongs {
    public Artist  artist;
    public Set<Song> songs;
    public Set<AlbumsDto> albums;

    public ArtistSongs(Artist artist, Set<Song> songs, Set<AlbumsDto> albums) {
        this.artist = artist;
        this.songs = songs;
        this.albums = albums;
    }

    public Set<AlbumsDto> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumsDto> albums) {
        this.albums = albums;
    }

    public ArtistSongs(Artist artist, Set<Song> songs) {
        this.artist = artist;
        this.songs = songs;
    }

    public ArtistSongs() {
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
