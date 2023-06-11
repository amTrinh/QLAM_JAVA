package net.codejava.QLAM.Dto;

import net.codejava.QLAM.Models.Playlist;
import net.codejava.QLAM.Models.Song;

import java.util.HashSet;
import java.util.Set;

public class PlaylistUser {
    public int id;
    public String playlistName;
    public String playlistImage;
    public String userName;
    public Set<Song> songPlaylist = new HashSet<>();

    public PlaylistUser(int id, String playlistName, String playlistImage, String userName, Set<Song> songPlaylist) {
        this.id = id;
        this.playlistName = playlistName;
        this.playlistImage = playlistImage;
        this.userName = userName;
        this.songPlaylist = songPlaylist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Song> getSongPlaylist() {
        return songPlaylist;
    }

    public void setSongPlaylist(Set<Song> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }

    public PlaylistUser() {
    }
}
