package com.camping.camping.applications;


import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.User;
import com.camping.camping.exceptions.NameNotExist;
import com.camping.camping.exceptions.PlaceAleadyReservation;
import com.camping.camping.exceptions.PlaceNotExist;
import com.camping.camping.repositories.PlaceRepository;
import com.camping.camping.repositories.PlaceReservationRepository;
import com.camping.camping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.camping.camping.domains.vo.ReservationStatus.REQUEST;

@Service
@Transactional
public class AddPlaceReservationService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PlaceReservationRepository placeReservationRepository;

    public AddPlaceReservationService(UserRepository userRepository, PlaceRepository placeRepository, PlaceReservationRepository placeReservationRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.placeReservationRepository = placeReservationRepository;
    }


    public String addPlaceReservation(String name, Long placeId, LocalDate reservationDate) {

        User user = userRepository
                .findByName(name)
                .orElseThrow(NameNotExist::new);

        Place place = placeRepository
                .findById(placeId)
                .orElseThrow(PlaceNotExist::new);

        List<PlaceReservation> reservations = placeReservationRepository
                .findByUser_IdAndPlace_IdAndReservationDate(user.id(), placeId, reservationDate);

        if (reservations.size() > 0) {
            throw new PlaceAleadyReservation();
        }


        PlaceReservation placeReservation = new PlaceReservation(
                user,
                user.name(),
                place,
                place.name(),
                place.price(),
                reservationDate,
                REQUEST);
        placeReservationRepository.save(placeReservation);

        return "success";
    }

}
