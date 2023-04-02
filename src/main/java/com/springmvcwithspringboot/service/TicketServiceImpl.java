package com.springmvcwithspringboot.service;

import com.springmvcwithspringboot.entity.Ticket;
import com.springmvcwithspringboot.exception.ReleaseNotFoundException;
import com.springmvcwithspringboot.exception.TicketNotFoundException;
import com.springmvcwithspringboot.model.TicketRequest;
import com.springmvcwithspringboot.model.TicketVO;
import com.springmvcwithspringboot.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Ticket service implementation
 * Author
 */

@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public List<TicketVO> findAll() {
        log.info("Inside TicketServiceImpl.findAll");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("Find all tickets response: {}", tickets);
        List<TicketVO> ticketVOS = tickets.stream().map(ticket -> {
            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.getTicketId());
            ticketVO.setApplication(ticket.getApplication());
            ticketVO.setRelease(ticket.getRelease());
            ticketVO.setDescription(ticket.getDescription());
            ticketVO.setStatus(ticket.getStatus());
            ticketVO.setTitle(ticket.getTitle());
            return ticketVO;
        }).collect(Collectors.toList());
        return ticketVOS;
    }

    /**
     * Get ticket by id
     * @param id
     * @return
     */
    @Override
    public TicketVO getTicketById(int id) throws ReleaseNotFoundException {
        log.info("Inside TicketServiceImpl.getTicketById, id:{}", id);
        if(id<=0){
            log.info("Invalid ticket id, ticket id:{}", id);
            throw new ReleaseNotFoundException("Invalid ticket id");
        }
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(!ticket.isPresent()){
            log.info("No record found for the ticket id:{}", id);
            throw new ReleaseNotFoundException("No record found for the ticket id");
        }
        TicketVO ticketVO = new TicketVO();
        ticketVO.setTicketId(ticket.get().getTicketId());
        ticketVO.setApplication(ticket.get().getApplication());
        ticketVO.setRelease(ticket.get().getRelease());
        ticketVO.setDescription(ticket.get().getDescription());
        ticketVO.setStatus(ticket.get().getStatus());
        ticketVO.setTitle(ticket.get().getTitle());
        return ticketVO;
    }

    /**
     * Save the tickets
     */

    public TicketVO save(TicketRequest ticketRequest) throws TicketNotFoundException {
        log.info("Inside TicketServiceImpl save ticketRequest:{}", ticketRequest);
        if(ticketRequest == null){
            log.info("Invalid ticket request");
            throw new TicketNotFoundException("Invalid ticket request");
        }
        // TicketRequest convert to ticket entity
        Ticket ticket = new Ticket();
        if(ticketRequest.getTicketId() > 0 ){
            ticket.setTicketId(ticketRequest.getTicketId());
        }
        if(ticketRequest.getApplication() != null){
            ticket.setApplication(ticketRequest.getApplication());
        }

        if(ticketRequest.getRelease() != null){
            ticket.setRelease(ticketRequest.getRelease());
        }

        if(ticketRequest.getDescription() != null){
            ticket.setDescription(ticketRequest.getDescription());
        }

        if(ticketRequest.getStatus() != null){
            ticket.setStatus(ticketRequest.getStatus());
        }

        if(ticketRequest.getTitle() != null){
            ticket.setTitle(ticketRequest.getTitle());
        }

        // save the JPA repository in entity
        Ticket ticketresponse = ticketRepository.save(ticket);
        TicketVO ticketVO = null;
        if(ticketresponse!=null){
            // another pojo class insert the record
            ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.getTicketId());
            ticketVO.setApplication(ticket.getApplication());
            ticketVO.setRelease(ticket.getRelease());
            ticketVO.setDescription(ticket.getDescription());
            ticketVO.setStatus(ticket.getStatus());
            ticketVO.setTitle(ticket.getTitle());

        }
        return ticketVO;

    }
}
