package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.repositories.PlaceRepository;
import com.camping.camping.repositories.PlaceReservationRepository;
import com.camping.camping.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.camping.camping.domains.vo.OrderStatus.READY;
import static com.camping.camping.domains.vo.ReservationStatus.REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;


class AddPlaceReservationServiceTest {

    private UserRepository userRepository;
    private PlaceRepository placeRepository;
    private PlaceReservationRepository placeReservationRepository;

    private AddPlaceReservationService addPlaceReservationService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        placeRepository = mock(PlaceRepository.class);
        placeReservationRepository = mock(PlaceReservationRepository.class);

        addPlaceReservationService = new AddPlaceReservationService(
                userRepository,
                placeRepository,
                placeReservationRepository
        );

    }


    @Test
    @DisplayName("예약 추가 테스트")
    void addPlaceReservationTest() {
        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";

        LocalDate localDate = LocalDate.now();

        List<PlaceReservation> reservations = new ArrayList<>();

        User user = new User(
                name,
                encodedePassword,
                Role.ROLE_USER);

        Place place = new Place(
                new Name("testPlace"),
                new Money(1000L),
                new Description("heyhey")
        );

        given(userRepository
                .findByName(user.name()))
                .willReturn(Optional.of(user));

        given(placeRepository
                .findById(place.id()))
                .willReturn(Optional.of(place));

        given(placeReservationRepository
                .findByUser_IdAndPlace_IdAndReservationDate(user.id(), place.id(), localDate))
                .willReturn(reservations);

        PlaceReservation placeReservation = new PlaceReservation(
                user,
                user.name(),
                place,
                place.name(),
                place.price(),
                localDate,
                REQUEST);

        placeReservationRepository.save(placeReservation);

        String res = addPlaceReservationService.addPlaceReservation(
                name,
                place.id(),
                localDate
        );

        assertThat(res).isEqualTo("success");
    }




}