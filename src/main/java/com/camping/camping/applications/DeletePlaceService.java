package com.camping.camping.applications;

import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.exceptions.PlaceNotExist;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeletePlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    public DeletePlaceService(PlaceRepository placeRepository, PlaceImageRepository placeImageRepository) {
        this.placeRepository = placeRepository;
        this.placeImageRepository = placeImageRepository;
    }


    public String deletePlace(Long placeId) {

        Place place = placeRepository
                .findById(placeId)
                .orElseThrow(PlaceNotExist::new);

        placeRepository.delete(place);

        List<PlaceImage> placeImages = placeImageRepository.findByPlace_Id(placeId);

        for (PlaceImage placeImage : placeImages) {
            placeImageRepository.delete(placeImage);
        }

        return "success";
    }
}
