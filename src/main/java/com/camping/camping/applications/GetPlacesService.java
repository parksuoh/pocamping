package com.camping.camping.applications;


import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.dtos.GetPlacesDto;
import com.camping.camping.dtos.PlaceImageDto;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetPlacesService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    public GetPlacesService(PlaceRepository placeRepository, PlaceImageRepository placeImageRepository) {
        this.placeRepository = placeRepository;
        this.placeImageRepository = placeImageRepository;
    }

    public List<GetPlacesDto> getPlaces() {

        List<Place> places = placeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));


        return places
                .stream()
                .map(place -> {

                    List<PlaceImage> placeImages = placeImageRepository.findByPlace_Id(place.id());

                    return new GetPlacesDto(
                            place.id(),
                            place.name().toString(),
                            place.price().asLong(),
                            placeImageToDto(placeImages)
                    );

                }).toList();


    }

    private List<PlaceImageDto> placeImageToDto(List<PlaceImage> placeImages) {
        return placeImages
                .stream()
                .map(placeImage ->
                        new PlaceImageDto(
                                placeImage.id(),
                                placeImage.url()
                        ))
                .toList();
    }

}
