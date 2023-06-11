package net.codejava.QLAM.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblArtist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name = "ArtistName", unique = true,nullable = false)
    private String artistName;

    @Column(name="Introduce", nullable = true)
    private String introduce;

    @Column(name = "ArtistImage", nullable = true)
    private String artistImage;

    @Column(name = "NumberOfFollower", nullable = false)
    private int numberOfFollower = 0;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="tblRepresentation",
            joinColumns = @JoinColumn(name="SingerId"),
            inverseJoinColumns = @JoinColumn(name="SongId")
    )
    private Set<Song> representation = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="tblComposing",
            joinColumns = @JoinColumn(name="ComposerId"),
            inverseJoinColumns = @JoinColumn(name="SongId")
    )
    private Set<Song> composing = new HashSet<>();


    @ManyToMany(mappedBy = "follow")
    private Set<User> follower = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="tblArtistAlbum",
            joinColumns = @JoinColumn(name="ArtistId"),
            inverseJoinColumns = @JoinColumn(name="AlbumId")
    )
    private Set<Album> artistAlbum = new HashSet<>();

    public int getNumberOfFollower() {
        return numberOfFollower;
    }

    public void setNumberOfFollower(int numberOfFollower) {
        this.numberOfFollower = numberOfFollower;
    }

    public Set<User> getFollower() {
        return follower;
    }

    public void setFollower(Set<User> follower) {
        this.follower = follower;
    }

    public Artist(){}

    public Artist(String artistName, String introduce, String artistImage) {
        this.artistName = artistName;
        this.introduce = introduce;
        this.artistImage = artistImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }

    public Set<Song> getRepresentation() {
        return representation;
    }

    public void setRepresentation(Set<Song> representation) {
        this.representation = representation;
    }

    public Set<Song> getComposing() {
        return composing;
    }

    public void setComposing(Set<Song> composing) {
        this.composing = composing;
    }
}
