package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(BookRepository bookRepository, CategoryRepository categoryRepository) {
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
	    };
	}

}
