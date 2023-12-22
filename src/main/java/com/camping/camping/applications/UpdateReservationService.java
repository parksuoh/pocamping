package com.camping.camping.applications;

import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.vo.ReservationStatus;
import com.camping.camping.exceptions.PlaceReservationAlreadyConfiremd;
import com.camping.camping.exceptions.PlaceReservationNotExist;
import com.camping.camping.exceptions.PlaceReservationStatusNotMatch;
import com.camping.camping.repositories.PlaceReservationRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UpdateReservationService {

    private final PlaceReservationRepository placeReservationRepository;

    public UpdateReservationService(PlaceReservationRepository placeReservationRepository) {
        this.placeReservationRepository = placeReservationRepository;
    }

    public String updateReservation(Long placeReservationId, String reservationStatus) {


        ReservationStatus status = ReservationStatus.isInStatus(reservationStatus);


        PlaceReservation placeReservation = placeReservationRepository
                .findById(placeReservationId)
                .orElseThrow(PlaceReservationNotExist::new);


        if(status.toString().equals("CONFIRM")) {

            LocalDate reservationDate = placeReservation.reservationDate();
            boolean isConfirmedInDate = placeReservationRepository
                    .existsByReservationStatusAndReservationDate(
                            ReservationStatus.valueOf(status.toString()),
                            reservationDate
                    );

            if(isConfirmedInDate) {
                throw new PlaceReservationAlreadyConfiremd();
            }
        }


        if(status.toString().equals("REQUEST")){
            placeReservation.toRequest();
        } else if (status.toString().equals("CONFIRM")) {
            placeReservation.toConfirm();
        } else if (status.toString().equals("RESERVATION_CANCELED")) {
            placeReservation.toCancel();
        }

        placeReservationRepository.save(placeReservation);

        return "success";
    }

}
