package com.siwoo.application;

import com.siwoo.application.config.RestClientConfig;
import com.siwoo.application.domain.Singer;
import com.siwoo.application.domain.Singers;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

@Log
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestClientTest {

    private static String URL_FIND_ALL =  "http://localhost:8080/singer/listdata";
    private static String URL_FIND_BY_ID =  "http://localhost:8080/singer/{id}";
    private static String URL_CREATE =  "http://localhost:8080/singer/";
    private static String URL_DELETE_BY_ID =  "http://localhost:8080/singer/{id}";

    @Autowired RestTemplate restTemplate;

    @Before
    public void setup(){
        assertNotNull(restTemplate);
    }

    Consumer<Singers> listSingers = (Singers singers) ->{
        singers.getSingers()
                .stream()
                .map(Singer::toString).forEach(log::warning);
    };

    @Test
    public void testA_FindAll(){
        Singers foundSingers = restTemplate.getForObject(URL_FIND_ALL,Singers.class);
        assertEquals(foundSingers.getSingers().size(),3);
        listSingers.accept(foundSingers);
    }

    @Test
    public void testB_FindById(){
        Singer singer = restTemplate.getForObject(URL_FIND_BY_ID,Singer.class,1);
        assertEquals(singer.getId().intValue(),1,0);
        listSingers.accept(new Singers(Arrays.asList(singer)));
    }

    @Test
    public void testC_Create(){
        Singer singer = new Singer();
        singer.setFirstName("Jenny");
        singer.setLastName("Black Pink");
        singer.setBirthDate(LocalDate.of(1994,6,4));
        Singer newSinger = restTemplate.postForObject(URL_CREATE,singer,Singer.class);

        assertNotNull(newSinger.getId());
        listSingers.accept(new Singers(Arrays.asList(newSinger)));

        Singers foundSingers = restTemplate.getForObject(URL_FIND_ALL,Singers.class);
        assertEquals(foundSingers.getSingers().size(),4);
        listSingers.accept(foundSingers);
    }

    @Test
    public void testD_Delete(){
        restTemplate.delete(URL_DELETE_BY_ID,1);

        Singers foundSingers = restTemplate.getForObject(URL_FIND_ALL,Singers.class);
        assertEquals(foundSingers.getSingers().size(),3);
        boolean found = false;
        for(Singer singer : foundSingers.getSingers()){
            if(singer.getId() == 1){
                found =true;
            }
        }
        assertFalse(found);
        listSingers.accept(foundSingers);

    }

}
