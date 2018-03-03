package com.siwoo.application;

import com.siwoo.application.config.RestClientConfig;
import com.siwoo.application.domain.Singer;
import com.siwoo.application.domain.Singers;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

@Log
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
public class RestClientTest {

    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/list";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";

    @Autowired RestTemplate restTemplate;

    @Before
    public void setup(){
        assertNotNull(restTemplate);
    }

    Consumer<Singers> listSinger = singers -> {
        singers.getSingers().stream()
                .map(Singer::toString)
                .forEach(log::info);
    };

    @Test
    public void testFindAll(){
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS,Singers.class);
        assertTrue(singers.getSingers().size()==3);
        listSinger.accept(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID,Singer.class,1);
        assertNotNull(singer);
        log.info(singer.toString());
    }

    @Test
    public void testUpdate(){
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID,Singer.class,1);
        singer.setFirstName("Siri");
        restTemplate.put(URL_UPDATE_SINGER,singer,1);
    }
}
