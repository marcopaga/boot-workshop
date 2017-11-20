package de.codecentric.boot.workshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/locations")
public class Locations {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${service.trucks.uri}")
    private String trucksServiceUri;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<Iterable<String>> list() {
        return new ResponseEntity<Iterable<String>>(Arrays.asList("Köln", "Erkrath", "Düsseldorf"), HttpStatus.OK);
    }

    @RequestMapping(path = "/{location}", method = RequestMethod.GET)
    public String location(@PathVariable("location") final String location) {
        return restTemplate.getForObject(trucksServiceUri, String.class);
    }

}
