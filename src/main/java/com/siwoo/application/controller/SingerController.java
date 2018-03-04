package com.siwoo.application.controller;

import com.siwoo.application.domain.Singer;
import com.siwoo.application.domain.Singers;
import com.siwoo.application.service.SingerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired private SingerService singerService;

    @GetMapping("/listdata")
    public Singers singers(){
        return new Singers(singerService.findAll());
    }

    @GetMapping("/{id}")
    public Singer singer(@PathVariable Long id){
        return singerService.findSingerById(id);
    }

    @PostMapping
    public Singer create(@RequestBody Singer singer){
        return singerService.save(singer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        singerService.delete(id);
    }


}
