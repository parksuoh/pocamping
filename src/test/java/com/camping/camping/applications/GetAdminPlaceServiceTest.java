package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.GetPlaceDetailResponseDto;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetAdminPlaceServiceTest {

    private PlaceRepository placeRepository;
    private PlaceImageRepository placeImageRepository;
    private GetAdminPlaceService getAdminPlaceService;

    @BeforeEach
    void setUp() {

        placeRepository = mock(PlaceRepository.class);
        placeImageRepository = mock(PlaceImageRepository.class);

        getAdminPlaceService = new GetAdminPlaceService(
                placeRepository,
                placeImageRepository
        );
    }

    @Test
    @DisplayName("관리자 장소상세 가져오기 테스트")
    void getPlaceTest() {

        Place place = new Place(
                new Name("testPlace"),
                new Money(1000L),
                new Description("testDesc"));

        PlaceImage placeImage = new PlaceImage(
                place,
                "testUrl");

        given(placeRepository
                .findById(place.id()))
                .willReturn(Optional.of(place));

        given(placeImageRepository
                .findByPlace_Id(place.id()))
                .willReturn(List.of(placeImage));

        GetPlaceDetailResponseDto placeDetail = getAdminPlaceService.getPlace(place.id());

        assertThat(placeDetail.name()).isEqualTo("testPlace");
    }


}