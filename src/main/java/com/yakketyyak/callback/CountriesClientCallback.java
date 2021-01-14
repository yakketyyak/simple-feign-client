package com.yakketyyak.callback;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yakketyyak.impl.CountriesInterface;
import com.yakketyyak.model.Countries;

/**
 * The Class CountriesClientFallback. In case you don't get response from your
 * response add custom logic here
 */
@Component
public class CountriesClientCallback implements CountriesInterface {

	/**
	 * Gets the all. Default behavior if service don't return values
	 *
	 * @return the all
	 */
	@Override
	public List<Countries> getAll() {
		return Collections.emptyList();
	}

}
