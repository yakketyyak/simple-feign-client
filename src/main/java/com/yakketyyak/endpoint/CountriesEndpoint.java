package com.yakketyyak.endpoint;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yakketyyak.impl.CountriesInterface;
import com.yakketyyak.model.Countries;

import lombok.RequiredArgsConstructor;

/**
 * The Class CountriesEndpoiint.
 */
@RestController
@CrossOrigin("*")

/**
 * Instantiates a new countries endpoiint.
 *
 * @param countriesInterface the countries interface
 */
@RequiredArgsConstructor
public class CountriesEndpoint {

	/** The countries interface. */
	private final CountriesInterface countriesInterface;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Countries>> getAll() {

		return ResponseEntity.ok(this.countriesInterface.getAll());

	}

}
