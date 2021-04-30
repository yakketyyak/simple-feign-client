package com.yakketyyak.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yakketyyak.callback.CountriesClientCallback;
import com.yakketyyak.config.FeignClientConfig;
import com.yakketyyak.model.Countries;

/**
 * The Interface CountriesInterface. Defines here all your call API
 * Specification. You can pass header or other things according to your need
 */
@FeignClient(value = "countries", url = "#{serviceUrlConfig.url}", fallback = CountriesClientCallback.class, configuration = FeignClientConfig.class)
public interface CountriesInterface {

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries from https://restcountries.eu/rest/v2
	 */
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Countries> getCountries();

}
