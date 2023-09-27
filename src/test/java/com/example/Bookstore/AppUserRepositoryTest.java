package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.domain.AppUser;
import com.example.Bookstore.domain.AppUserRepository;


@DataJpaTest
public class AppUserRepositoryTest {
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Test
	public void findByUserName() {
		AppUser appUsers = appUserRepository.findByUsername("user");
		assertThat(appUsers).isNotNull();
	}
	
	@Test
	public void addAppUser() {
	    AppUser newAppUser = new AppUser();
	    newAppUser.setUsername("Test user");

	    appUserRepository.save(newAppUser);

	    AppUser retrievedCategory = appUserRepository.findByUsername("Test user").get(0);

	    assertThat(retrievedCategory).isNotNull();
	    assertThat(retrievedCategory.getUsername()).isEqualTo("Test user");
	}
	
	@Test
    public void deleteAppUser() {
		AppUser newAppUser = new AppUser();
	    newAppUser.setUsername("Test user");

	    appUserRepository.save(newAppUser);

        appUserRepository.delete(newAppUser);

        AppUser deletedAppUser = appUserRepository.findByUsername("Test user");

        assertThat(deletedAppUser).isNull();
    }
}
