package com.example.Bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Bookstore.domain.*;

@Controller
public class BookController {
	@GetMapping("/index")
	public String index(Model model){
		List<Book>books = new ArrayList<>();
		books.add(new Book("Guns, Germs, and Steel", "Jared Diamond", 1997, "ISBN-0-393-03891-2", 43.55));
		books.add(new Book("Cosmos", "Carl Sagan", 1983, "ISBN-0-13-3456-777", 30.99));
		books.add(new Book("Harry Potter and the Prisoner of Azkaban", "J. K. Rowling ", 1999, "ISBN-0-7475-4215-5", 12.9));
		
		model.addAttribute("books", books);
		return "index";
	}
}
