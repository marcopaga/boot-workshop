package de.codecentric.boot.workshop;

import de.codecentric.boot.workshop.model.Truck;
import de.codecentric.boot.workshop.model.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TrucksApplication {

	@Autowired
	private TruckRepository truckRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrucksApplication.class, args);
	}

	@PostConstruct
	public void load(){
		truckRepository.save(new Truck("ME TC 1234", "Erkrath"));
	}
}
