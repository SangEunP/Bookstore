package com.example.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.domain.*;

@Controller
public class BookController {
	
	@Autowired
    private BookRepository bookRepository;
	
	@ModelAttribute("book")
	public Book book() {
		return new Book();
	}

	@GetMapping("/booklist")
    public String bookList(Model model) {
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
	    bookRepository.deleteById(id);

	    return "redirect:/booklist";
	}
	
	@GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }
}
