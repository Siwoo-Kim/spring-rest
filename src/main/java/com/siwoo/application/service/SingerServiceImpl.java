package com.siwoo.application.service;

import com.siwoo.application.domain.Singer;
import com.siwoo.application.repository.SingerRepository;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService") @Transactional(readOnly = true)
public class SingerServiceImpl implements SingerService{

    @Autowired SingerRepository singerRepository;

    @Override
    public List<Singer> findAll(){
        return singerRepository.findAll();
    }

    @Override
    public Singer findSingerById(Long id){
        Asserts.notNull(id,"id must not empty");
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public Singer save(Singer singer){
        Asserts.notNull(singer,"singer must not empty");
        return singerRepository.save(singer);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Singer singer){
        singerRepository.delete(singer);
    }

    @Override
    public void delete(Long id) {
        delete(findSingerById(id));
    }

}
