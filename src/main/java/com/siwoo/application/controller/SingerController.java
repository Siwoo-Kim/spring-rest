package com.siwoo.application.controller;

import com.siwoo.application.domain.Singer;
import com.siwoo.application.domain.Singers;
import com.siwoo.application.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/singer")
public class SingerController {

    @Autowired private SingerService singerService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK) @ResponseBody
    public Singers singers(){
        return new Singers(singerService.findAll());
    }

    @GetMapping("/{id}") @ResponseBody
    public Singer singerById(@PathVariable Long id){
        return singerService.findSingerById(id);
    }

    @PostMapping @ResponseBody
    public Singer create(@RequestBody Singer singer){
        log.info("Request Body for saving : singer "+singer);
        singerService.save(singer);
        log.info("Singer created successfully with info: "+singer);
        return singer;
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Singer singer, @PathVariable Long id){
        log.info("Updating singer: "+singer);
        singerService.save(singer);
        log.info("Singer updated successfully with info: "+singer);
    }

    @DeleteMapping("/{id}") @ResponseBody
    public void delete(@PathVariable Long id){
        log.info("Deleting singer with id: "+id);
        singerService.delete(id);
        log.info("Singer deleted successfully");
    }

}
