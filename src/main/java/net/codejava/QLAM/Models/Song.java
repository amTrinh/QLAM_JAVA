package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblSong")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="SongName",unique = true,nullable = false)
    private String songName;

    @Column(name="Lyric",nullable = true)
    private String lyric;

    @Column(name="LikedTimes",nullable = false)
    private int likedTimes;

    @Column(name="HearedTimes",nullable = false)
    private int hearedTimes;

    @Column(name="TimeLimit",nullable = true)
    private String timeLimit;

    @Column(name="SongIamge",nullable = true)
    private String songImage;

    @Column(name="SongLink",nullable = false)
    private String songLink;

    @Column(name="PlayImage",nullable = true)
    private String playImage;

    @Column(name="ReleaseDate",nullable = true)
    private Date releaseDate = new Date();

    @JsonIgnore
    @ManyToMany(mappedBy = "songPlaylist")
    private Set<Playlist> songPlaylist = new HashSet<>();


    @ManyToMany(mappedBy = "representation")
    private Set<Artist> representation = new HashSet<>();

    @ManyToMany(mappedBy = "composing")
    private Set<Artist> composing = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "likedSong")
    private Set<User> likedSong = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "CountryId", referencedColumnName = "Id")
    private Country country;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "GenreId", referencedColumnName = "Id")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "AlbumId", referencedColumnName = "Id")
    private Album album;


//    @Transient
//    private int KhoangTGPH;

    public Song(){}

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

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public int getLikedTimes() {
        return likedTimes;
    }

    public void setLikedTimes(int likedTimes) {
        this.likedTimes = likedTimes;
    }

    public int getHearedTimes() {
        return hearedTimes;
    }

    public void setHearedTimes(int hearedTime) {
        this.hearedTimes = hearedTime;
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

    public String getPlayImage() {
        return playImage;
    }

    public void setPlayImage(String playImage) {
        this.playImage = playImage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Playlist> getSongPlaylist() {
        return songPlaylist;
    }

    public void setSongPlaylist(Set<Playlist> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }

    public Set<Artist> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Set<Artist> representation) {
        this.representation = representation;
    }

    public Set<User> getLikedSong() {
        return likedSong;
    }

    public void setLikedSong(Set<User> likedSong) {
        this.likedSong = likedSong;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Artist> getComposing() {
        return composing;
    }

    public void setComposing(Set<Artist> composing) {
        this.composing = composing;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Song(String songName, String lyric, int likedTimes, int hearedTime, String timeLimit, String songImage, String songLink, String playImage, Date releaseDate) {
        this.songName = songName;
        this.lyric = lyric;
        this.likedTimes = likedTimes;
        this.hearedTimes = hearedTime;
        this.timeLimit = timeLimit;
        this.songImage = songImage;
        this.songLink = songLink;
        this.playImage = playImage;
        this.releaseDate = releaseDate;
    }

    public Song(String songName, String lyric, int likedTimes, int hearedTime, String timeLimit, String songImage, String songLink, String playImage, Date releaseDate, Set<Playlist> songPlaylist) {
        this.songName = songName;
        this.lyric = lyric;
        this.likedTimes = likedTimes;
        this.hearedTimes = hearedTime;
        this.timeLimit = timeLimit;
        this.songImage = songImage;
        this.songLink = songLink;
        this.playImage = playImage;
        this.releaseDate = releaseDate;
        this.songPlaylist = songPlaylist;
    }

    public void addRepresentation(Artist a){
        this.representation.add(a);
    }
    public void addComposing(Artist a){
        this.composing.add(a);
    }

}
