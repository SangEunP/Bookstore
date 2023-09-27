package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;


@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findByName() {
		List<Category> categories = categoryRepository.findByName("Fiction");
		assertThat(categories).hasSize(3);
		assertThat(categories).isNotNull();
	}
	
	@Test
	public void addCategory() {
	    Category newCategory = new Category();
	    newCategory.setName("Test Category");

	    categoryRepository.save(newCategory);

	    Category retrievedCategory = categoryRepository.findByName("Test Category").get(0);

	    assertThat(retrievedCategory).isNotNull();
	    assertThat(retrievedCategory.getName()).isEqualTo("Test Category");
	}
	
	@Test
    public void deleteCategory() {
		Category newCategory = new Category();
	    newCategory.setName("Test Category");

	    categoryRepository.save(newCategory);

        categoryRepository.delete(newCategory);

        Category deletedCategory = categoryRepository.findByName("Test Category").stream().findFirst().orElse(null);

        assertThat(deletedCategory).isNull();
    }
}
