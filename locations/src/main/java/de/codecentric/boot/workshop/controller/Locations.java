package de.codecentric.boot.workshop.controller;

import de.codecentric.boot.workshop.model.Location;
import de.codecentric.boot.workshop.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/locations")
public class Locations {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.trucks.uri}")
    private String trucksServiceUri;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public Iterable<Resource<Location>> list() {
        final Resource<Location> solingen = constructLocationResource("Solingen");
        final Resource<Location> erkrath = constructLocationResource("Erkrath");

        return Arrays.asList(solingen, erkrath);
    }

    @RequestMapping(path = "/{location}", method = RequestMethod.GET)
    public Resources<Truck> location(@PathVariable("location") final String location) {
        final String uri = trucksServiceUri + "search/findByLocation?location=" + location;
        final ResponseEntity<PagedResources<Resource<Truck>>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new PagedTrucksType());

        final List<Truck> trucks = response.getBody().getContent().stream().map(Resource::getContent).collect(Collectors.toList());

        final Link locationsLink = linkTo(methodOn(Locations.class).list()).withRel("locations");
        return new Resources<Truck>(trucks, locationsLink);
    }

    private Resource<Location> constructLocationResource(final String name) {
        final Location location = new Location();
        location.setName(name);

        final Link trucksLink = linkTo(methodOn(Locations.class).location(name)).withRel("trucks");
        return new Resource<>(location, trucksLink);
    }

    private static final class PagedTrucksType extends TypeReferences.PagedResourcesType<Resource<Truck>> {
    }
}
