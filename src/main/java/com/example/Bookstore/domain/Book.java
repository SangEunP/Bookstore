package com.example.Bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String author;
	private int pubYear;
	private String ISBN;
	private double price;
	
	public Book () {}
	public Book (String title, String author, int pubYear, String ISBN, double price){
		super();
		this.title = title;
		this.author = author;
		this.pubYear = pubYear;
		this.ISBN = ISBN;
		this.price = price;
	}
	
	//GET
	public Long getId() {
        return id;
    }
	public String getTitle(){
		return title;
	}
	public String getAuthor(){
		return author;
	}
	public int getPubYear(){
		return pubYear;
	}
	public String getISBN(){
		return ISBN;
	}
	public double getPrice(){
		return price;
	}
	
	//SET
	public void setTitle(String title){
		this.title = title;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public void setPubYear(int pubYear){
		this.pubYear = pubYear;
	}
	public void setISBN(String ISBN){
		this.ISBN = ISBN;
	}
	public void setPrice(Double price){
		this.price = price;
	}
	
	//Category
	@ManyToOne
    @JoinColumn(name = "category_id")
	@JsonIgnore
    private Category category;
	
	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

}
