package com.siwoo.application.repository;

import com.siwoo.application.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepository extends JpaRepository<Singer,Long>{
}
