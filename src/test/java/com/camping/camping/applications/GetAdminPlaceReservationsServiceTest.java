package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceReservation;
import com.camping.camping.domains.User;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.ReservationStatus;
import com.camping.camping.domains.vo.Role;
import com.camping.camping.dtos.GetPlaceReservationResponseDto;
import com.camping.camping.repositories.PlaceReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetAdminPlaceReservationsServiceTest {

    private PlaceReservationRepository placeReservationRepository;
    private GetAdminPlaceReservationsService getAdminPlaceReservationsService;

    @BeforeEach
    void setUp() {

        placeReservationRepository = mock(PlaceReservationRepository.class);

        getAdminPlaceReservationsService = new GetAdminPlaceReservationsService(placeReservationRepository);

    }

    @Test
    @DisplayName("관리자 예약목록 가져오기 테스트")
    void getPlaceReservationsTest(){
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


        given(placeReservationRepository
                .findAllByOrderByIdDesc())
                .willReturn(List.of(placeReservation));

        List<GetPlaceReservationResponseDto> placeReservations = getAdminPlaceReservationsService.getPlaceReservations();

        assertThat(placeReservations).hasSize(1);

    }


}