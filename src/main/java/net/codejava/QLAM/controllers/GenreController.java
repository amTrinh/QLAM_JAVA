package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Models.ResponseObject;
import net.codejava.QLAM.Models.Genre;
import net.codejava.QLAM.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;
    @GetMapping("")
    List<Genre> getAllGenres(){return genreRepository.findAll();}

    @GetMapping("/{id}")
    Optional<Genre> getGenreById(@PathVariable int id){
        return genreRepository.findById(id);
    }

    @PostMapping("")
    Genre saveGenre(@RequestBody Genre newGenre){
        Genre foundGenres = genreRepository.findByGenreName(newGenre.getGenreName().trim());
        if(foundGenres != null)
            return new Genre();
        return genreRepository.save(newGenre);
    }

    @PutMapping("/{id}")
    Genre updateGenre(@RequestBody Genre newGenre, @PathVariable int id){
        Genre updateGenre = genreRepository.findById(id)
                .map(genre -> {
                    genre.setGenreName(newGenre.getGenreName());
                    return genreRepository.save(genre);
                }).orElseGet(() -> {
                    return genreRepository.save(newGenre);
                });
        return updateGenre;
    }

    @DeleteMapping("{id}")
    Boolean deleteGenre(@PathVariable int id){
        boolean exists = genreRepository.existsById(id);
        if(!exists)
            return false;
        genreRepository.deleteById(id);
        return true;
    }
}
