package com.lisowski.pms;

import com.lisowski.pms.entity.Category;
import com.lisowski.pms.entity.ERole;
import com.lisowski.pms.entity.Product;
import com.lisowski.pms.entity.User;
import com.lisowski.pms.repository.CategoryRepository;
import com.lisowski.pms.repository.ProductRepository;
import com.lisowski.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class PmsApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findByUsername("admin").isEmpty()){
			User user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("test"));
			user.setRole(ERole.ROLE_ADMIN);
			user.setEmail("jacek@o2.pl");
			user.setPhoneNumber("111111111");
			userRepository.save(user);
		}
		if(userRepository.findByUsername("test1").isEmpty()){
			User user = new User();
			user.setUsername("test1");
			user.setPassword(passwordEncoder.encode("test1"));
			user.setRole(ERole.ROLE_USER);
			user.setEmail("michal@o2.pl");
			user.setPhoneNumber("211111112");
			userRepository.save(user);
		}
		if(categoryRepository.findCategoryByName("CPU").isEmpty()){
			Category category = new Category();
			category.setName("CPU");
			categoryRepository.save(category);
		}
		if(categoryRepository.findCategoryByName("GPU").isEmpty()){
			Category category = new Category();
			category.setName("GPU");
			categoryRepository.save(category);
		}
		if(productRepository.findByProductName("i5 2500k").isEmpty()) {
			Product product = new Product();
			product.setProductName("i5 2500k");
			product.setAvailable(true);
			product.setDescription("Procesor na podstawkę LGA1155");
			product.setNetPrice(700.00);
			product.setVat(25);
			product.updateGrossPrice();
			product.setCategory(categoryRepository.findCategoryByName("CPU").get());
			productRepository.save(product);
		}
		if(productRepository.findByProductName("HD7950").isEmpty()) {
			Product product = new Product();
			product.setProductName("HD7950");
			product.setAvailable(false);
			product.setDescription("Grafika do kopania :D");
			product.setNetPrice(6700.24);
			product.setVat(6);
			product.updateGrossPrice();
			product.setCategory(categoryRepository.findCategoryByName("GPU").get());
			productRepository.save(product);
		}


	}
}
