package com.siwoo.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Singers implements Serializable {
    private List<Singer> singers;


}
