package com.springmvcwithspringboot.service;

import com.springmvcwithspringboot.exception.ApplicationNotFoundException;
import com.springmvcwithspringboot.exception.ReleaseNotFoundException;
import com.springmvcwithspringboot.model.ReleaseRequest;
import com.springmvcwithspringboot.model.ReleaseVO;

import java.util.List;

public interface ReleaseService {

    List<ReleaseVO> findAll();
    ReleaseVO getReleasebyId(int id) throws ReleaseNotFoundException;

    ReleaseVO save(ReleaseRequest releaseRequest) throws ReleaseNotFoundException;

    String delete(int id) throws ReleaseNotFoundException;
}
