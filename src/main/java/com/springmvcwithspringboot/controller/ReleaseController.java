package com.springmvcwithspringboot.controller;

import com.springmvcwithspringboot.model.ReleaseRequest;
import com.springmvcwithspringboot.model.ReleaseVO;
import com.springmvcwithspringboot.service.ReleaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("releases")
@Slf4j
public class ReleaseController {

    @Autowired
    ReleaseService releaseService;

    @GetMapping("/list")
    public String getRelease(Model model) {
        log.info("Inside the ReleaseController.getRelease");
        List<ReleaseVO> releaseVOS = null;
        try {
            releaseVOS = releaseService.findAll();
            if(CollectionUtils.isEmpty(releaseVOS)){
                log.info("Application details are not found");
             //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            log.info("Exception while calling getApplications", ex);
           // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        model.addAttribute("releases", releaseVOS);
        return "releases";
        //return new ResponseEntity<List<ReleaseVO>>(releaseVOS, HttpStatus.OK);
    }


    @GetMapping("/create")
    public String createRelease(Model model) {
        log.info("Inside the ReleaseController.getRelease");
        List<ReleaseVO> releaseVOS = null;
        try {
            ReleaseRequest releaseRequest = new ReleaseRequest();
            model.addAttribute("releaseRequest", releaseRequest);
        }catch(Exception ex){
            log.info("Exception while calling getApplications", ex);

        }
         return "releasenew";

    }
    //http://localhost:8085/api/vi/releases/id
    @GetMapping("/{id}")
    public ResponseEntity<ReleaseVO> getReleaseById(@PathVariable("id") int id) {
        log.info("Input to ReleaseController.getReleaseById, id:{}", id);
        ReleaseVO releaseVOS = null;
        try {
            releaseVOS = releaseService.getReleasebyId(id);
            log.info("Release details for the release id:{}, and details:{}", id, releaseVOS);
            if(releaseVOS == null){
                log.info("Release details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.info("Exception error while processing the ReleaseController.getReleasebyId", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ReleaseVO>(releaseVOS, HttpStatus.OK);

    }

    @PostMapping("/save")
    public RedirectView saveRelease(@ModelAttribute("releaseRequest") ReleaseRequest releaseRequest) {
        log.info("Inside ReleaseController.save, releaseVO:{}", releaseRequest);
        if (releaseRequest == null) {
            log.info("Invalid release request");

        }
        ReleaseVO releaseVO = null;
        try {
            releaseVO = releaseService.save(releaseRequest);
            if (releaseVO == null) {
                log.error("Release details not saved");

            }
        } catch (Exception ex) {
            log.error("Exception while saving releases");

        }

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/releases/list");
        return redirectView;
    }

    @GetMapping("/delete")
    public RedirectView deleteApplicationById(@RequestParam("id") int id){
        log.info("Input to ApplicationController.deleteApplicationById, id:{}", id);
        String applicationVO=null;
        try {
            releaseService.delete(id);
            log.info("Delete Application details for the application id:{}, and details:{}", id, applicationVO);
            log.info("Application details found");


        } catch (Exception ex) {
            if (applicationVO == null) {
                log.info("Application details not found");
                log.info("Exception error while processing the ApplicationController.deleteApplicationById", ex);

            }
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/releases/list");
        return redirectView;
    }

}
