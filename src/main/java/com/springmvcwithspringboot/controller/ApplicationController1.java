package com.springmvcwithspringboot.controller;

import com.springmvcwithspringboot.exceptionhandling.ResourceNotFoundException;
import com.springmvcwithspringboot.model.ApplicationVO;
import com.springmvcwithspringboot.exceptionhandling.ResourceFoundException;
import com.springmvcwithspringboot.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/applications")
@Slf4j
public class ApplicationController1 {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationVO> getApplicationById1(@PathVariable("id") long id) throws Exception, ResourceFoundException, ResourceNotFoundException {
        log.info("Input to ApplicationController.getApplicationById, id:{}", id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.getAppId(id);
            log.info("Application details for the application id:{}, and details:{}", id, applicationVO);

                if (applicationVO == null) {
                    throw new ResourceNotFoundException("Application details not found");
                }
            }catch(Exception e) {
                log.info("Exception error while processing the ApplicationController.getApplicationById", e);
                throw new ResourceNotFoundException("ID is not found");
            }
            return new ResponseEntity<ApplicationVO>(applicationVO, HttpStatus.OK);

    }
    // http://localhost:8085/api/v1/applications?name=appName&id=23
    @GetMapping("/name")
    public ResponseEntity<ApplicationVO> getApplicationByName1(@RequestParam("name") String appName, @RequestParam("id") int id) throws Exception, ResourceNotFoundException {
        log.info("Input to ApplicationController.getApplicationByName, appName:{} id:{}", appName, id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.findGetByName(appName);
            if(applicationVO ==null) {
                throw new ResourceNotFoundException("Application details not found");
            }
        } catch (Exception ex) {
            log.error("Exception while getting applications", ex);
            throw new ResourceNotFoundException("Name is not found");
        }

        return new ResponseEntity<ApplicationVO>(HttpStatus.OK);
    }

}
