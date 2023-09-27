package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void findByTitle() {
		List<Book> books = bookRepository.findByTitle("1984");
		assertThat(books).hasSize(3);
		assertThat(books.get(0).getAuthor().equals("George Orwell"));
	}
	
	@Test
	public void addBook() {
	    Book newBook = new Book();
	    newBook.setTitle("Test Book");
	    newBook.setAuthor("Test Author");
	    newBook.setPubYear(2023);
	    newBook.setISBN("1234567890");
	    newBook.setPrice(19.99);

	    bookRepository.save(newBook);

	    Book retrievedBook = bookRepository.findByTitle("Test Book").get(0);

	    assertThat(retrievedBook).isNotNull();
	    assertThat(retrievedBook.getTitle()).isEqualTo("Test Book");
	    assertThat(retrievedBook.getAuthor()).isEqualTo("Test Author");
	    assertThat(retrievedBook.getPubYear()).isEqualTo(2023);
	    assertThat(retrievedBook.getISBN()).isEqualTo("1234567890");
	    assertThat(retrievedBook.getPrice()).isEqualTo(19.99);
	}
	
	@Test
    public void deleteBook() {
        Book newBook = new Book();
        newBook.setTitle("Test Book");
        newBook.setAuthor("Test Author");
        newBook.setPubYear(2023);
        newBook.setISBN("1234567890");
        newBook.setPrice(19.99);

        bookRepository.save(newBook);

        bookRepository.delete(newBook);

        Book deletedBook = bookRepository.findByTitle("Test Book").stream().findFirst().orElse(null);

        assertThat(deletedBook).isNull();
    }
}
