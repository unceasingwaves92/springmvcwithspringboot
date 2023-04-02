package com.springmvcwithspringboot.model;

import com.springmvcwithspringboot.entity.Application;
import com.springmvcwithspringboot.entity.Release;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketVO {
    private int ticketId;
    private Application application;
    private Release release;
    private String description;
    private String status;
    private String title;
}
