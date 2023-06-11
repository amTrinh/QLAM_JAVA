package net.codejava.QLAM.Database;

import net.codejava.QLAM.Models.*;
import net.codejava.QLAM.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/*
docker run -d --rm --name mysql-spring-boot-qlam -e MYSQL_ROOT_PASSWORD= -e MYSQL_USER=root -e MYSQL_PASSWORD= -e MYSQL-DATABASE=qlam -p 3306:3306 --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:latest

mysql -h localhost -p 3312 --protocol=tcp -u root -p
* */
@Configuration
public class Database {
    @Bean
    CommandLineRunner initDataBase(
            SongRepository songRepository,
            PlaylistRepository playListRepository,
            CountryRepository countryRepository,
            GenreRepository genreRepository,
            AlbumRepository albumRepository,
            ArtistRepository artistRepository
    ){
        //logger
        final Logger logger = LoggerFactory.getLogger((Database.class));
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Song baihatA = new Song("a", "hi", 1, 1, "05:06", "anh", "link", "anhpn",  new Date());
//                Song baihatB = new Song("b", "hi", 1, 1, "05:06", "anh", "link", "anhpn",  new Date());
//                logger.info("Insert data: " + songRepository.save(baihatA));
//                logger.info("Insert data: " + songRepository.save(baihatB));
//
//                Playlist playlist = new Playlist("playlist 1");
//                logger.info("insert data" + playListRepository.save(playlist));
//
//                Country quocgia = new Country("Việt Nam");
//                logger.info("Insert: " + countryRepository.save(quocgia));
//
//                Genre genre = new Genre("Kpop");
//                logger.info("Insert: " + genreRepository.save(genre));
//
//                Album album = new Album("album1", 1, new Date(), "linkimage");
//                logger.info("Insert: " + albumRepository.save(album));
//
//                Artist artist = new Artist("artist 1", "hh", "lin");
//                logger.info("Insert" + artistRepository.save(artist));

            }
        };
    }
}
