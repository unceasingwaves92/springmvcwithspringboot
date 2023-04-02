package com.springmvcwithspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="RELEASE_TBL")
public class Release implements Serializable{
    public static final Long serialVersionUID = 342343l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "release_gen")
    @SequenceGenerator(name = "release_gen", sequenceName = "RELEASE_TBL_SEQ",allocationSize = 1)
    @Column(name = "release_id")
    private int releaseId;

    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

}
