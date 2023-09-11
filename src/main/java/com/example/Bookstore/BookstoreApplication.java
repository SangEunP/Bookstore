package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book b1 = new Book("1984", "George Orwell", 1949, "ISBN978-0451524935", 19.99);
            Book b2 = new Book("Pride and Prejudice", "Jane Austen", 1813, "ISBN978-0141439518", 29.99);
            Book b3 = new Book("Harry Potter and the Sorcerer's Stone", "J.K.Rowling", 1997, "ISBN978-0439708180", 39.99);
		
            repository.save(b1);
            repository.save(b2);
            repository.save(b3);
		};
	}
}
