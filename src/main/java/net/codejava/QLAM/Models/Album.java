package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblAlbum")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="AlbumName",unique = true,nullable = false)
    private String albumName;

    @Column(name="InterestTimes", nullable = false)
    private int interestTimes;

    @Column(name="ReleaseDate", nullable = true)
    private Date releaseDate = new Date();

    @Column(name="AlbumImage", nullable = true)
    private String albumImage;

    @ManyToMany(mappedBy = "artistAlbum")
    private Set<Artist> artistAlbum = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    private Set<Song> songs = new HashSet<>();

    public  Album(){}

    public Album(String albumName, int interestTimes, Date realseDate, String albumImage) {
        this.albumName = albumName;
        this.interestTimes = interestTimes;
        this.releaseDate = realseDate;
        this.albumImage = albumImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getInterestTimes() {
        return interestTimes;
    }

    public void setInterestTimes(int interestTimes) {
        this.interestTimes = interestTimes;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Set<Artist> getArtistAlbum() {
        return artistAlbum;
    }

    public void setArtistAlbum(Set<Artist> artistAlbum) {
        this.artistAlbum = artistAlbum;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addArtist(Artist a){
        this.artistAlbum.add(a);
    }
}
