package com.example.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findByTitle(String title);
	
	void deleteById(Long id);
	
	List<Book> getBookById(Long id);
}
