package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.Bookstore.domain.AppUser;
import com.example.Bookstore.domain.AppUserRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Bootstore")
public class BookstoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository appuserRepository) {
	    return (args) -> {
	        // Insert categories
	        Category fictionCategory = new Category("Fiction");
	        Category mysteryCategory = new Category("Mystery");
	        Category fantasyCategory = new Category("Fantasy");

	        categoryRepository.save(fictionCategory);
	        categoryRepository.save(mysteryCategory);
	        categoryRepository.save(fantasyCategory);

	        // Create books and associate them with categories
	        Book b1 = new Book("1984", "George Orwell", 1949, "ISBN978-0451524935", 19.99);
	        b1.setCategory(fictionCategory);

	        Book b2 = new Book("Pride and Prejudice", "Jane Austen", 1813, "ISBN978-0141439518", 29.99);
	        b2.setCategory(fictionCategory);

	        Book b3 = new Book("Harry Potter and the Sorcerer's Stone", "J.K.Rowling", 1997, "ISBN978-0439708180", 39.99);
	        b3.setCategory(fantasyCategory);

	        bookRepository.save(b1);
	        bookRepository.save(b2);
	        bookRepository.save(b3);
	        
	        // Create users: admin/admin user/user
	        AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			appuserRepository.save(user1);
			appuserRepository.save(user2);
	    };
	}

}
