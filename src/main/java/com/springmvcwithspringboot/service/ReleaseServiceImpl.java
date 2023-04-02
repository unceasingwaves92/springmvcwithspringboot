package com.springmvcwithspringboot.service;

import com.springmvcwithspringboot.entity.Release;
import com.springmvcwithspringboot.exception.ApplicationNotFoundException;
import com.springmvcwithspringboot.exception.ReleaseNotFoundException;
import com.springmvcwithspringboot.model.ReleaseRequest;
import com.springmvcwithspringboot.model.ReleaseVO;
import com.springmvcwithspringboot.repository.ReleaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Release service implementation
 * Author
 */

@Service
@Slf4j
public class ReleaseServiceImpl implements ReleaseService{

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public List<ReleaseVO> findAll() {
        log.info("Inside ReleaseServiceImpl.findAll");
        List<Release> releases = releaseRepository.findAll();
        log.info("Find all release response: {}", releases);
        List<ReleaseVO> releaseVOS = releases.stream().map(release -> {
            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setReleaseId(release.getReleaseId());
            releaseVO.setDescription(release.getDescription());
            releaseVO.setReleaseDate(release.getReleaseDate());
            return releaseVO;
        }).collect(Collectors.toList());
        return releaseVOS;
    }

    /**
     * Get release by id
     * @param id
     * @return
     * @throws ReleaseNotFoundException
     */

    @Override
    public ReleaseVO getReleasebyId(int id) throws ReleaseNotFoundException {
        log.info("Inside ReleaseServiceImpl.getReleasebyId, id:{}", id);
        if(id<=0){
            log.info("Invalid release id, release id:{}", id);
            throw new ReleaseNotFoundException("Invalid release id");
        }
        Optional<Release> release = releaseRepository.findById(id);
        if(!release.isPresent()){
            log.info("No record found for the release id:{}", id);
            throw new ReleaseNotFoundException("No record found for the release id");
        }
        ReleaseVO releaseVO = new ReleaseVO();
        releaseVO.setReleaseId(release.get().getReleaseId());
        releaseVO.setDescription(release.get().getDescription());
        releaseVO.setReleaseDate(release.get().getReleaseDate());
        return releaseVO;
    }
    /**
     * Save the releases
     */

    public ReleaseVO save(ReleaseRequest releaseRequest) throws ReleaseNotFoundException{
        log.info("Inside ReleaseServiceImpl.save, releaseRequest:{}", releaseRequest);
        if(releaseRequest == null){
            log.info("Invalid release request");
            throw new ReleaseNotFoundException("Invalid release request");
        }
        // Release request convert into release entity
        Release release = new Release();
        if(releaseRequest.getReleaseId()>0){
            release.setReleaseId(releaseRequest.getReleaseId());
        }
        if(releaseRequest.getDescription() != null){
            release.setDescription(releaseRequest.getDescription());
        }

        if(releaseRequest.getReleaseDate() != null){
            release.setReleaseDate(releaseRequest.getReleaseDate());
        }

        // save the entity in JPA repository
        Release releaseresponse = releaseRepository.save(release);
        ReleaseVO releaseVO = null;
        if(releaseresponse!=null){
            // another pojo class it will insert the record
            releaseVO = new ReleaseVO();
            releaseVO.setReleaseId(release.getReleaseId());
            releaseVO.setDescription(release.getDescription());
            releaseVO.setReleaseDate(release.getReleaseDate());
        }
        return releaseVO;

    }

    @Override
    public String delete(int id) throws ReleaseNotFoundException {
        log.info("Input to AppplicationServiceImpl.delete, id:{}", id);
        if(id<0){
            log.info("Invalid application id");
            throw new ReleaseNotFoundException("Invalid application id");
        }
        try {
            releaseRepository.deleteById(id);
        }catch(Exception ex){
            log.error("Exception while deleting application");
            throw new ReleaseNotFoundException("Exception while deleting application");
        }
        return "Release has been deleted";
    }
    }

