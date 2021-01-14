package com.yakketyyak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new countries. For test pursoses i just use these feilds of
 * object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Countries {

	/** The name. */
	private String name;

	/** The alpha 2 code. */
	private String alpha2Code;

	/** The alpha 3 code. */
	private String alpha3Code;

	/** The region. */
	private String region;

}
