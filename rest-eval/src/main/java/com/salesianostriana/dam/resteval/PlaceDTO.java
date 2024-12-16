package com.salesianostriana.dam.resteval;

import lombok.NoArgsConstructor;

import java.util.List;


public record PlaceDTO(
        Long id,
        String name,
        String address,
        String location,
        String description,
        List<String> tags,
        String image
) {

    public static PlaceDTO fromPlace(Place place){
        return new PlaceDTO(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getCoords(),
                place.getDesc(),
                place.getTags(),
                place.getImage()
        );
    }


}
