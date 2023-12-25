package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.CategoryRepository;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeletePlaceServiceTest {

    private PlaceRepository placeRepository;
    private PlaceImageRepository placeImageRepository;
    private DeletePlaceService deletePlaceService;


    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        placeImageRepository = mock(PlaceImageRepository.class);
        deletePlaceService = new DeletePlaceService(
                placeRepository,
                placeImageRepository
        );
    }

    @Test
    @DisplayName("장소 삭제 테스트")
    void deletePlaceTest() {

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

        placeRepository.delete(place);

        given(placeImageRepository
                .findByPlace_Id(place.id()))
                .willReturn(List.of(placeImage));

        placeImageRepository.delete(placeImage);

        String res = deletePlaceService.deletePlace(place.id());

        assertThat(res).isEqualTo("success");

    }




}