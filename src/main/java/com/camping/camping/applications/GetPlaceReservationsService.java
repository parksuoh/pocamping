package com.camping.camping.applications;

import com.camping.camping.domains.User;
import com.camping.camping.dtos.GetPlaceReservationResponseDto;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.repositories.PlaceReservationRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetPlaceReservationsService {

    private final UserRepository userRepository;
    private final PlaceReservationRepository placeReservationRepository;

    public GetPlaceReservationsService(UserRepository userRepository, PlaceReservationRepository placeReservationRepository) {
        this.userRepository = userRepository;
        this.placeReservationRepository = placeReservationRepository;
    }

    public List<GetPlaceReservationResponseDto> getPlaceReservations(String name) {

        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        return placeReservationRepository
                .findByUser_Id(user.id())
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
