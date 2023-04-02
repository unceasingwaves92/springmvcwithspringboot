package com.springmvcwithspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="TICKET_TBL")
public class Ticket implements Serializable {

    public static final Long serialVersionUID = 342343l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_gen")
    @SequenceGenerator(name = "ticket_gen", sequenceName = "TICKET_TBL_SEQ",allocationSize = 1)
    @Column(name = "ticket_id")
    private int ticketId;

    @ManyToOne
    @JoinColumn(name="application_id")
    // many tickets can associated with 1 application
   // private int applicationId;
    private Application application;

    @ManyToOne
    @JoinColumn(name="release_id")
    // many release can associated with 1 ticket
  //  private int releaseId;
    private Release release;

    private String description;
    private String status;
    private String title;
}
