package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void testDefaultMessage() throws Exception{
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
    public void testBookListEndpoint() throws Exception {
		MvcResult result = mockMvc.perform(get("/booklist"))
	               .andExpect(status().isOk())
	               .andReturn();

	    String viewName = result.getModelAndView().getViewName();
	    assertThat(viewName).isEqualTo("booklist");
    }

    @Test
    public void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid"))
               .andExpect(status().isNotFound());
    }
}
