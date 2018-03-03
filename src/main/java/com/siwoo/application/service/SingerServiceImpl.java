package com.siwoo.application.service;

import com.siwoo.application.domain.Singer;
import com.siwoo.application.repository.SingerRepository;
import org.castor.core.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional(readOnly = true)
public class SingerServiceImpl implements SingerService{

    @Autowired SingerRepository singerRepository;

    @Override
    public List<Singer> findAll(){
        return singerRepository.findAll();
    }
    @Override
    public Singer findSingerById(Long id){
        Assert.notNull(id,"id must not null");
        return singerRepository.findById(id).orElseThrow(NullPointerException::new);
    }
    @Override
    @Transactional(readOnly = false)
    public Singer save(Singer singer){
        Assert.notNull(singer,"singer must not null");
        return singerRepository.save(singer);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(Singer singer){
        Assert.notNull(singer,"singer must not null");
        delete(singer.getId());
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id){
        Assert.notNull(id,"id must not null");
        singerRepository.delete(findSingerById(id)); //NullPointException
    }

}
