package com.camping.camping.applications;


import com.camping.camping.domains.Place;
import com.camping.camping.dtos.GetAdminPlacesResponseDto;
import com.camping.camping.repositories.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminPlacesService {

    private final PlaceRepository placeRepository;

    public GetAdminPlacesService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<GetAdminPlacesResponseDto> getAdminPlaces() {

        List<Place> places = placeRepository.findAllByOrderByIdDesc();

        return places
                .stream()
                .map(place -> new GetAdminPlacesResponseDto(
                        place.id(),
                        place.name().toString(),
                        place.price().asLong()
                ))
                .toList();
    }

}
