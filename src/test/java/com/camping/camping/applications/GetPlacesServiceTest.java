package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.GetPlacesDto;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetPlacesServiceTest {

    private PlaceRepository placeRepository;
    private PlaceImageRepository placeImageRepository;
    private GetPlacesService getPlacesService;

    @BeforeEach
    void setUp() {

        placeRepository = mock(PlaceRepository.class);
        placeImageRepository = mock(PlaceImageRepository.class);
        getPlacesService = new GetPlacesService(
                placeRepository,
                placeImageRepository
        );

    }

    @Test
    @DisplayName("장소목록 가져오기 테스트")
    void getPlacesTest() {

        Place place = new Place(
                new Name("testPlace"),
                new Money(1000L),
                new Description("testDesc"));

        PlaceImage placeImage = new PlaceImage(
                place,
                "testUrl");

        given(placeRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id")))
                .willReturn(List.of(place));

        given(placeImageRepository
                .findByPlace_Id(place.id()))
                .willReturn(List.of(placeImage));

        List<GetPlacesDto> places = getPlacesService.getPlaces();

        assertThat(places).hasSize(1);

    }


}