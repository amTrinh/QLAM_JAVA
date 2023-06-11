package net.codejava.QLAM.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import net.codejava.QLAM.Models.Artist;
import net.codejava.QLAM.Models.Country;
import net.codejava.QLAM.Models.Song;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AlbumsDto {
    public int id;
    public String albumName;
    public int interestTimes;
    public Date releaseDate;
    public String albumImage;
    public Set<Artist> artist = new HashSet<>();
    public Set<Country> country = new HashSet<>();
    public AlbumsDto() {
    }

    public AlbumsDto(int id, String albumName, int interestTimes, Date releaseDate, String albumImage, Set<Artist> artist, Set<Country> country) {
        this.id = id;
        this.albumName = albumName;
        this.interestTimes = interestTimes;
        this.releaseDate = releaseDate;
        this.albumImage = albumImage;
        this.artist = artist;
        this.country = country;
    }


}
