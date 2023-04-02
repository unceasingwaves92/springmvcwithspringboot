package com.springmvcwithspringboot.controller;

import com.springmvcwithspringboot.model.TicketRequest;
import com.springmvcwithspringboot.model.TicketVO;
import com.springmvcwithspringboot.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("tickets")
@Slf4j
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping("/list")
    public String getTicket(Model model) {
        log.info("Inside the TicketController.getTicket");
        List<TicketVO> ticketVOS = null;
        try {
            ticketVOS = ticketService.findAll();
            if(CollectionUtils.isEmpty(ticketVOS)){
                log.info("Application details are not found");
               // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            log.info("Exception while calling getApplications", ex);
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        model.addAttribute("tickets", ticketVOS);
        return "tickets";
        //return new ResponseEntity<List<TicketVO>>(ticketVOS, HttpStatus.OK);
    }

    // http://localhost:8085/api/vi/tickets/id
    @GetMapping("/{id}")
    public ResponseEntity<TicketVO> getTicketById(@PathVariable("id") int id){
        log.info("Input to TicketController.getTicketById, id:{}", id);
        TicketVO ticketVO=null;
        try {
            ticketVO=ticketService.getTicketById(id);
            log.info("Ticket details for the ticket id:{}, and details:{}", id, ticketVO);
            if(ticketVO==null){
                log.info("Ticket details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.info("Exception error while processing the TicketController.getTicketById", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketVO>(ticketVO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> saveTicket(@RequestBody TicketRequest ticketRequest) {
        if(ticketRequest==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TicketVO ticketVO = null;
        try {
            ticketVO=ticketService.save(ticketRequest);
            if(ticketVO==null){
                log.error("Tickets details not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            log.error("Exception while saving tickets");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Tickets has been saved", HttpStatus.OK);
    }

}
