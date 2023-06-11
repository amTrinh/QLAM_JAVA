package net.codejava.QLAM.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="tblHearedSong")
public class HearedSong {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int id;

    @Column(name="CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="UserId", referencedColumnName = "Id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="BaiHatId", referencedColumnName = "Id")
    private Song song;

    public HearedSong() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public HearedSong(Date createdDate, User user, Song song) {
        this.createdDate = createdDate;
        this.user = user;
        this.song = song;
    }
}
