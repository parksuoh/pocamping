package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.ReservationStatus;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.repositories.PlaceReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateReservationServiceTest {

    private PlaceReservationRepository placeReservationRepository;
    private UpdateReservationService updateReservationService;

    @BeforeEach
    void setUp() {
        placeReservationRepository = mock(PlaceReservationRepository.class);

        updateReservationService = new UpdateReservationService(placeReservationRepository);
    }

    @Test
    @DisplayName("관리자 예약 변경 테스트")
    void updateReservationTest(){

        String name = "user10";
        String password = "1234";
        String encodedePassword = "$argon2id$v=19$m=16384,t=2,p=1$5YZYj8U2tIXC8yLu9u9s5A$AFPPJqVyNqUw0BTi53Uwr25FW32zjscZ8/8HsGLBuZU";


        User user = new User(
                name,
                encodedePassword,
                Role.ROLE_USER);

        Place place = new Place(
                new Name("testPlace"),
                new Money(1000L),
                new Description("testDesc"));

        PlaceReservation placeReservation = new PlaceReservation(
                user,
                user.name(),
                place,
                place.name(),
                new Money(1000L),
                LocalDate.now(),
                ReservationStatus.REQUEST
        );


        String reservationStatus = "CONFIRM";

        ReservationStatus status = ReservationStatus.isInStatus(reservationStatus);

        given(placeReservationRepository
                .findById(placeReservation.id()))
                .willReturn(Optional.of(placeReservation));


        if(status.toString().equals("CONFIRM")) {
            LocalDate reservationDate = placeReservation.reservationDate();
            given(placeReservationRepository
                    .existsByReservationStatusAndReservationDate(
                            ReservationStatus.valueOf(status.toString()),
                            reservationDate
                    ))
                    .willReturn(Boolean.FALSE);

        }

        placeReservation.changeReservationStatus(status);

        placeReservationRepository.save(placeReservation);

        String res = updateReservationService.updateReservation(placeReservation.id(), reservationStatus);

        assertThat(res).isEqualTo("success");

        assertThat(placeReservation.reservationStatus().toString()).isEqualTo(reservationStatus);


    }


}