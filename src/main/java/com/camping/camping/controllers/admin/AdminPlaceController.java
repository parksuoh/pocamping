package com.camping.camping.controllers.admin;

import com.camping.camping.applications.AddPlaceService;
import com.camping.camping.applications.DeletePlaceService;
import com.camping.camping.applications.GetAdminPlaceReservationsService;
import com.camping.camping.applications.GetAdminPlaceService;
import com.camping.camping.applications.GetAdminPlacesService;
import com.camping.camping.applications.UpdateReservationService;
import com.camping.camping.dtos.AddPlaceRequestDto;
import com.camping.camping.dtos.GetAdminPlacesResponseDto;
import com.camping.camping.dtos.GetPlaceDetailResponseDto;
import com.camping.camping.dtos.GetPlaceReservationResponseDto;
import com.camping.camping.dtos.UpdatePlaceReservationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/place")
public class AdminPlaceController {

    private final AddPlaceService addPlaceService;
    private final GetAdminPlacesService getAdminPlacesService;
    private final GetAdminPlaceService getAdminPlaceService;
    private final DeletePlaceService deletePlaceService;
    private final GetAdminPlaceReservationsService getAdminPlaceReservationsService;

    private final UpdateReservationService updateReservationService;

    public AdminPlaceController(AddPlaceService addPlaceService, GetAdminPlacesService getAdminPlacesService, GetAdminPlaceService getAdminPlaceService, DeletePlaceService deletePlaceService, GetAdminPlaceReservationsService getAdminPlaceReservationsService, UpdateReservationService updateReservationService) {
        this.addPlaceService = addPlaceService;
        this.getAdminPlacesService = getAdminPlacesService;
        this.getAdminPlaceService = getAdminPlaceService;
        this.deletePlaceService = deletePlaceService;
        this.getAdminPlaceReservationsService = getAdminPlaceReservationsService;
        this.updateReservationService = updateReservationService;
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String post(
            @RequestPart(value = "AddPlaceRequestDto") AddPlaceRequestDto addPlaceRequestDto,
            @RequestPart(value = "image") MultipartFile image) throws IOException {

        return addPlaceService.addPlace(
                addPlaceRequestDto.name(),

                addPlaceRequestDto.price(),
                addPlaceRequestDto.description(),
                image
        );
    }

    @GetMapping("/detail/{placeId}")
    public GetPlaceDetailResponseDto detail(@PathVariable Long placeId){

        return getAdminPlaceService.getPlace(placeId);
    }

    @GetMapping("/reservation")
    public List<GetPlaceReservationResponseDto> getReservation(){

        return getAdminPlaceReservationsService.getPlaceReservations();
    }

    @PatchMapping("/reservation")
    public String update(@Valid @RequestBody UpdatePlaceReservationDto updatePlaceReservationDto){

        return updateReservationService.updateReservation(
                updatePlaceReservationDto.placeReservationId(),
                updatePlaceReservationDto.reservcationStatus()
        );
    }


    @GetMapping
    public List<GetAdminPlacesResponseDto> get(){

        return getAdminPlacesService.getAdminPlaces();
    }


    @DeleteMapping("/{placeId}")
    public String delete(@PathVariable Long placeId) {

        return deletePlaceService.deletePlace(placeId);
    }

}
