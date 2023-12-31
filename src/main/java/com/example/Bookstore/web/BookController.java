package com.example.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.Bookstore.domain.*;

@Controller
@RequestMapping("/api")
public class BookController {
	
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}  
	
	@ModelAttribute("book")
	public Book book() {
		return new Book();
	}
	
	//List
	@GetMapping("/booklist")
    public String bookList(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "books";
    }
	
	//Delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	bookRepository.deleteById(studentId);
        return "redirect:/booklist";
    }  
	
	//ADD
    @GetMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/savebook")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveBook(@ModelAttribute("book") Book book, @RequestParam("category.id") Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            book.setCategory(category);
            bookRepository.save(book);
        }
        return "redirect:/booklist";
    }
    
    //Edit
	@GetMapping("/editbook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        
        if (book == null) {
            return "redirect:/booklist";
        }

        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());

        return "editbook";
    }

    @PostMapping("/editbook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") Book editedBook, @RequestParam("category.id") Long categoryId) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category != null) {
                book.setTitle(editedBook.getTitle());
                book.setAuthor(editedBook.getAuthor());
                book.setPubYear(editedBook.getPubYear());
                book.setISBN(editedBook.getISBN());
                book.setPrice(editedBook.getPrice());
                book.setCategory(category);

                bookRepository.save(book);
            }
        }
        return "redirect:/booklist";
    }
    
    //REST
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
    
    @GetMapping("/books/{id}")
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId){
    	return bookRepository.findById(bookId).orElse(null);
    }
    
    @PostMapping("/books")
    public Book addBookREST(@RequestBody Book book, @RequestParam("category.id") Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            book.setCategory(category);
            bookRepository.save(book);
        }
        return book;
    }
    
    @DeleteMapping("/books/{id}")
    public String deleteBookREST(@PathVariable("id") Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return "Book with ID " + bookId + " has been deleted.";
        } else {
            return "Book with ID " + bookId + " not found.";
        }
    }  
}
