package com.camping.camping.controllers;

import com.camping.camping.applications.AddPlaceReservationService;
import com.camping.camping.applications.GetPlaceDetailService;
import com.camping.camping.applications.GetPlaceReservationsService;
import com.camping.camping.applications.GetPlacesService;
import com.camping.camping.dtos.AddPlaceReservationRequestDto;
import com.camping.camping.dtos.AuthUserDto;
import com.camping.camping.dtos.GetPlaceDetailResponseDto;
import com.camping.camping.dtos.GetPlaceReservationResponseDto;
import com.camping.camping.dtos.GetPlacesDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final GetPlacesService getPlacesService;
    private final GetPlaceDetailService getPlaceDetailService;
    private final AddPlaceReservationService addPlaceReservationService;
    private final GetPlaceReservationsService getPlaceReservationsService;

    public PlaceController(GetPlacesService getPlacesService, GetPlaceDetailService getPlaceDetailService, AddPlaceReservationService addPlaceReservationService, GetPlaceReservationsService getPlaceReservationsService) {
        this.getPlacesService = getPlacesService;
        this.getPlaceDetailService = getPlaceDetailService;
        this.addPlaceReservationService = addPlaceReservationService;
        this.getPlaceReservationsService = getPlaceReservationsService;
    }

    @GetMapping("/detail/{placeId}")
    public GetPlaceDetailResponseDto get(@PathVariable Long placeId){

        return getPlaceDetailService.getPlaceDetail(placeId);
    }

    @GetMapping("/reservations")
    public List<GetPlaceReservationResponseDto> reservations(Authentication authentication) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();
        return getPlaceReservationsService.getPlaceReservations(authUser.name());
    }

    @GetMapping
    public List<GetPlacesDto> get(){

        return getPlacesService.getPlaces();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String reservation(
            Authentication authentication,
            @Valid @RequestBody AddPlaceReservationRequestDto addPlaceReservationRequestDto
    ) {
        AuthUserDto authUser = (AuthUserDto) authentication.getPrincipal();

        return addPlaceReservationService.addPlaceReservation(
                authUser.name(),
                addPlaceReservationRequestDto.placeId(),
                addPlaceReservationRequestDto.reservationDate()
        );
    }




}
