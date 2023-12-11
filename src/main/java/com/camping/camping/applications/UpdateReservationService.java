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
@Transactional
public class UpdateReservationService {

    private final PlaceReservationRepository placeReservationRepository;

    public UpdateReservationService(PlaceReservationRepository placeReservationRepository) {
        this.placeReservationRepository = placeReservationRepository;
    }

    public String updateReservation(Long placeReservationId, String reservationStatus) {
        if (!reservationStatus.equals("REQUEST")
                && !reservationStatus.equals("CONFIRM")
                && !reservationStatus.equals("RESERVATION_CANCELED")){
            throw new PlaceReservationStatusNotMatch();
        }

        PlaceReservation placeReservation = placeReservationRepository
                .findById(placeReservationId)
                .orElseThrow(PlaceReservationNotExist::new);


        if(reservationStatus.equals("CONFIRM")) {

            LocalDate reservationDate = placeReservation.reservationDate();
            boolean isConfirmedInDate = placeReservationRepository
                    .existsByReservationStatusAndReservationDate(
                            ReservationStatus.valueOf(reservationStatus),
                            reservationDate
                    );

            if(isConfirmedInDate) {
                throw new PlaceReservationAlreadyConfiremd();
            }
        }



        placeReservation.changeReservationStatus(ReservationStatus.valueOf(reservationStatus));
        placeReservationRepository.save(placeReservation);

        return "success";
    }

}
