package com.camping.camping.applications;

import com.camping.camping.dtos.GetPlaceReservationResponseDto;
import com.camping.camping.repositories.PlaceReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminPlaceReservationsService {

    private final PlaceReservationRepository placeReservationRepository;

    public GetAdminPlaceReservationsService(PlaceReservationRepository placeReservationRepository) {
        this.placeReservationRepository = placeReservationRepository;
    }

    public List<GetPlaceReservationResponseDto> getPlaceReservations() {

        return placeReservationRepository
                .findAllByOrderByIdDesc()
                .stream()
                .map(placeReservation -> new GetPlaceReservationResponseDto(
                        placeReservation.id(),
                        placeReservation.place().id(),
                        placeReservation.placeName().toString(),
                        placeReservation.price().asLong(),
                        placeReservation.reservationDate(),
                        placeReservation.reservationStatus().toString()
                ))
                .toList();
    }

}
