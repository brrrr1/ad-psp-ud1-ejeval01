package com.salesianostriana.dam.resteval;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/place/")
public class PlaceController {

    private final PlaceRepository placeRepository;

    @GetMapping
    public List<PlaceDTO> getAll(){
        return ResponseEntity.ok(placeRepository.getAll().stream().map(PlaceDTO::fromPlace).collect(Collectors.toList())).getBody();
        //return placeRepository.getAll().stream().map(PlaceDTO::fromPlace).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public PlaceDTO getById(Long id){
        return PlaceDTO.fromPlace(placeRepository.get(id).orElseThrow());
    }

    @PostMapping
    public PlaceDTO create(Place place){
        return PlaceDTO.fromPlace(placeRepository.add(place));
    }

    @PostMapping("{id}")
    public PlaceDTO edit(Long id, Place place){
        return PlaceDTO.fromPlace(placeRepository.edit(id, place).orElseThrow());
    }

    @DeleteMapping("{id}")
    public void delete(Long id){
        placeRepository.delete(id);
    }

    @PostMapping("{id}/tag")
    public PlaceDTO addTag(Long id, String tag){
        return PlaceDTO.fromPlace(placeRepository.addTag(id, tag));
    }

    @DeleteMapping("{id}/tag")
    public PlaceDTO removeTag(Long id, String tag){
        return PlaceDTO.fromPlace(placeRepository.removeTag(id, tag));
    }




}
