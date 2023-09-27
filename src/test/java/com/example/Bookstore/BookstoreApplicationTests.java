package com.example.Bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Bookstore.web.BookController;

import static org.assertj.core.api.Assertions.assertThat; //specific


@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	@Test
	void contextLoads() throws Exception{
		assertThat(bookController).isNotNull();
	}
}
