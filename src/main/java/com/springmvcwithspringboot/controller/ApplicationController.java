package com.springmvcwithspringboot.controller;

import com.springmvcwithspringboot.model.ApplicationRequest;
import com.springmvcwithspringboot.model.ApplicationVO;
import com.springmvcwithspringboot.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("applications")
@Slf4j
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/list")
    public String getApplication(Model model){
        log.info("Inside the ApplicationController.getApplications");
        List<ApplicationVO> applicationVOS = null;
        try {
            applicationVOS = applicationService.findAll();
            log.info("Application response:{}", applicationVOS);
            if(CollectionUtils.isEmpty(applicationVOS)) {
                log.info("Application details not found");
            //    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            log.info("Exception while calling getApplications", ex);
           // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     //   return new ResponseEntity<List<ApplicationVO>>(applicationVOS, HttpStatus.OK);
        model.addAttribute("applications", applicationVOS);
        return "applications";

    }

    @GetMapping("/create")
    public String createApplication(Model model){
        log.info("Inside the ApplicationController.getApplications");
        try {
            ApplicationRequest applicationRequest = new ApplicationRequest();
            model.addAttribute("applicationRequest", applicationRequest);
        }catch(Exception ex){
            log.info("Exception while calling getApplications", ex);

        }
        return "applicationnew";

    }
    // http://localhost:8085/api/v1/applications/id
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationVO> getApplicationById(@PathVariable("id") long id){
        log.info("Input to ApplicationController.getApplicationById, id:{}", id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.getAppId(id);
            log.info("Application details for the application id:{}, and details:{}", id, applicationVO);
            if(applicationVO==null){
                log.info("Application details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.info("Exception error while processing the ApplicationController.getApplicationById", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApplicationVO>(applicationVO, HttpStatus.OK);

    }

    @GetMapping("/edit")
    public String getApplicationById(@RequestParam("id") long id, Model model){
        log.info("Input to ApplicationController.getApplicationById, id:{}", id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.getAppId(id);
            log.info("Application details for the application id:{}, and details:{}", id, applicationVO);
            if(applicationVO==null){
                log.info("Application details not found");

            }
            model.addAttribute("application", applicationVO);
        } catch (Exception ex) {
            log.info("Exception error while processing the ApplicationController.getApplicationById", ex);

        }
          return "applicationedit";

    }


    // http://localhost:8085/api/v1/applications?name=appName&id=23
    @GetMapping("/name")
    public ResponseEntity<ApplicationVO> getApplicationByName(@RequestParam("name") String appName, @RequestParam("id") int id){
        log.info("Input to ApplicationController.getApplicationByName, appName:{} id:{}", appName, id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.findGetByName(appName);
            if(applicationVO ==null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting applications", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ApplicationVO>(HttpStatus.OK);
    }

    @GetMapping("/appName/id")
    public ResponseEntity<ApplicationVO> getByName(@RequestParam("name") String appName, @RequestParam("id") long id){
        log.info("Input to ApplicationController.getApplicationByName, appName:{} id:{}", appName, id);
        ApplicationVO applicationVO=null;
        try {
            applicationVO=applicationService.findByNameApp(appName, id);
            if(applicationVO ==null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting applications", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ApplicationVO>(applicationVO, HttpStatus.OK);
    }
    @PostMapping("/save")
    public RedirectView saveApplication(@ModelAttribute("applicationRequest") ApplicationRequest applicationRequest) {
        log.info("Inside ApplicationController.save, applicationVO:{}", applicationRequest);
        if(applicationRequest==null){
            log.info("Invalid Application Request");

        }
        ApplicationVO applicationVO = null;
        try {
            applicationVO = applicationService.save(applicationRequest);
            if(applicationVO == null){
                log.info("Application details are not saved");

            }
        } catch (Exception ex) {
            log.error("Exception while saving applications");

        }

       RedirectView redirectView = new RedirectView();
       redirectView.setContextRelative(true);
       redirectView.setUrl("/applications/list");
        return redirectView;
    }

    @PostMapping("/update")
    public RedirectView updateApplication(@ModelAttribute("application")  ApplicationVO application) {
        log.info("Inside ApplicationController.save, applicationVO:{}", application);
        if(application==null){
            log.info("Invalid Application Request");

        }
        // Model response applicationVO but service layer expecting applicationRequest we can converting
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setApplicationId(application.getApplicationId());
        applicationRequest.setApplicationName(application.getApplicationName());
        applicationRequest.setDescription(application.getDescription());
        applicationRequest.setOwner(application.getOwner());
        try {
            application = applicationService.save(applicationRequest);
            if(application == null){
                log.info("Application details are not saved");

            }
        } catch (Exception ex) {
            log.error("Exception while saving applications");

        }

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/applications/list");
        return redirectView;
    }

    @GetMapping("/delete")
    public RedirectView deleteApplicationById(@RequestParam("id") long id){
        log.info("Input to ApplicationController.deleteApplicationById, id:{}", id);
        String applicationVO=null;
       try {
           applicationService.delete(id);
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
        redirectView.setUrl("/applications/list");
        return redirectView;
    }

    @GetMapping("/byidandname") //12132ms 6112 ms
    public ResponseEntity<List<ApplicationVO>> getApplicationByIdAndName(@RequestParam("id") long id, @RequestParam("name") String name){
        log.info("Inside the ApplicationController.getApplications");
        CompletableFuture<ApplicationVO> applicationVO = null;
        CompletableFuture<ApplicationVO> applicationVO1 = null;
        List<ApplicationVO> applicationVOS = new ArrayList<>();
        try {
            applicationVO = applicationService.getApplicationId(id);
            log.info("Get application by id response:{}", applicationVO );
            applicationVO1 = applicationService.findByName(name);
            log.info("Get application by name response:{}", applicationVO1);

            CompletableFuture.allOf(applicationVO, applicationVO1).join();

            applicationVOS.add(applicationVO.get());
            applicationVOS.add(applicationVO1.get());
            if(CollectionUtils.isEmpty(applicationVOS)){
                log.info("Application details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex){
            log.error("Exception while calling getApplications", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<ApplicationVO>>(applicationVOS, HttpStatus.OK);
    }

    @GetMapping("/clearcache")
    public ResponseEntity<String> clearCache(){
        try {
            applicationService.clearFindByIdcCache();
            applicationService.clearFindByNameCache();
        }catch (Exception ex){
            log.error("Exception while calling getApplications", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Cache has been deleted", HttpStatus.OK);
    }


}


