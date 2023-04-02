package com.springmvcwithspringboot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReleaseRequest {

    private int releaseId;
    private String description;
    private Date releaseDate;

    @Override
    public String toString() {
        return "ReleaseRequest{" +
                "releaseId=" + releaseId +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
