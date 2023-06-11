package net.codejava.QLAM.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import net.codejava.QLAM.Models.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LikedSong {
    public int id;
    public String songName;
    public String lyric;
    public int likedTimes;
    public int hearedTimes;
    public String timeLimit;
    public String songImage;
    public String songLink;
    public String playImage;
    public Date releaseDate;
    public Set<Artist> representation = new HashSet<>();
    public Set<Artist> composing = new HashSet<>();
    public Country country;
    public Genre genre;
    public Album album;
    public int isFavorite;
}
