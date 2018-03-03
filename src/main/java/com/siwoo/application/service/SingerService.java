package com.siwoo.application.service;

import com.siwoo.application.domain.Singer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    Singer findSingerById(Long id);

    @Transactional(readOnly = false)
    Singer save(Singer singer);

    @Transactional(readOnly = false)
    void delete(Singer singer);

    @Transactional(readOnly = false)
    void delete(Long id);
}
