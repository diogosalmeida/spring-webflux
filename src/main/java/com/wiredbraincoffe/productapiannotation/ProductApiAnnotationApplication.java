package com.wiredbraincoffe.productapiannotation;

import com.wiredbraincoffe.productapiannotation.model.Product;
import com.wiredbraincoffe.productapiannotation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProductApiAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiAnnotationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, ProductRepository repository){
		return args -> {
			Flux<Product> productFlux = Flux.just(
				new Product(null, "Caffe bom", 2.98),
			    new Product(null, "Caffe ruim", 1.00),
			    new Product(null, "Caffe foda", 6.80))
					.flatMap(product -> repository.save(product));


            productFlux.thenMany(repository.findAll())
                    .subscribe(System.out::println);
		};
	}
}
