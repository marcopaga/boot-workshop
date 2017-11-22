package de.codecentric.boot.workshop.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TruckRepository extends CrudRepository<Truck, Long> {
    Page<Truck> findByLocation(@Param("location") String location, Pageable p);
}
