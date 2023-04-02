package com.springmvcwithspringboot.model;


import com.springmvcwithspringboot.entity.Application;
import com.springmvcwithspringboot.entity.Release;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {

    private int ticketId;
    private Application application;
    private Release release;
    private String description;
    private String status;
    private String title;

    @Override
    public String toString() {
        return "TicketRequest{" +
                "ticketId=" + ticketId +
                ", application=" + application +
                ", release=" + release +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
