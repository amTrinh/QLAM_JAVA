package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="UserName",unique = true,nullable = false)
    private String userName;

    @Column(name="Password",unique = true,nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UploadSong> uploadSongs = new HashSet<>();


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="tblLikedSong",
            joinColumns = @JoinColumn(name="UserId"),
            inverseJoinColumns = @JoinColumn(name="SongId")
    )
    private Set<Song> likedSong = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="tblLikedPlaylist",
            joinColumns = @JoinColumn(name="UserId"),
            inverseJoinColumns = @JoinColumn(name="PlaylistId")
    )
    private Set<Playlist> likedPlaylist = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="tblFollow",
            joinColumns = @JoinColumn(name="UserId"),
            inverseJoinColumns = @JoinColumn(name="ArtistId")
    )
    private Set<Artist> follow = new HashSet<>();

    public User() {
    }

    public Set<Artist> getFollow() {
        return follow;
    }

    public void setFollow(Set<Artist> follow) {
        this.follow = follow;
    }

    public Set<Playlist> getLikedPlaylist() {
        return likedPlaylist;
    }

    public void setLikedPlaylist(Set<Playlist> likedPlaylist) {
        this.likedPlaylist = likedPlaylist;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Song> getLikedSong() {
        return likedSong;
    }

    public void setLikedSong(Set<Song> likedSong) {
        this.likedSong = likedSong;
    }

    public Set<UploadSong> getUploadSongs() {
        return uploadSongs;
    }

    public void setUploadSongs(Set<UploadSong> uploadSongs) {
        this.uploadSongs = uploadSongs;
    }

    public void addLikedSong(Song song){
        likedSong.add(song);
    }
    public void removeLikedSong(Song song){
        likedSong.remove(song);
    }
    public void addLikedPlaylist(Playlist playlist){
        likedPlaylist.add(playlist);
    }
    public void removeLikedPlaylist(Playlist playlist){
        likedPlaylist.remove(playlist);
    }
    public void addFollower(Artist artist){
        follow.add(artist);
    }
    public void removeFollower(Artist artist){
        follow.remove(artist);
    }
    public void addUploadSong(UploadSong song){
        uploadSongs.add(song);
    }
    public void removeUploadSong(UploadSong song){
        uploadSongs.remove(song);
    }
}
