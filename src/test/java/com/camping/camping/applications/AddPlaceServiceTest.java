package com.camping.camping.applications;

import com.camping.camping.aws.S3UploadService;
import com.camping.camping.domains.Place;
import com.camping.camping.domains.PlaceImage;
import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.repositories.PlaceImageRepository;
import com.camping.camping.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class AddPlaceServiceTest {

    private PlaceRepository placeRepository;

    private S3UploadService s3UploadService;

    private PlaceImageRepository placeImageRepository;

    private AddPlaceService addPlaceService;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        s3UploadService = mock(S3UploadService.class);
        placeImageRepository = mock(PlaceImageRepository.class);

        addPlaceService = new AddPlaceService(
                placeRepository,
                s3UploadService,
                placeImageRepository
        );

    }

    @Test
    @DisplayName("장소 생성 테스트")
    void addPlaceTest() throws IOException {

        String name = "testPlace";
        Long price = 1000L;
        String description = "testDesc";

        String fileName = "testcamping"; //파일명
        String contentType = "jpg"; //파일타입
        String filePath = "src/test/images/"+fileName+"."+contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile image1 = new MockMultipartFile(
                fileName, //name
                fileName + "." + contentType, //originalFilename
                contentType,
                fileInputStream
        );


        Place place = new Place(
                new Name(name),
                new Money(price),
                new Description(description));

        placeRepository.save(place);
        
        String url = "testUrl"; 

        given(s3UploadService.saveFile(image1)).willReturn(url);

        PlaceImage placeImage = new PlaceImage(
                place,
                url);

        placeImageRepository.save(placeImage);

        String res = addPlaceService.addPlace(name, price, description, image1);

        assertThat(res).isEqualTo("success");


    }


}