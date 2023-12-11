package com.camping.camping.repositories;

import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.vo.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlaceReservationRepository extends JpaRepository<PlaceReservation, Long> {

    List<PlaceReservation> findByUser_IdAndPlace_IdAndReservationDate(Long userId, Long placeId, LocalDate reservationDate);

    List<PlaceReservation> findByUser_Id(Long userId);

    List<PlaceReservation> findAllByOrderByIdDesc();

    boolean existsByReservationStatusAndReservationDate(ReservationStatus reservationStatus, LocalDate reservationDate);

}
