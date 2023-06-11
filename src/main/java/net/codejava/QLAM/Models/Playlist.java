package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblPlaylist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="PlaylistName",unique = true,nullable = false)
    private String playlistName;

    @Column(name="PlaylistImage",nullable = true)
    private String playlistImage = "https://res.cloudinary.com/dlkakcrde/image/upload/v1685866494/pl1_vntdar.jpg";

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;

    @ManyToMany
    @JoinTable(name="tblSongPlaylist",
            joinColumns = @JoinColumn(name="PlayListId"),
            inverseJoinColumns = @JoinColumn(name="SongId")
    )
    private Set<Song> songPlaylist = new HashSet<>();

    @ManyToMany(mappedBy = "likedPlaylist")
    private Set<User> likedPlaylist = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getLikedPlaylist() {
        return likedPlaylist;
    }

    public void setLikedPlaylist(Set<User> likedPlaylist) {
        this.likedPlaylist = likedPlaylist;
    }

    public Playlist(int id, String playlistName, String playlistImage, User user, Set<Song> songPlaylist, Set<User> likedPlaylist) {
        this.id = id;
        this.playlistName = playlistName;
        this.playlistImage = playlistImage;
        this.user = user;
        this.songPlaylist = songPlaylist;
        this.likedPlaylist = likedPlaylist;
    }

    public String getPlaylistImage() {
        return playlistImage;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }

    public Playlist() {
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

    public Set<Song> getSongPlaylist() {
        return songPlaylist;
    }

    public void setSongPlaylist(Set<Song> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    public void addSong(Song song) {
        this.songPlaylist.add(song);
    }

    public void deleleted(){

    }
}
