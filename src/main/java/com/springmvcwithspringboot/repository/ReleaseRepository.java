package com.springmvcwithspringboot.repository;

import com.springmvcwithspringboot.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {


}
