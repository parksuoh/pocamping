package com.camping.camping.repositories;

import com.camping.camping.domains.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByOrderByIdDesc();
}
