package net.codejava.QLAM.controllers;

import net.codejava.QLAM.Models.Country;
import net.codejava.QLAM.Models.ResponseObject;
import net.codejava.QLAM.Repositories.SongRepository;
import net.codejava.QLAM.Repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/countrys")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private SongRepository songRepository;

    @GetMapping("")
    List<Country> getAllCountrys(){return countryRepository.findAll();}

    @GetMapping("/{id}")
    Optional<Country> getCountryById(@PathVariable int id){
         return countryRepository.findById(id);
    }

    @PostMapping("")
    Country saveCountry(@RequestBody Country newQuocGia){
        Country foundQuocGias = countryRepository.findByCountryName(newQuocGia.getCountryName().trim());
        if(foundQuocGias != null)
            return new Country();
        return countryRepository.save(newQuocGia);
    }

    @PutMapping("/{id}")
    Country updateCountry(@RequestBody Country newQuocGia, @PathVariable int id){
        Country updateQuocGia = countryRepository.findById(id)
                .map(quocGia -> {
                    quocGia.setCountryName(newQuocGia.getCountryName());
                    return countryRepository.save(quocGia);
                }).orElseGet(() -> {
                    return countryRepository.save(newQuocGia);
                });
        return  updateQuocGia;
    }

    @DeleteMapping("{id}")
    Boolean deleteQuocGia(@PathVariable int id){
        boolean exists = countryRepository.existsById(id);
        if(!exists)
            return false;
        countryRepository.deleteById(id);
        return true;
    }


}
