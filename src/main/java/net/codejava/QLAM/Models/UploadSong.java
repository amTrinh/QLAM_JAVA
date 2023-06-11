package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="tblUploadSong")
public class UploadSong {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="SongName",unique = true,nullable = false)
    private String songName;

    @Column(name="TimeLimit",nullable = true)
    private String timeLimit;

    @Column(name="SongImage",nullable = true)
    private String songImage;

    @Column(name="SongLink",nullable = false)
    private String songLink;

    @Column(name="Artist",nullable = false)
    private String artist;

    public int getUserIdTempd() {
        return userIdTemp;
    }

    public void setUserIdTempd(int userIdTempd) {
        this.userIdTemp = userIdTempd;
    }

    public UploadSong(int id, String songName, String timeLimit, String songImage, String songLink, String artist, int userIdTempd, User user) {
        this.id = id;
        this.songName = songName;
        this.timeLimit = timeLimit;
        this.songImage = songImage;
        this.songLink = songLink;
        this.artist = artist;
        this.userIdTemp = userIdTempd;
        this.user = user;
    }

    @Column(name="UserIdTemp")
    private int userIdTemp;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;


    public UploadSong() {
    }

    public UploadSong(int id, String songName, String timeLimit, String songImage, String songLink, String artist, User user) {
        this.id = id;
        this.songName = songName;
        this.timeLimit = timeLimit;
        this.songImage = songImage;
        this.songLink = songLink;
        this.artist = artist;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
