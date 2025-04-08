package com.CPAN_228_Group10.CPAN_228_FinalProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.Item;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.Brand;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.repository.ItemRepository;

@SpringBootApplication
public class Cpan228FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cpan228FinalProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ItemRepository repository) {
		return (args) -> {
			repository.save(new Item("T-shirt", Brand.BALENCIAGA, 2022, 1200.00));
			repository.save(new Item("Jeans", Brand.STONE_ISLAND, 2022, 1500.50));
			repository.save(new Item("Jacket", Brand.DIOR, 2022, 2000.00));
			repository.save(new Item("Hoodie", Brand.BALENCIAGA, 2023, 1800.00));
			repository.save(new Item("Pants", Brand.STONE_ISLAND, 2024, 1300.00));
			repository.save(new Item("Sweater", Brand.DIOR, 2022, 2200.00));
		};
	}
}
