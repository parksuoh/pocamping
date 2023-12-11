package com.camping.camping.applications;


import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.dtos.GetPlaceDetailResponseDto;
import com.camping.camping.dtos.PlaceImageDto;
import com.camping.camping.exceptions.PlaceNotExist;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetAdminPlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    public GetAdminPlaceService(PlaceRepository placeRepository, PlaceImageRepository placeImageRepository) {
        this.placeRepository = placeRepository;
        this.placeImageRepository = placeImageRepository;
    }

    public GetPlaceDetailResponseDto getPlace(Long placeId) {

        Place place = placeRepository
                .findById(placeId)
                .orElseThrow(PlaceNotExist::new);

        List<PlaceImage> placeImages = placeImageRepository.findByPlace_Id(place.id());

        return new GetPlaceDetailResponseDto(
                place.id(),
                place.name().toString(),
                place.price().asLong(),
                place.description().toString(),
                placeImageToDto(placeImages)
        );
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
