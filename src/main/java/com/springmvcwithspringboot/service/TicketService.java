package com.springmvcwithspringboot.service;

import com.springmvcwithspringboot.exception.ReleaseNotFoundException;
import com.springmvcwithspringboot.exception.TicketNotFoundException;
import com.springmvcwithspringboot.model.TicketRequest;
import com.springmvcwithspringboot.model.TicketVO;

import java.util.List;

public interface TicketService {
    List<TicketVO> findAll();
    TicketVO getTicketById(int id) throws ReleaseNotFoundException;

    TicketVO save(TicketRequest ticketRequest) throws TicketNotFoundException;

}
