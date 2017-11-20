package de.codecentric.boot.workshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories("de.codecentric.boot.workshop.model")
public class TrucksApplicationTests {

	@Test
	public void contextLoads() {
	}

}
