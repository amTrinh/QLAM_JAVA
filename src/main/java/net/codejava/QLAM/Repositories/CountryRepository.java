package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCountryName(String countryName);
}
