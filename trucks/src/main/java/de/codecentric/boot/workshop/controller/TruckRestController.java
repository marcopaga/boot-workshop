package de.codecentric.boot.workshop.controller;

import de.codecentric.boot.workshop.model.Truck;
import de.codecentric.boot.workshop.model.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/homemade")
public class TruckRestController {

    @Autowired
    private TruckRepository truckRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/trucks")
    public Iterable<Truck> allTrucks(){
        return truckRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/truck")
    public ResponseEntity<?> createTruck(@RequestBody Truck truck, UriComponentsBuilder b){
        final Truck saved = truckRepository.save(truck);
        final Long id = saved.getId();

        UriComponents uriComponents = b.path("/trucks/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
