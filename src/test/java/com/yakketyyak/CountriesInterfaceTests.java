package com.yakketyyak;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.yakketyyak.impl.CountriesInterface;
import com.yakketyyak.model.Countries;

/**
 * Instantiates a new countries interface tests.
 *
 * @param countriesInterface the countries interface
 */
@SpringBootTest
@ExtendWith(SpringExtension.class) // for JUnit 5
@AutoConfigureMockMvc
public class CountriesInterfaceTests {

	/** The countries interface. */
	@MockBean
	private CountriesInterface countriesInterface;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Should return values when calling service get all.
	 * 
	 * @throws Exception
	 */
	@Test
	public void should_return_values_when_calling_service_getAll() throws Exception {
		Countries country = Countries.builder().region("region").alpha2Code("alpha2code").alpha3Code("alpha3Code")
				.name("name").build();
		List<Countries> countries = Arrays.asList(country);

		given(countriesInterface.getAll()).willReturn(countries);

		mockMvc.perform(get("/countries")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].name").exists()).andExpect(jsonPath("$[0].alpha2Code").exists())
				.andExpect(jsonPath("$[0].alpha3Code").exists()).andExpect(jsonPath("$[0].region").exists());

	}

}
