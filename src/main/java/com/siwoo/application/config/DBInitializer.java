package com.siwoo.application.config;

import com.siwoo.application.domain.Singer;
import com.siwoo.application.repository.SingerRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service @Log
public class DBInitializer {

    @Autowired SingerRepository singerRepository;

    @PostConstruct
    public void initDB(){
        log.info("Starting inserting data...");

        Singer singer = Singer.builder()
                .firstName("Siwoo")
                .lastName("Kim")
                .birthDate(LocalDate.of(1989,3,4))
                .build();
        singerRepository.save(singer);

        singer = Singer.builder()
                .firstName("Jin Eun")
                .lastName("Lee")
                .birthDate(LocalDate.of(1993,5,12))
                .build();
        singerRepository.save(singer);

        singer = Singer.builder()
                .firstName("Ka Su")
                .lastName("Kim")
                .birthDate(LocalDate.of(1992,11,1))
                .build();
        singerRepository.save(singer);

        log.info("Finishing inserting data...");
    }
}
