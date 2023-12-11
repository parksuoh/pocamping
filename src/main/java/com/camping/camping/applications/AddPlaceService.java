package com.camping.camping.applications;


import com.camping.camping.aws.S3UploadService;
import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class AddPlaceService {

    private final PlaceRepository placeRepository;

    private final S3UploadService s3UploadService;

    private final PlaceImageRepository placeImageRepository;

    public AddPlaceService(PlaceRepository placeRepository, S3UploadService s3UploadService, PlaceImageRepository placeImageRepository) {
        this.placeRepository = placeRepository;
        this.s3UploadService = s3UploadService;
        this.placeImageRepository = placeImageRepository;
    }

    public String addPlace(String name, Long price, String description, MultipartFile image) throws IOException {

        Place place = new Place(new Name(name), new Money(price), new Description(description));
        placeRepository.save(place);

        String url = s3UploadService.saveFile(image);
        PlaceImage placeImage = new PlaceImage(place, url);
        placeImageRepository.save(placeImage);

        return "success";
    }

}
