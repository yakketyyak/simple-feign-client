package com.yakketyyak;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakketyyak.endpoint.CountriesEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.yakketyyak.impl.CountriesInterface;
import com.yakketyyak.model.Countries;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Instantiates a new countries interface tests.
 *
 * @param countriesInterface the countries interface
 */
@WebMvcTest(controllers = CountriesEndpoint.class)
@AutoConfigureMockMvc
class CountriesInterfaceTests {

	/** The countries interface. */
	@MockBean
	private CountriesInterface countriesInterface;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * Should return values when calling service get all.
	 *
	 */
	@Test
	void getCountries_fetchAllValues_returnAll() throws Exception {
		Countries country = Countries.builder().region("region").alpha2Code("alpha2code").alpha3Code("alpha3Code")
				.name("name").build();
		List<Countries> countries = Collections.singletonList(country);

		given(countriesInterface.getCountries()).willReturn(countries);

		MvcResult result = mockMvc.perform(get("/countries")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		List<Countries>  response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Countries>>(){});

		assertThat(response).isNotEmpty();
		assertThat(response.get(0).getName()).isEqualTo("name");
		assertThat(response.get(0).getRegion()).isEqualTo("region");
	}

}
