package com.salesianostriana.dam.resteval;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class PlaceRepository {

    private HashMap<Long, Place> places = new HashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @PostConstruct
    public void init() {
        // Añadir aquí bares de ejemplo
        add(Place.builder()
                .name("Bar El Comercio")
                .address("Calle San Eloy, 3")
                .coords("37.3924,-5.9841")
                .desc("Bar de tapas y desayunos")
                .image("https://www.baresyrestaurantes.es/wp-content/uploads/2019/07/Bar-El-Comercio.jpg")
                .build());

        add(Place.builder()
                .name("Bar La Campana")
                .address("Calle Sierpes, 1")
                .coords("37.3924,-5.9841")
                .desc("Bar de tapas y desayunos")
                .image("https://www.baresyrestaurantes.es/wp-content/uploads/2019/07/Bar-La-Campana.jpg")
                .build());



    }

    public Place add(Place place) {
        var id = counter.incrementAndGet();
        place.setId(id);
        places.put(id, place);
        return place;
    }

    public Optional<Place> get(Long id) {
        return Optional.ofNullable(places.get(id));
    }

    public List<Place> getAll() {
        return List.copyOf(places.values());
    }

    public Optional<Place> edit(Long id, Place place) {
        return Optional.ofNullable(places.computeIfPresent(id, (k,v) -> {
            v.setName(place.getName());
            v.setDesc(place.getDesc());
            v.setImage(place.getImage());
            v.setCoords(place.getCoords());
            v.setAddress(place.getAddress());
            return v;
        }));
    }

    public void delete(Long id) {
        places.remove(id);
    }


    public Place addTag(Long id, String tag){
        var place = places.get(id);
        if (place != null) {
            place.addTag(tag);
        }
        return place;
    }

    public Place removeTag(Long id, String tag) {
        var place = places.get(id);
        if (place != null) {
            place.removeTag(tag);
        }
        return place;
    }



}
