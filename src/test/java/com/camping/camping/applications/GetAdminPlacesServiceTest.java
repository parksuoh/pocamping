package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.dtos.GetAdminPlacesResponseDto;
import com.camping.camping.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class GetAdminPlacesServiceTest {

    private PlaceRepository placeRepository;
    private GetAdminPlacesService getAdminPlacesService;

    @BeforeEach
    void setUp(){

        placeRepository = mock(PlaceRepository.class);

        getAdminPlacesService = new GetAdminPlacesService(placeRepository);
        
    }
    
    
    void getAdminPlacesTest(){

        Place place = new Place(
                new Name("testPlace"),
                new Money(1000L),
                new Description("testDesc"));


        given(placeRepository
                .findAllByOrderByIdDesc())
                .willReturn(List.of(place));

        List<GetAdminPlacesResponseDto> adminPlaces = getAdminPlacesService.getAdminPlaces();

        assertThat(adminPlaces).hasSize(1);
    }

}